package com.neemshade.matri.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.neemshade.matri.service.CascaderParamService;
import com.neemshade.matri.service.CascaderService;
import com.neemshade.matri.service.FieldAttributeService;
import com.neemshade.matri.service.FieldService;
import com.neemshade.matri.service.MalaParamService;
import com.neemshade.matri.service.MalaService;
import com.neemshade.matri.service.ParamCreatorService;
import com.neemshade.matri.service.mapper.CascaderMapper;
import com.neemshade.matri.service.mapper.CascaderParamMapper;
import com.neemshade.matri.service.mapper.FieldAttributeMapper;
import com.neemshade.matri.service.mapper.FieldMapper;
import com.neemshade.matri.service.mapper.MalaMapper;
import com.neemshade.matri.service.mapper.MalaParamMapper;
import com.neemshade.matri.service.paramCreator.FieldParamEngine;
import com.neemshade.matri.service.paramCreator.ParamEngine;

@Service
@Transactional
public class ParamCreatorServiceImpl implements ParamCreatorService{
	private final Logger log = LoggerFactory.getLogger(ParamCreatorServiceImpl.class);

	@Autowired
	private CascaderService cascaderService;
	
	@Autowired
	private CascaderMapper cascaderMapper;
	
	@Autowired
	private CascaderParamService cascaderParamService;
	
	@Autowired
	private CascaderParamMapper cascaderParamMapper;
	
	@Autowired
	private MalaService malaService;
	
	@Autowired
	private MalaMapper malaMapper;
	
	@Autowired
	private MalaParamService malaParamService;
	
	@Autowired
	private MalaParamMapper malaParamMapper;
	
	@Autowired
	private FieldService fieldService;
	
	@Autowired
	private FieldMapper fieldMapper;
	
	@Autowired
	private FieldAttributeService fieldAttributeService;
	
	@Autowired
	private FieldAttributeMapper fieldAttributeMapper;
	
	
	@Override
	public String extractData(MultipartFile[] files) {
		
		Map<String, Object> servicesMap = new HashMap<>();
		servicesMap.put("CascaderService", cascaderService);
		servicesMap.put("CascaderMapper", cascaderMapper);
		servicesMap.put("CascaderParamService", cascaderParamService);
		servicesMap.put("CascaderParamMapper", cascaderParamMapper);
		
		servicesMap.put("MalaService", malaService);
		servicesMap.put("MalaMapper", malaMapper);
		
		servicesMap.put("MalaParamService", malaParamService);
		servicesMap.put("MalaParamMapper", malaParamMapper);
		
		servicesMap.put("FieldService", fieldService);
		servicesMap.put("FieldMapper", fieldMapper);
		
		servicesMap.put("FieldAttributeService", fieldAttributeService);
		servicesMap.put("FieldAttributeMapper", fieldAttributeMapper);
		
		ParamEngine.setServicesMap(servicesMap);
		
		String result = "<ul>";
		
		log.debug("{} files", files.length );
		
		// we want field file to be handled after all cascade files
		List<ParamEngine> fieldParamEngines = new ArrayList<>();
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
            System.out.println(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
            ParamEngine pe = ParamEngine.getInstance(file);
            
            if(pe instanceof FieldParamEngine) {
            	fieldParamEngines.add(pe);
            	continue;
            }
            
            pe.performParsing();
            result += pe.announceCount();
            log.info(result);
        }
		
		for (ParamEngine pe : fieldParamEngines) {
			pe.performParsing();
            result += pe.announceCount();
            log.info(result);
		}
		
		
		result += "</ul>";
		
		return result;
	}

}
