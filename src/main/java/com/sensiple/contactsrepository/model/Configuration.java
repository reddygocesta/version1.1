package com.sensiple.contactsrepository.model;

public class Configuration {
	
	private long configurationId;
	private long userId;
	private int campaignLeadTime;
	private DaysType campaignLeadTimeType;
	private int allowAssignContacts;
	private DaysType allowAssignContactsType;
	private int deleteThreshold;
	private DaysType deleteThresholdType;
	private int saveSearchThreshold;
	private DaysType saveSearchThresholdType;
	private int maximumCompaignsAllowed;
	private String contactAvailability;
	private ContactStatus contactStatus;
	private CampaignContactStatus campaignContactStatus;
	
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getCampaignLeadTime() {
		return campaignLeadTime;
	}
	public void setCampaignLeadTime(int campaignLeadTime) {
		this.campaignLeadTime = campaignLeadTime;
	}
	public DaysType getCampaignLeadTimeType() {
		return campaignLeadTimeType;
	}
	public void setCampaignLeadTimeType(DaysType campaignLeadTimeType) {
		this.campaignLeadTimeType = campaignLeadTimeType;
	}
	public int getAllowAssignContacts() {
		return allowAssignContacts;
	}
	public void setAllowAssignContacts(int allowAssignContacts) {
		this.allowAssignContacts = allowAssignContacts;
	}
	public DaysType getAllowAssignContactsType() {
		return allowAssignContactsType;
	}
	public void setAllowAssignContactsType(DaysType allowAssignContactsType) {
		this.allowAssignContactsType = allowAssignContactsType;
	}
	public int getDeleteThreshold() {
		return deleteThreshold;
	}
	public void setDeleteThreshold(int deleteThreshold) {
		this.deleteThreshold = deleteThreshold;
	}
	public DaysType getDeleteThresholdType() {
		return deleteThresholdType;
	}
	public void setDeleteThresholdType(DaysType deleteThresholdType) {
		this.deleteThresholdType = deleteThresholdType;
	}
	public int getSaveSearchThreshold() {
		return saveSearchThreshold;
	}
	public void setSaveSearchThreshold(int saveSearchThreshold) {
		this.saveSearchThreshold = saveSearchThreshold;
	}
	public DaysType getSaveSearchThresholdType() {
		return saveSearchThresholdType;
	}
	public void setSaveSearchThresholdType(DaysType saveSearchThresholdType) {
		this.saveSearchThresholdType = saveSearchThresholdType;
	}
	public int getMaximumCompaignsAllowed() {
		return maximumCompaignsAllowed;
	}
	public void setMaximumCompaignsAllowed(int maximumCompaignsAllowed) {
		this.maximumCompaignsAllowed = maximumCompaignsAllowed;
	}
	public String getContactAvailability() {
		return contactAvailability;
	}
	public void setContactAvailability(String contactAvailability) {
		this.contactAvailability = contactAvailability;
	}
	public ContactStatus getContactStatus() {
		return contactStatus;
	}
	public void setContactStatus(ContactStatus contactStatus) {
		this.contactStatus = contactStatus;
	}
	public long getConfigurationId() {
		return configurationId;
	}
	public void setConfigurationId(long configurationId) {
		this.configurationId = configurationId;
	}
	public CampaignContactStatus getCampaignContactStatus() {
		return campaignContactStatus;
	}
	public void setCampaignContactStatus(CampaignContactStatus campaignContactStatus) {
		this.campaignContactStatus = campaignContactStatus;
	}
	
	
	
}
