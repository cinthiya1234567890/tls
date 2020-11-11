package com.neemshade.matri.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neemshade.matri.service.ParamCreatorService;
import com.neemshade.matri.service.dto.IssueDTO;
import com.neemshade.matri.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api/param_creator")
public class ParamCreatorResource {
	private final Logger log = LoggerFactory.getLogger(ParamCreatorResource.class);

    private static final String ENTITY_NAME = "issue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private ParamCreatorService paramCreatorService;
    
    public ParamCreatorResource(ParamCreatorService paramCreatorService) {
    	this.paramCreatorService = paramCreatorService;
    }
    
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam MultipartFile[] files) throws URISyntaxException {
        
        log.debug("REST request to save param files " + files.length);
        
        String result = paramCreatorService.extractData(files);;
        return ResponseEntity.created(new URI("//matriPages/techno/param-creator"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result))
            .body(result);
    }
    
}
