package com.neemshade.matri.service;

import org.springframework.web.multipart.MultipartFile;

public interface ParamCreatorService {

	public String extractData(MultipartFile[] files);
}
