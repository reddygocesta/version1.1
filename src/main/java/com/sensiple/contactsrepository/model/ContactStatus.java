package com.sensiple.contactsrepository.model;

import java.util.List;

public class ContactStatus {

	private int id;
	private String status;
	private List<CampaignContactStatus> campaignContactStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CampaignContactStatus> getCampaignContactStatus() {
		return campaignContactStatus;
	}
	public void setCampaignContactStatus(List<CampaignContactStatus> campaignContactStatus) {
		this.campaignContactStatus = campaignContactStatus;
	}
	
}
