package com.neemshade.matri.service.paramCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.neemshade.matri.domain.Cascader;
import com.neemshade.matri.domain.CascaderParam;
import com.neemshade.matri.service.CascaderParamService;
import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.service.dto.CascaderDTO;
import com.neemshade.matri.service.dto.CascaderParamDTO;
import com.neemshade.matri.service.mapper.CascaderMapper;
import com.neemshade.matri.service.mapper.CascaderParamMapper;


/*
Cascading data extracted from string
values are stored into db

Special chars 
hypen is delimiter of cascadeName and rest of data
/ separates sibling data
[] encapsules children of parent node

for example
bodyType - Slim/Average/Athletic/Heavy

Location - India[Tamilnadu[Coimbatore/Chennai/Salem]
/Karnataka[Bengaluru/Mysuru]]
/USA[California[San Fransisco/San Jose]]	 

*/


public class CascadeParamEngine extends ParamEngine {
	
	private final Logger log = LoggerFactory.getLogger(CascadeParamEngine.class);
	
	final private String delimiters = "-/[]";
	
	private Stack<CascaderParam> stack = new Stack<>();
	private CascaderParam parent = null;
	
	private Cascader cascader = null;
	private boolean isNewCascader = false;
	
	private int paramLevel = 1;
	private boolean shouldPeckOrderFetched = true;
	private int peckOrder = 10;
	
	private CascaderService cascaderService;
	
	private CascaderMapper cascaderMapper;
	
	private CascaderParamService cascaderParamService;
	
	private CascaderParamMapper cascaderParamMapper;
	

	public CascadeParamEngine(MultipartFile file) {
		super(file);
		
		cascaderService = (CascaderService) servicesMap.get("CascaderService");
		cascaderMapper = (CascaderMapper) servicesMap.get("CascaderMapper");
		
		cascaderParamService = (CascaderParamService) servicesMap.get("CascaderParamService");
		cascaderParamMapper = (CascaderParamMapper) servicesMap.get("CascaderParamMapper");
	}

	@Override
	public void preOperation() {
		stack.clear();
		parent = null;
		
		paramLevel = 1;
		shouldPeckOrderFetched = true;
		peckOrder = 10;
	}

	@Override
	public void postOperation() {
		
	}

	@Override
	public void parseLine(String line) {
		
		// isNewCascader = false;
		
		// check if the line has cascadeName
		// portion before hypen is used to create cascader object
		// portion after hyphen is treated in regular manner to handle params
		int hyphenPos = line.indexOf('-');
		if(hyphenPos >= 0) {
			String namePortion = line.substring(0, hyphenPos);
			cascader = composeCascader(namePortion);
			log.debug("cascade name [{}] [{}]", cascader.getCascaderName(), cascader.isCanEnterCustomValue());
			
			if(hyphenPos + 1 < line.length()) {
				line = line.substring(hyphenPos + 1);
			} else {
				line = null;
			}
			

			// if it is first time to create the cascader, check if it is height.
			// if so, fill cascaderParam
			if(isNewCascader && cascader.getCascaderName().equals("height")) {
				
				line = "";
				// these are number of inches from 2.5 to 8.3 feets
				for(int i=30; i < 101; i++) {
					line += ("/" + i);
				}
				
				isNewCascader = false;
				// log.debug("inside height forming - {}", line);
			}
		}
		

		if(line == null)
			return;
		
		line = line.trim();
		
		
		
		if("".equals(line))
			return;
		
		StringTokenizer st = new StringTokenizer(line, delimiters, true);
		CascaderParam prevToken = null;

		List<String> tokens = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken().trim());
		}

		for (int j = 0; j < tokens.size(); j++) {
			String token = tokens.get(j);
			
			if(token == null || "".equals(token))
				continue;

			// it is special char. handle the delimiter
			if (token.length() == 1 && delimiters.indexOf(token) >= 0) {

				// start of a list
				if (token.equals("[")) {
					stack.push(parent);
					parent = prevToken;
					
					paramLevel++;
					shouldPeckOrderFetched = true;
				}

				// end of a list
				if (token.equals("]")) {
					parent = stack.empty() ? null : stack.pop();
					prevToken = parent;
					
					paramLevel--;
					shouldPeckOrderFetched = true;
				}
				
				continue;
			}

			log.debug("token : {} -> {}", parent, token);
			
			CascaderParam cascaderParam = composeCascaderParam(token);
			prevToken = cascaderParam;

		}

		
	}

	/**
	 * create or retrieve cascaderParam
	 * @param token
	 * @return
	 */
	private CascaderParam composeCascaderParam(String token) {
		
		peckOrder = findPeckOrderOfCascaderParam();
		
		CascaderParamDTO requiredCascaderParamDTO = new CascaderParamDTO();
		requiredCascaderParamDTO.setCascaderId(cascader == null ? null : cascader.getId());
		requiredCascaderParamDTO.setParentId(parent == null ? null : parent.getId());
		requiredCascaderParamDTO.setLevelIndex(paramLevel);
		requiredCascaderParamDTO.setParamTitle(token);
		requiredCascaderParamDTO.setPeckOrder(peckOrder);
		
		return sureGetCascaderParam(requiredCascaderParamDTO);
	}
	
	
	private CascaderParam sureGetCascaderParam(CascaderParamDTO givenCascaderParamDTO) {
		Optional<CascaderParamDTO> optionalCascaderParamDTO = cascaderParamService.findUniqueParamTitle(
				givenCascaderParamDTO.getCascaderId(), givenCascaderParamDTO.getParentId(),
				givenCascaderParamDTO.getParamTitle());
		
		// if record already exist, return it
		CascaderParamDTO obtainedCascaderParamDTO = null;
		
		
		try {
			if(optionalCascaderParamDTO.isPresent()) {
				obtainedCascaderParamDTO = optionalCascaderParamDTO.get();
			} else {
				obtainedCascaderParamDTO = cascaderParamService.save(givenCascaderParamDTO);
				successCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(obtainedCascaderParamDTO == null) {
			errorTokens.add(givenCascaderParamDTO.getParamTitle());
			return null;
		}
			 
		return cascaderParamMapper.toEntity(obtainedCascaderParamDTO);
	}

	/**
	 * if shouldPeckOrderFetched is false, return the current peckOrder
	 * if not, get the max peckOrder available for parentId
	 * @return
	 */
	private int findPeckOrderOfCascaderParam() {
		if(!shouldPeckOrderFetched)
			return peckOrder;
		
		Optional<CascaderParamDTO> maxRecord = cascaderParamService.findMaxPeckOrderUnderParentId(
				cascader == null ? null : cascader.getId(), 
				parent == null ? null : parent.getId());
		
		if(maxRecord.isPresent()) {
			peckOrder = maxRecord.get().getPeckOrder() + 10;
		} else {
			peckOrder = 10;
		}
		
		return peckOrder;
	}

	/**
	 * the given string has name and flag to tell if field can be given custom value
	 * cascader object is created.  Checks if db already has the cascader, if so fetch the same from db.
	 * Else write into db and fetch the created record.
	 * @param namePortion
	 * @return
	 */
	private Cascader composeCascader(String namePortion) {
		String CUSTOM = "canEnterCustomValue";
		
		namePortion = namePortion.trim();
		int flagPos = namePortion.indexOf(CUSTOM);
		
		String name = namePortion;
		if(flagPos >= 0) {
//			log.debug("flagPos = {}, CUSTOM = {}, namePortion = {}", flagPos, CUSTOM.length(), namePortion.length());
//			name = namePortion.substring(0, flagPos) + namePortion.substring(flagPos + CUSTOM.length() + 1);
			name = namePortion.replace(CUSTOM, "");
			name = name.trim();
		}
		
		CascaderDTO requiredCascaderDTO = new CascaderDTO();
		requiredCascaderDTO.setCascaderName(name);
		requiredCascaderDTO.setCanEnterCustomValue(flagPos >= 0);
		
		return sureGetCascader(requiredCascaderDTO);
	}

	/**
	 * check if record exist with the given title.  return if found
	 * else create a new record and return a copy of the saved record
	 */
	private Cascader sureGetCascader(CascaderDTO givenCascaderDTO) {
		Optional<CascaderDTO> optionalCascaderDTO = cascaderService.findOneByCascaderName(givenCascaderDTO.getCascaderName());
		
		// if record already exist, return it
		CascaderDTO obtainedCascaderDTO = null;
		
		
		try {
			if(optionalCascaderDTO.isPresent()) {
				obtainedCascaderDTO = optionalCascaderDTO.get();
				isNewCascader = false;
			} else {
				obtainedCascaderDTO = cascaderService.save(givenCascaderDTO);
				log.debug("new cascader=[{}] ", obtainedCascaderDTO.getCascaderName());
				isNewCascader = true;
				successCount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(obtainedCascaderDTO == null) {
			errorTokens.add(givenCascaderDTO.getCascaderName());
			return null;
		}
			 
		return cascaderMapper.toEntity(obtainedCascaderDTO);
	}

}
