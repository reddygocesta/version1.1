package com.sensiple.contactsrepository.model;

import org.springframework.web.multipart.MultipartFile;

public class contactUpload {
	private String templateName;  
	MultipartFile file;
	
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
