package com.neemshade.matri.service.paramCreator;

import org.springframework.web.multipart.MultipartFile;

public class CityParamEngine extends ParamEngine {

	private CascadeParamEngine cpe;
	private int parentIndex = 2;
	private int childIndex = 1;
	
	public CityParamEngine(MultipartFile file) {
		super(file);
	}

	@Override
	public void preOperation() {
		cpe = new CascadeParamEngine(file);
	}

	@Override
	public void postOperation() {
		
	}

	@Override
	public void parseLine(String line) {
		
		// if the line has hyhen, treat it as a line that has cascade name and other cascade params
		// else, consider it as a comma separated line
		
		if(line.indexOf('-') >= 0) {
			cpe.parseLine(line);
			return;
		}
		
		String[] words = line.split(",");
		String fomedLine = String.format("%s [%s]", words[parentIndex], words[childIndex]);
		cpe.parseLine(fomedLine);
	}

	@Override
	public String announceCount() {
		return cpe.announceCount();
	}

	
	
	public CascadeParamEngine getCpe() {
		return cpe;
	}

	public void setCpe(CascadeParamEngine cpe) {
		this.cpe = cpe;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public int getChildIndex() {
		return childIndex;
	}

	public void setChildIndex(int childIndex) {
		this.childIndex = childIndex;
	}
	
	

}
