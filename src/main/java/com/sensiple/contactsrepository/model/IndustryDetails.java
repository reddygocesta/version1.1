package com.sensiple.contactsrepository.model;

import java.util.List;

public class IndustryDetails {
	
	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	private int industryId;
	
	private String industryName;
	
	private List<SicCodeDetails> sicCodeDetailsList;

	public List<SicCodeDetails> getSicCodeDetailsList() {
		return sicCodeDetailsList;
	}

	public void setSicCodeDetailsList(List<SicCodeDetails> sicCodeDetailsList) {
		this.sicCodeDetailsList = sicCodeDetailsList;
	}

}
