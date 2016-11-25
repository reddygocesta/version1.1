package com.sensiple.contactsrepository.model;

import com.sensiple.contactsrepository.model.CampaignDetails;

public class CampaignDetails {

	private long campaignId;
	
	private int serialNumber;

	private String campaignName;

	private CampaignCategory campaignCategory;

	private int number;

	private String description;

	private CampaignType campaignType;

	private String leadTime;

	private String contactSheetLimit;

	private User campaignOwner;

	private String plannedRunDate;

	private CampaignStatus campaignStatus;
	
	private String subject;

	private CampaignServiceOffering serviceOffering;
	
	private String leadTimeChangeReason;
	
	private String contactOpenedDate;
	
	private int contactLimit;
	
	private CompanyDetails company;
	
	private CampaignBusinessUnit businessUnit;
	
	private CampaignRegion campaignRegion;
	
	private String createdBy;
	
	private String createDate;
	
	private String updatedOn;
	
	private String actualRunDate;
	
	private int totalRecords;

	public long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(long campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CampaignType getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(CampaignType campaignType) {
		this.campaignType = campaignType;
	}

	public String getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}

	public String getContactSheetLimit() {
		return contactSheetLimit;
	}

	public void setContactSheetLimit(String contactSheetLimit) {
		this.contactSheetLimit = contactSheetLimit;
	}

	public String getPlannedRunDate() {
		return plannedRunDate;
	}

	public void setPlannedRunDate(String plannedRunDate) {
		this.plannedRunDate = plannedRunDate;
	}

	public CampaignStatus getCampaignStatus() {
		return campaignStatus;
	}

	public void setCampaignStatus(CampaignStatus campaignStatus) {
		this.campaignStatus = campaignStatus;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public CampaignServiceOffering getServiceOffering() {
		return serviceOffering;
	}

	public void setServiceOffering(CampaignServiceOffering serviceOffering) {
		this.serviceOffering = serviceOffering;
	}

	public String getLeadTimeChangeReason() {
		return leadTimeChangeReason;
	}

	public void setLeadTimeChangeReason(String leadTimeChangeReason) {
		this.leadTimeChangeReason = leadTimeChangeReason;
	}

	public String getContactOpenedDate() {
		return contactOpenedDate;
	}

	public void setContactOpenedDate(String contactOpenedDate) {
		this.contactOpenedDate = contactOpenedDate;
	}

	

	public CompanyDetails getCompany() {
		return company;
	}

	public void setCompany(CompanyDetails company) {
		this.company = company;
	}

	public CampaignBusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(CampaignBusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the owner
	 */
	public User getcampaignOwner() {
		return campaignOwner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setcampaignOwner(User campaignOwner) {
		this.campaignOwner = campaignOwner;
	}

	/**
	 * @return the campaignCategory
	 */
	public CampaignCategory getCampaignCategory() {
		return campaignCategory;
	}

	/**
	 * @param campaignCategory the campaignCategory to set
	 */
	public void setCampaignCategory(CampaignCategory campaignCategory) {
		this.campaignCategory = campaignCategory;
	}

	public User getCampaignOwner() {
		return campaignOwner;
	}

	public void setCampaignOwner(User campaignOwner) {
		this.campaignOwner = campaignOwner;
	}

	public int getContactLimit() {
		return contactLimit;
	}

	public void setContactLimit(int contactLimit) {
		this.contactLimit = contactLimit;
	}

	public CampaignRegion getCampaignRegion() {
		return campaignRegion;
	}

	public void setCampaignRegion(CampaignRegion campaignRegion) {
		this.campaignRegion = campaignRegion;
	}

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the serialNumber
	 */
	public int getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the actualRunDate
	 */
	public String getActualRunDate() {
		return actualRunDate;
	}

	/**
	 * @param actualRunDate the actualRunDate to set
	 */
	public void setActualRunDate(String actualRunDate) {
		this.actualRunDate = actualRunDate;
	}

	
	

	

	
}
	
	
   	