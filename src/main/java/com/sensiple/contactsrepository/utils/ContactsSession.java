package com.sensiple.contactsrepository.utils;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignBusinessUnit;
import com.sensiple.contactsrepository.model.CampaignCategory;
import com.sensiple.contactsrepository.model.CampaignRegion;
import com.sensiple.contactsrepository.model.CampaignStatus;
import com.sensiple.contactsrepository.model.CampaignType;
import com.sensiple.contactsrepository.model.CompanySizeDetails;
import com.sensiple.contactsrepository.model.ContactServiceOffering;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.IndustryDetails;
import com.sensiple.contactsrepository.model.JobFunction;
import com.sensiple.contactsrepository.model.JobTitle;
import com.sensiple.contactsrepository.model.RevenueDetails;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.StateDetails;
import com.sensiple.contactsrepository.model.User;

/**
 * This Class is used for putting all the meta table data to session
 *
 * @author marshallv
 *
 */
public class ContactsSession {
	
	private List<Role> roleList;

	private List<CountryDetails> countryList;
	
	private List<StateDetails> stateList;

	public List<StateDetails> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateDetails> stateList) {
		this.stateList = stateList;
	}

	private List<ContactStatus> contactStatuslist;

	private List<RevenueDetails> revenueDetailslist;

	private List<CompanySizeDetails> companySizeDetails;

	private List<IndustryDetails> industryDetails;

	private List<JobTitle> jobTitleList;

	private List<JobFunction> jobFunctionList;

	private List<ContactServiceOffering> contactServiceOfferingList;

	private List<CampaignStatus> campaignStatusList;

	private List<CampaignType> campaignTypeList;

	private List<CampaignBusinessUnit> campaignBusinessUnitList;

	private List<CampaignRegion> campaignRegionList;
	
	private User user;
	
	private List<CampaignCategory> campaignCategoryList;

	public List<ContactStatus> getContactStatuslist() {
		return contactStatuslist;
	}

	public void setContactStatuslist(List<ContactStatus> contactStatuslist) {
		this.contactStatuslist = contactStatuslist;
	}

	public List<RevenueDetails> getRevenueDetailslist() {
		return revenueDetailslist;
	}

	public void setRevenueDetailslist(List<RevenueDetails> revenueDetailslist) {
		this.revenueDetailslist = revenueDetailslist;
	}

	public List<CompanySizeDetails> getCompanySizeDetails() {
		return companySizeDetails;
	}

	public void setCompanySizeDetails(List<CompanySizeDetails> companySizeDetails) {
		this.companySizeDetails = companySizeDetails;
	}

	public List<IndustryDetails> getIndustryDetails() {
		return industryDetails;
	}

	public void setIndustryDetails(List<IndustryDetails> industryDetails) {
		this.industryDetails = industryDetails;
	}

	public List<JobTitle> getJobTitleList() {
		return jobTitleList;
	}

	public void setJobTitleList(List<JobTitle> jobTitleList) {
		this.jobTitleList = jobTitleList;
	}

	public List<JobFunction> getJobFunctionList() {
		return jobFunctionList;
	}

	public void setJobFunctionList(List<JobFunction> jobFunctionList) {
		this.jobFunctionList = jobFunctionList;
	}

	public List<ContactServiceOffering> getContactServiceOfferingList() {
		return contactServiceOfferingList;
	}

	public void setContactServiceOfferingList(List<ContactServiceOffering> contactServiceOfferingList) {
		this.contactServiceOfferingList = contactServiceOfferingList;
	}

	public List<CountryDetails> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDetails> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the campaignStatusList
	 */
	public List<CampaignStatus> getCampaignStatusList() {
		return campaignStatusList;
	}

	/**
	 * @param campaignStatusList
	 *            the campaignStatusList to set
	 */
	public void setCampaignStatusList(List<CampaignStatus> campaignStatusList) {
		this.campaignStatusList = campaignStatusList;
	}

	/**
	 * @return the campaignTypeList
	 */
	public List<CampaignType> getCampaignTypeList() {
		return campaignTypeList;
	}

	/**
	 * @param campaignTypeList
	 *            the campaignTypeList to set
	 */
	public void setCampaignTypeList(List<CampaignType> campaignTypeList) {
		this.campaignTypeList = campaignTypeList;
	}

	/**
	 * @return the campaignBusinessUnitList
	 */
	public List<CampaignBusinessUnit> getCampaignBusinessUnitList() {
		return campaignBusinessUnitList;
	}

	/**
	 * @param campaignBusinessUnitList
	 *            the campaignBusinessUnitList to set
	 */
	public void setCampaignBusinessUnitList(List<CampaignBusinessUnit> campaignBusinessUnitList) {
		this.campaignBusinessUnitList = campaignBusinessUnitList;
	}

	/**
	 * @return the campaignRegionList
	 */
	public List<CampaignRegion> getCampaignRegionList() {
		return campaignRegionList;
	}

	/**
	 * @param campaignRegionList
	 *            the campaignRegionList to set
	 */
	public void setCampaignRegionList(List<CampaignRegion> campaignRegionList) {
		this.campaignRegionList = campaignRegionList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<CampaignCategory> getCampaignCategoryList() {
		return campaignCategoryList;
	}

	public void setCampaignCategoryList(List<CampaignCategory> campaignCategoryList) {
		this.campaignCategoryList = campaignCategoryList;
	}

}
