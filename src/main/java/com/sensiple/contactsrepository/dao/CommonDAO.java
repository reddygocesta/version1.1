package com.sensiple.contactsrepository.dao;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignBusinessUnit;
import com.sensiple.contactsrepository.model.CampaignCategory;
import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.CampaignRegion;
import com.sensiple.contactsrepository.model.CampaignStatus;
import com.sensiple.contactsrepository.model.CampaignType;
import com.sensiple.contactsrepository.model.CompanySizeDetails;
import com.sensiple.contactsrepository.model.ContactServiceOffering;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.IndustryDetails;
import com.sensiple.contactsrepository.model.JobFunction;
import com.sensiple.contactsrepository.model.JobTitle;
import com.sensiple.contactsrepository.model.RevenueDetails;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.StateDetails;


public interface CommonDAO {
	
	List<Role> getRole() throws Exception;
	
	List<ContactStatus> getStatus() throws Exception;
	
	List<DaysType> getDaysType() throws Exception;
	
	List<CampaignContactStatus> getStatusMappings(int id) throws Exception;
	
	List<CountryDetails> getCountryDetails() throws Exception;
	
	List<StateDetails> getStateDetails() throws Exception;
	
	List<RevenueDetails> getRevenueDetails() throws Exception;
	
	List<CompanySizeDetails> getCompanySizeDetails() throws Exception;
	
	List<IndustryDetails> getIndustryDetails() throws Exception;
	
	List<JobTitle> getJobTitleList() throws Exception;
	
	List<JobFunction> getJobFunctionList() throws Exception;
	
	List<ContactServiceOffering> getContactServiceOffering() throws Exception;

	List<CampaignStatus> getCampaignStatus() throws Exception;

	List<CampaignType> getCampaignType() throws Exception;

	List<CampaignBusinessUnit> getCampaignBusinessUnit() throws Exception;

	List<CampaignRegion> getCampaignRegion() throws Exception;

	List<CampaignCategory> getCampaignCategory()throws Exception;

}
