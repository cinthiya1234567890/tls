package com.neemshade.matri.service.paramCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * base class to handle param data files
 * @author Admin
 *
 */
public abstract class ParamEngine {
	protected MultipartFile file;
	
	protected static Map<String, Object> servicesMap;
	
	protected int successCount = 0;
	protected List<String> errorTokens = new ArrayList<>();
	
	public ParamEngine(MultipartFile file) {
		this.file = file;
	}
	
	
	/* this is a factory method.
	 * generates ParamEngine object based on the given file name
	 */
	public static ParamEngine getInstance(MultipartFile file) {
		
		String filename = file.getOriginalFilename();
		
		
		if(filename.startsWith("paramNames")) {
			ParamEngine pe = new FieldParamEngine(file);
			
			return pe;
		}
		
		
		// if it is a csv, it should be having city names
		if(filename.endsWith(".csv")) {
			ParamEngine pe = new CityParamEngine(file);
			
			return pe;
		}
		
		ParamEngine pe = new CascadeParamEngine(file);
		
		return pe;
	}

	abstract public void preOperation();
	
	abstract public void postOperation();
	
	/**
	 * single line from given source file is sent to this method
	 * this method need to parse the line and take appropriate action
	 * @param line
	 */
	abstract public void parseLine(String line);
	
	/**
	 * go through the available text file and perform operation on each line
	 * @return
	 */
	public void performParsing() {
		preOperation();
		
		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			BufferedReader br=new BufferedReader(reader);
			String line;
			
			while((line=br.readLine())!=null)  {
				line = line.trim();
				if(line.startsWith("#") || "".equals(line)) {
					continue;
				}
				
				parseLine(line);
			}
			
			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		postOperation();
		
		
	}
	

	/**
	 * gathers output values and compose a result string
	 * @return
	 */
	public String announceCount() {
		
		StringBuffer sb = new StringBuffer("");
		for (String token : errorTokens) {
			sb.append(token + ", ");
		}
		
		return String.format("<li>%s - Success = (%d), Error = (%d) %s</li>", 
				file.getOriginalFilename(), successCount, errorTokens.size(), sb.toString());
	}
	
	
	

	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}


	public static Map<String, Object> getServicesMap() {
		return servicesMap;
	}


	public static void setServicesMap(Map<String, Object> servicesMap) {
		ParamEngine.servicesMap = servicesMap;
	}


	public List<String> getErrorTokens() {
		return errorTokens;
	}


	public void setErrorTokens(List<String> errorTokens) {
		this.errorTokens = errorTokens;
	}

	
}
