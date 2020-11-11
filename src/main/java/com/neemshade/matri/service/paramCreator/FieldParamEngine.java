package com.neemshade.matri.service.paramCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.neemshade.matri.domain.Cascader;
import com.neemshade.matri.domain.CascaderParam;
import com.neemshade.matri.domain.FieldAttribute;
import com.neemshade.matri.domain.Mala;
import com.neemshade.matri.service.CascaderParamService;
import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.service.FieldAttributeService;
import com.neemshade.matri.service.FieldService;
import com.neemshade.matri.service.MalaParamService;
import com.neemshade.matri.service.MalaService;
import com.neemshade.matri.service.dto.FieldAttributeDTO;
import com.neemshade.matri.service.dto.FieldDTO;
import com.neemshade.matri.service.dto.MalaDTO;
import com.neemshade.matri.service.dto.MalaParamDTO;
import com.neemshade.matri.service.mapper.CascaderMapper;
import com.neemshade.matri.service.mapper.CascaderParamMapper;
import com.neemshade.matri.service.mapper.FieldAttributeMapper;
import com.neemshade.matri.service.mapper.FieldMapper;
import com.neemshade.matri.service.mapper.MalaMapper;
import com.neemshade.matri.service.mapper.MalaParamMapper;

public class FieldParamEngine extends ParamEngine {
	
	private final Logger log = LoggerFactory.getLogger(FieldParamEngine.class);
	
	private Map<String, Cascader> cascaderMap = null;
	/*
	 * map of maps.  first key is cascaderName and nested key is paramTitle
	 */
	private Map<String, Map<String, CascaderParam>> cascaderParamMap = null;
	
	private Mala mala;
	
	private MalaService malaService;
	private MalaMapper malaMapper;
	
	private MalaParamService malaParamService;
	private MalaParamMapper malaParamMapper;
	
	private FieldService fieldService;
	private FieldMapper fieldMapper;
	
	private FieldAttributeService fieldAttributeService;
	private FieldAttributeMapper fieldAttributeMapper;
	
	private CascaderService cascaderService;
	
	private CascaderMapper cascaderMapper;
	
	private CascaderParamService cascaderParamService;
	
	private CascaderParamMapper cascaderParamMapper;
	

	
	private int fieldPeckOrder = 10;
	private int paramPeckOrder = 10;

	
	
	public final static String MALA_NAME = "malaName";
	public final static String CASCADER = "cascader";
	public final static String DESCRIPTION = "description";
	public final static String FIELD_NAME = "fieldName";
	public final static String DATA_TYPE = "dataType";
	public final static String PARAM_SOURCE = "paramSource";
	public final static String REPLACE = "replace";
	public final static String DYNAMIC_PARAM = "dynamicParam";
	public final static String RANGE = "range";

	public FieldParamEngine(MultipartFile file) {
		super(file);
		
		cascaderService = (CascaderService) servicesMap.get("CascaderService");
		cascaderMapper = (CascaderMapper) servicesMap.get("CascaderMapper");
		
		cascaderParamService = (CascaderParamService) servicesMap.get("CascaderParamService");
		cascaderParamMapper = (CascaderParamMapper) servicesMap.get("CascaderParamMapper");
		
		malaService = (MalaService) servicesMap.get("MalaService");
		malaMapper = (MalaMapper) servicesMap.get("MalaMapper");
		
		malaParamService = (MalaParamService) servicesMap.get("MalaParamService");
		malaParamMapper = (MalaParamMapper) servicesMap.get("MalaParamMapper");
		
		fieldService = (FieldService) servicesMap.get("FieldService");
		fieldMapper = (FieldMapper) servicesMap.get("FieldMapper");
		
		fieldAttributeService = (FieldAttributeService) servicesMap.get("FieldAttributeService");
		fieldAttributeMapper = (FieldAttributeMapper) servicesMap.get("FieldAttributeMapper");
	}

	@Override
	public void preOperation() {
		
		Optional<FieldDTO> optionalFieldDTO = fieldService.findMaxPeckOrder();
		
		fieldPeckOrder = optionalFieldDTO.isPresent() ? 
				optionalFieldDTO.get().getPeckOrder() + 10 : 10;
		paramPeckOrder = 10;
		
		
	}

	@Override
	public void postOperation() {
		
	}

	@Override
	public void parseLine(String line) {
		Map<String, Object> tokens = gatherTokens(line);
		
		if(tokens == null)
			return;
		
		if(tokens.containsKey("malaName")) {
			composeMala(tokens);
			return;
		}
		
		if(!tokens.containsKey("fieldName")) {
			log.error("Error in line. fieldName expected {}", line);
			errorTokens.add(line);
			return;
		}
		
		composeField(tokens);
		
		
	}

	/**
	 * go thru given line, parse the tokens
	 * anything before hyphen is field name (can have space)
	 * the description is placed inside two double quotes
	 * other words (without space) are considered attributes
	 * special keyword replace may be part of attributes
	 * @param line
	 * @return
	 */
	private Map<String, Object> gatherTokens(String line) {

		Map<String, Object> tokens = new HashMap<>();

		int quotesPos = line.indexOf('"');
		// check if quotes is present
		if(quotesPos >= 0) {
			int endQuotesPos = line.lastIndexOf('"');
			if(quotesPos == endQuotesPos) {
				tokens.put(DESCRIPTION, line.substring(quotesPos + 1));
				line = line.substring(0, quotesPos);
			} else {
				tokens.put(DESCRIPTION, line.substring(quotesPos + 1, endQuotesPos));
				line = line.substring(0, quotesPos) + line.substring(endQuotesPos + 1);
			}
		}
		
		int percentPos = line.indexOf('%');
		if(percentPos >= 0) {
			String malaName = line.substring(percentPos + 1); 
			tokens.put(MALA_NAME, malaName.trim());
			return tokens;
		}
		
		
		
		int hyphenPos = line.indexOf('-');
		
		if(hyphenPos <= 0) {
			log.error("line with no field name : {}", line );
			errorTokens.add(line);
			return null;
		}
		
		String fieldName = line.substring(0, hyphenPos);
		
		if(fieldName == null || "".equals(fieldName.trim())) {
			log.error("line with empty field name : {}", line );
			errorTokens.add(line);
			return null;
		}
		
		tokens.put(FIELD_NAME, fieldName.trim());
		
		if(hyphenPos >= line.length()) {
			return tokens;
		}
		
		line = line.substring(hyphenPos + 1);
		
		
		String words[] = line.split("\\s");
		for (int i = 0; i < words.length; i++)  {
			String word = words[i];
			
			if("".equals(word)) {
				continue;
			}
			
			if(CASCADER.equals(word)) {
				if(i+1 >= words.length){
					log.error("invalid cascader name : {}", line );
					errorTokens.add(line);
					return null;
				}
				
				tokens.put(word, words[i+1]);
				
				i++;
				continue;
			}
			
			tokens.put(word, "");
		}
		
		return tokens;
	}

	/**
	 * go thru tokens and save field, malaParam and fieldAttributes
	 * @param tokens
	 */
	public void composeField(Map<String, Object> tokens) {
		
		// the tokens that should not go into fieldAttribute table
		Map<String, Boolean> excludes = new HashMap<>();
		String excludedWords[] = { REPLACE, FIELD_NAME };
		for (String excludeWord : excludedWords) {
			excludes.put(excludeWord, true);
		}
		
		
		FieldDTO fieldDTO = new FieldDTO();
		fieldDTO.setPeckOrder(fieldPeckOrder);
		
		List<FieldAttributeDTO> fieldAttributeList = new ArrayList<>();
		
		for(String key : tokens.keySet()) {
//			log.debug("key = {}, value = {}", key, tokens.get(key));
			
			if(FIELD_NAME.equals(key)) {
				fieldDTO.setFieldName((String) tokens.get(key));
				continue;
			}
			
			if(CASCADER.equals(key)) {
				Cascader cascade = fetchCascader((String) tokens.get(CASCADER));
				fieldDTO.setCascaderId(cascade == null ? null : cascade.getId());
				continue;
			}
			
			CascaderParam cascadeParamFromDataType = fetchCascaderParam(DATA_TYPE, key);
			if(cascadeParamFromDataType != null) {
				fieldDTO.setDataTypeId(cascadeParamFromDataType.getId());
				continue;
			}
			
			CascaderParam cascadeParamFromParamSource = fetchCascaderParam(PARAM_SOURCE, key);
			if(cascadeParamFromParamSource != null) {
				fieldDTO.setDataSourceId(cascadeParamFromParamSource.getId());
				continue;
			}
			
			if(excludes.containsKey(key)) {
				continue;
			}
			
			FieldAttributeDTO fieldAttributeDTO = new FieldAttributeDTO();
			fieldAttributeDTO.setAttributeName(key);
			fieldAttributeList.add(fieldAttributeDTO);
		}
		
		
		if(fieldDTO.getDataTypeId() == null && 
				!(tokens.containsKey(CASCADER) || tokens.containsKey(RANGE))) {
			log.error("data type is missing in this field {}", fieldDTO.getFieldName());
			errorTokens.add(fieldDTO.getFieldName());
			return;
		}
		
		if(fieldDTO.getDataSourceId() == null) {
			CascaderParam dynamicParam = fetchCascaderParam(PARAM_SOURCE, DYNAMIC_PARAM);
			if(dynamicParam == null) {
				log.error("dynamicParam missing");
				return;
			}
			fieldDTO.setDataSourceId(dynamicParam.getId());
		}
		
//		log.debug("Got this field {}", fieldDTO);
		saveFieldAndAttributes(fieldDTO, fieldAttributeList, tokens);
	}
	
	
	private void saveFieldAndAttributes(FieldDTO fieldDTO, List<FieldAttributeDTO> fieldAttributeList,
			Map<String, Object> tokens) {
		// mala should be defined
		if(mala == null) {
			return;
		}
		
		Optional<FieldDTO> optionalFieldDTO = fieldService.findUniqueFieldUnderMala(mala.getMalaName(), fieldDTO.getFieldName());
		
		FieldDTO obtainedFieldDTO = null;
		boolean isNewField = false;
		
		if(optionalFieldDTO.isPresent()) {
			obtainedFieldDTO = optionalFieldDTO.get();
		} else {
			obtainedFieldDTO = fieldService.save(fieldDTO);
			isNewField = true;
			successCount++;
			fieldPeckOrder += 10;
		}
		
		// if new field or attribute has replace, then store attributes and malaparam
		if(! (isNewField || tokens.containsKey(REPLACE))) {
			return;
		}
		
		// if new field, place an entry into malaParam table
		if(isNewField) {
			MalaParamDTO malaParamDTO = new MalaParamDTO();
			malaParamDTO.setMalaId(mala.getId());
			malaParamDTO.setFieldId(obtainedFieldDTO.getId());
			malaParamDTO.setPeckOrder(paramPeckOrder);
			
			malaParamService.save(malaParamDTO);
			paramPeckOrder += 10;
		}
		
		// if it is existing field, delete the attribute fields
		if(tokens.containsKey(REPLACE)) {
			fieldAttributeService.deleteUnderField(obtainedFieldDTO.getId());
		}
		
		for (FieldAttributeDTO fieldAttributeDTO : fieldAttributeList) {
			fieldAttributeDTO.setFieldId(obtainedFieldDTO.getId());
			fieldAttributeService.save(fieldAttributeDTO);
		}
	}

	/**
	 * retrieve cascaderMap from db for the first time
	 * refer the map for cascaderName
	 * @param cascaderName
	 * @return
	 */
	public Cascader fetchCascader(String cascaderName) {
		
		if(cascaderMap == null) {
			cascaderMap = cascaderService.findAllCascaders();
		}
		
		if(cascaderMap == null || !cascaderMap.containsKey(cascaderName)) {
			log.error("Cascader {} not found in db", cascaderName);
			return null;
		}
		
		return cascaderMap.get(cascaderName);
	}
	
	/**
	 * load all the cascaderParam maps from db for the first time
	 * refer the map to get the cascaderParam of paramTitle
	 * @param paramTitle
	 * @return
	 */
	public CascaderParam fetchCascaderParam(String cascaderName, String paramTitle) {
		
		if(cascaderParamMap == null) {
			cascaderParamMap = new HashMap<>();
			String cascaderNames[] = { PARAM_SOURCE, DATA_TYPE };
			
			for (String cn : cascaderNames) {
				cascaderParamMap.put(cn, cascaderParamService.findAllParamsOfCascader(cn));
			}		
			
		}
		
		if(cascaderParamMap == null || !cascaderParamMap.containsKey(cascaderName) || 
				!cascaderParamMap.get(cascaderName).containsKey(paramTitle)) {
//			log.error("CascaderParam {} not found in db, {}", paramTitle, cascaderParamMap);
			return null;
		}
		
		return cascaderParamMap.get(cascaderName).get(paramTitle);
	}
	
	public void composeMala(Map<String, Object> tokens) {
		
		MalaDTO malaDTO = new MalaDTO();
		malaDTO.setMalaName((String) tokens.get(MALA_NAME));
		malaDTO.setDescription((String) tokens.get(DESCRIPTION));
		malaDTO.setIsEditable(true);
		
		mala = sureGetMala(malaDTO);
		
		if(mala == null)
			return;
		
		Optional<MalaParamDTO> malaParamMaxPeckOrder = malaParamService.findMaxPeckOrderUnderMala(mala.getId());
		
		paramPeckOrder = malaParamMaxPeckOrder.isPresent() ?
				malaParamMaxPeckOrder.get().getPeckOrder() + 10 : 10;
	}

	/**
	 * check if mala already exist, if so, retrive that record
	 * else create a new record
	 * @param malaDTO
	 * @return
	 */
	private Mala sureGetMala(MalaDTO malaDTO) {
		Optional<MalaDTO> optionalMalaDTO = malaService.findUniqueMala(malaDTO.getMalaName());
		
		MalaDTO obtainedMalaDTO = null;
		if(optionalMalaDTO.isPresent()) {
			obtainedMalaDTO = optionalMalaDTO.get();
			
		} else {
			obtainedMalaDTO = malaService.save(malaDTO);
			successCount++;
		}
		
		if(obtainedMalaDTO == null) {
			log.error("Unable to create or retrive mala {}" + malaDTO.getMalaName());
			errorTokens.add(malaDTO.getMalaName());
			return null;
		}
		
		return malaMapper.toEntity(obtainedMalaDTO);
	}
}
