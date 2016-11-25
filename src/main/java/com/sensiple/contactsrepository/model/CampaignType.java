package com.sensiple.contactsrepository.model;

public class CampaignType {
	
	private int id;
	
	private String campaignTypeName;


	/**
	 * @return the campaignTypeName
	 */
	public String getCampaignTypeName() {
		return campaignTypeName;
	}

	/**
	 * @param campaignTypeName the campaignTypeName to set
	 */
	public void setCampaignTypeName(String campaignTypeName) {
		this.campaignTypeName = campaignTypeName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}

