package com.sensiple.contactsrepository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sensiple.contactsrepository.dao.CommonDAO;
import com.sensiple.contactsrepository.mapper.CampaignBusinessUnitRowMapper;
import com.sensiple.contactsrepository.mapper.CampaignCategoryRowMapper;
import com.sensiple.contactsrepository.mapper.CampaignContactStatusMapper;
import com.sensiple.contactsrepository.mapper.CampaignRegionRowMapper;
import com.sensiple.contactsrepository.mapper.CampaignStatusRowMapper;
import com.sensiple.contactsrepository.mapper.CampaignTypeRowMapper;
import com.sensiple.contactsrepository.mapper.CompanySizeDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.ContactServiceOfferingRowMapper;
import com.sensiple.contactsrepository.mapper.ContactsStatusMapper;
import com.sensiple.contactsrepository.mapper.CountryDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.DaysTypeMapper;
import com.sensiple.contactsrepository.mapper.IndustryDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.JobFunctionRowMapper;
import com.sensiple.contactsrepository.mapper.JobTitleRowMapper;
import com.sensiple.contactsrepository.mapper.RevenueDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.RoleDetailsMapper;
import com.sensiple.contactsrepository.mapper.SicCodeDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.StateDetailsRowMapper;
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
import com.sensiple.contactsrepository.model.SicCodeDetails;
import com.sensiple.contactsrepository.model.StateDetails;
import com.sensiple.contactsrepository.utils.SqlConstant;
import com.sensiple.contactsrepository.web.controller.ConfigurationController;

/**
 * This Class is used for getting all the meta table data
 * 
 *@author marshallv
 *
 */

@Repository
public class CommonDAOImpl implements CommonDAO{
	private Logger LOGGER = Logger.getLogger(ConfigurationController.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * This method is used to get the list of roles.
	 * 
	 * @return list of roles.
	 */
	@Override
	public List<Role> getRole() {

		List<Role> roleList = new ArrayList<Role>();
		try{
		roleList = jdbcTemplate.query(SqlConstant.SQL_ROLE_LIST, new RoleDetailsMapper());
		}catch(Exception e){
			LOGGER.error("Exception in getRole method "+ExceptionUtils.getStackTrace(e));
		}
		return roleList;
	}
	
	/**
	 * This method is used to get the list of Contact Status.
	 * 
	 * @return list of ContactStatus.
	 */
	@Override
	public List<ContactStatus> getStatus() 
	{
		LOGGER.info("DAO getstatus method invoked");
		List<ContactStatus> statusList = new ArrayList<ContactStatus>();
		try
		{
			statusList=jdbcTemplate.query(SqlConstant.SQL_GET_CONTACT_STATUS, new ContactsStatusMapper());
		
			} catch(Exception e)
		{
				LOGGER.error("Exception in getStatus method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
		 return statusList;
		
		
	}
	
	/**
	 * This method is used to get the list of RevenueDetails.
	 * 
	 * @return list of RevenueDetails.
	 */
	@Override
	public List<RevenueDetails> getRevenueDetails() 
	{
		LOGGER.info("DAO getRevenueDetails method invoked");
		List<RevenueDetails> revenueList=new ArrayList<RevenueDetails>();
		try
		{
			revenueList=jdbcTemplate.query(SqlConstant.SQL_GET_REVENUE_DETAILS, new RevenueDetailsRowMapper());
		
			} catch(Exception e)
		{
				LOGGER.error("Exception in getRevenueDetails method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
		 return revenueList;
		
		
	}
	
	/**
	 * This method is used to get the list of DaysType.
	 * 
	 * @return list of DaysType.
	 */
	@Override
	public List<DaysType> getDaysType()
	{
		LOGGER.info("DAO get days method invoked");
		List<DaysType> daysList=new ArrayList<DaysType>();
		try
		{
			daysList=jdbcTemplate.query(SqlConstant.SQL_GET_DAYS_TYPE, new DaysTypeMapper());

		} catch(Exception e)
		{
			LOGGER.error("Exception in getDaysType method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
		 return daysList;
	
	}
	
	/**
	 * This method is used to get the list of Campaign Contact Status.
	 * 
	 * @return list of CampaignContactStatus.
	 */
	@Override
	public List<CampaignContactStatus> getStatusMappings(int id)
	{
		LOGGER.info("DAO getstatus mapping method invoked");
		List<CampaignContactStatus> mappingList=new ArrayList<CampaignContactStatus>();
		
		try
		{
			mappingList=jdbcTemplate.query(SqlConstant.SQL_GET_STATUS_MAPPING, new CampaignContactStatusMapper(),id);
		} catch(Exception e)
		{
			LOGGER.error("Exception in getStatusMappings method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
		 return mappingList;
	}


	/**
	 * This method is used to get the list of CountryDetails.
	 * 
	 * @return list of CountryDetails.
	 */
	@Override
	public List<CountryDetails> getCountryDetails() {
		
		List<CountryDetails> countryList = new ArrayList<CountryDetails>();
		LOGGER.info("DAO getCountryDetails invoked");
		try
		{
			countryList=jdbcTemplate.query(SqlConstant.SQL_GET_COUNTRY_DETAILS, new CountryDetailsRowMapper());
			for (CountryDetails countryDetails : countryList) {
			List<StateDetails> stateList=new ArrayList<StateDetails>();
			stateList=jdbcTemplate.query(SqlConstant.SQL_GET_STATE_DETAILS, new StateDetailsRowMapper(),countryDetails.getCountryId());
			countryDetails.setStates(stateList);
			}
			

		} catch(Exception e)
		{
			LOGGER.error("Exception in getCountryDetails method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
	
		
		return countryList;
	}

	/**
	 * This method is used to get the list of CompanySizeDetails.
	 * 
	 * @return list of CompanySizeDetails.
	 */
	@Override
	public List<CompanySizeDetails> getCompanySizeDetails() {
		LOGGER.info("DAO getCompanySizeDetails method invoked");
		List<CompanySizeDetails> companySizeList=new ArrayList<CompanySizeDetails>();
		try
		{
			companySizeList=jdbcTemplate.query(SqlConstant.SQL_GET_COMPANY_SIZE_DETAILS,new CompanySizeDetailsRowMapper());
		
			} catch(Exception e)
		{
				LOGGER.error("Exception in getCompanySizeDetails method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
		 return companySizeList;
	}

	/**
	 * This method is used to get the list of IndustryDetails.
	 * 
	 * @return list of IndustryDetails.
	 */
	@Override
	public List<IndustryDetails> getIndustryDetails() {
		
		List<IndustryDetails> industryDetailsList = new ArrayList<IndustryDetails>();
		LOGGER.info("DAO getIndustryDetails invoked");
		try
		{
			industryDetailsList=jdbcTemplate.query(SqlConstant.SQL_GET_INDUSTRY_DETAILS, new IndustryDetailsRowMapper());
			for (IndustryDetails industryDetails : industryDetailsList) {
			List<SicCodeDetails> SicCodeDetailsList=new ArrayList<SicCodeDetails>();
			SicCodeDetailsList=jdbcTemplate.query(SqlConstant.SQL_GET_SIC_CODE_DETAILS, new SicCodeDetailsRowMapper(),industryDetails.getIndustryId());
			industryDetails.setSicCodeDetailsList(SicCodeDetailsList);
			}
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getIndustryDetails method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
	
		
		return industryDetailsList;
	}

	/**
	 * This method is used to get the list of JobTitle.
	 * 
	 * @return list of JobTitle.
	 */
	@Override
	public List<JobTitle> getJobTitleList() {
		
		List<JobTitle> jobTitleList = new ArrayList<JobTitle>();
		LOGGER.info("DAO getJobTitleList invoked");
		try
		{
			jobTitleList=jdbcTemplate.query(SqlConstant.SQL_GET_JOB_TITLE_DETAILS, new JobTitleRowMapper());
			/*for (JobTitle jobTitle : jobTitleList) {
			List<JobFunction> jobFunctionList=new ArrayList<JobFunction>();
			jobFunctionList=jdbcTemplate.query(SqlConstant.SQL_GET_JOB_FUNCTION_DETAILS_TITLEWISE, new JobFunctionRowMapper(),jobTitle.getJobTitleId());
			jobTitle.setJobFunction(jobFunctionList.get(0));
			}*/
			

		} catch(Exception e)
		{
			LOGGER.error("Exception in getJobTitleList method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
	
		
		return jobTitleList;
	}

	/**
	 * This method is used to get the list of JobFunction.
	 * 
	 * @return list of JobFunction.
	 */
	@Override
	public List<JobFunction> getJobFunctionList() {
		
		List<JobFunction> jobFunction = new ArrayList<JobFunction>();
		LOGGER.info("DAO getJobFunctionList invoked");
		try
		{
			jobFunction=jdbcTemplate.query(SqlConstant.SQL_GET_JOB_FUNCTION_DETAILS, new JobFunctionRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getJobFunctionList method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
	
		
		return jobFunction;
	}

	/**
	 * This method is used to get the list of service offerings.
	 * 
	 * @return list of ServiceOfferings.
	 */
	@Override
	public List<ContactServiceOffering> getContactServiceOffering() {
		
		List<ContactServiceOffering> contactServiceOfferings = new ArrayList<ContactServiceOffering>();
		String serviceOffering = "";
		LOGGER.info("DAO getContactServiceOffering invoked");
		try
		{
			contactServiceOfferings=jdbcTemplate.query(SqlConstant.SQL_GET_SERVICE_OFFERING_DETAILS, new ContactServiceOfferingRowMapper(), serviceOffering);
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
	
		
		return contactServiceOfferings;
	}

	
	/**
	 * This method is used to get the list of campaign status.
	 * 
	 * @return list of campaignStatus.
	 */
	@Override
	public List<CampaignStatus> getCampaignStatus() throws Exception {
		List<CampaignStatus> campaignStatusList = new ArrayList<CampaignStatus>();
		LOGGER.info("DAO getContactServiceOffering invoked");
		try
		{
			campaignStatusList = jdbcTemplate.query(SqlConstant.SQL_GET_CAMPAIGN_STATUS, new CampaignStatusRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
	
		
		return campaignStatusList;
	}

	
	/**
	 * This method is used to get the list of campaign type.
	 * 
	 * @return list of CampaignType.
	 */
	@Override
	public List<CampaignType> getCampaignType() throws Exception {
		List<CampaignType> campaignTypeList = new ArrayList<CampaignType>();
		LOGGER.info("DAO getContactServiceOffering invoked");
		try
		{
			campaignTypeList = jdbcTemplate.query(SqlConstant.SQL_GET_CAMPAIGN_TYPE, new CampaignTypeRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
		return campaignTypeList;
	}

	
	/**
	 * This method is used to get the list of campaign BusinessUnit.
	 * 
	 * @return list of campaignBusinessUnit.
	 */
	@Override
	public List<CampaignBusinessUnit> getCampaignBusinessUnit() throws Exception {
		List<CampaignBusinessUnit> campaignBusinessUnitList = new ArrayList<CampaignBusinessUnit>();
		LOGGER.info("DAO getContactServiceOffering invoked");
		try
		{
			campaignBusinessUnitList = jdbcTemplate.query(SqlConstant.SQL_GET_CAMPAIGN_BUSINESS_UNIT, new CampaignBusinessUnitRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 
		 
		return campaignBusinessUnitList;
	}

	
	/**
	 * This method is used to get the list of campaign regions.
	 * 
	 * @return list of campaignRegion.
	 */
	@Override
	public List<CampaignRegion> getCampaignRegion() throws Exception {
		List<CampaignRegion> campaignRegionList = new ArrayList<CampaignRegion>();
		LOGGER.info("DAO getContactServiceOffering invoked");
		try
		{
			campaignRegionList = jdbcTemplate.query(SqlConstant.SQL_GET_CAMPAIGN_REGION, new CampaignRegionRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		
		 
		return campaignRegionList;
	}

	@Override
	public List<CampaignCategory> getCampaignCategory() throws Exception {
		List<CampaignCategory> campaignCategoryList = new ArrayList<CampaignCategory>();
		LOGGER.info("DAO getCampaignCategory invoked");
		try
		{
			campaignCategoryList = jdbcTemplate.query(SqlConstant.SQL_GET_CAMPAIGN_CATEGORY, new CampaignCategoryRowMapper());
			
		} catch(Exception e)
		{
			LOGGER.error("Exception in getCampaignCategory method "+ExceptionUtils.getStackTrace(e));
		}
		return campaignCategoryList;
	}

	@Override
	public List<StateDetails> getStateDetails() throws Exception {
		
		List<StateDetails> stateList = new ArrayList<StateDetails>();
		LOGGER.info("DAO getCountryDetails invoked");
		try
		{
			stateList=jdbcTemplate.query(SqlConstant.SQL_GET_ALLSTATE_DETAILS, new StateDetailsRowMapper());

		} catch(Exception e)
		{
			LOGGER.error("Exception in getCountryDetails method "+ExceptionUtils.getStackTrace(e));
		}
		return stateList;
	}


}
