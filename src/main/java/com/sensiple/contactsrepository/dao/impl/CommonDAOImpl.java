package com.sensiple.contactsrepository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sensiple.contactsrepository.dao.CommonDAO;
import com.sensiple.contactsrepository.mapper.CampaignBusinessUnitRowMapper;
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
import com.sensiple.contactsrepository.mapper.SicCodeDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.StateDetailsRowMapper;
import com.sensiple.contactsrepository.model.CampaignBusinessUnit;
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
import com.sensiple.contactsrepository.model.SicCodeDetails;
import com.sensiple.contactsrepository.model.StateDetails;

@Repository
public class CommonDAOImpl implements CommonDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ContactStatus> getStatus() 
	{
		System.out.println("--------------DAO getstatus method invoked--------------");
		List<ContactStatus> statusList = new ArrayList<ContactStatus>();
		try
		{
			statusList=jdbcTemplate.query("call GET_CONTACT_STATUS();",new ContactsStatusMapper());
		
			} catch(Exception e)
		{
				System.out.println("Exception in getStatus method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("status list "+statusList);
		 
		 return statusList;
		
		
	}
	
	@Override
	public List<RevenueDetails> getRevenueDetails() 
	{
		System.out.println("--------------DAO getRevenueDetails method invoked--------------");
		List<RevenueDetails> revenueList=new ArrayList<RevenueDetails>();
		try
		{
			revenueList=jdbcTemplate.query("call GET_REVENUE_DETAILS();",new RevenueDetailsRowMapper());
		
			} catch(Exception e)
		{
				System.out.println("Exception in getRevenueDetails method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("revenueList "+revenueList);
		 
		 return revenueList;
		
		
	}
	
	@Override
	public List<DaysType> getDaysType()
	{
		System.out.println("--------------DAO get days method invoked--------------");
		List<DaysType> daysList=new ArrayList<DaysType>();
		try
		{
			daysList=jdbcTemplate.query("call GET_DAYS_TYPE();", new DaysTypeMapper());

		} catch(Exception e)
		{
				System.out.println("Exception in getDaysType method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("daystypes list "+daysList);
		 
		 return daysList;
	
	}
	
	@Override
	public List<CampaignContactStatus> getStatusMappings(int id)
	{
		System.out.println("--------------DAO getstatus mapping method invoked--------------");
		List<CampaignContactStatus> mappingList=new ArrayList<CampaignContactStatus>();
		
		try
		{
			mappingList=jdbcTemplate.query("call GET_STATUS_MAPPING(?);", new CampaignContactStatusMapper(),id);
		} catch(Exception e)
		{
			System.out.println("Exception in getStatusMappings method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("Status mapping list "+mappingList);
		 
		 return mappingList;
	}


	@Override
	public List<CountryDetails> getCountryDetails() {
		
		List<CountryDetails> countryList = new ArrayList<CountryDetails>();
		System.out.println("--------------DAO getCountryDetails invoked--------------");
		try
		{
			countryList=jdbcTemplate.query("call GET_COUNTRY_DETAILS();", new CountryDetailsRowMapper());
			for (CountryDetails countryDetails : countryList) {
			List<StateDetails> stateList=new ArrayList<StateDetails>();
			stateList=jdbcTemplate.query("call GET_STATE_DETAILS(?);", new StateDetailsRowMapper(),countryDetails.getCountryId());
			countryDetails.setStates(stateList);
			}
			

		} catch(Exception e)
		{
				System.out.println("Exception in getCountryDetails method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("countryList list "+countryList);
		 
	
		
		return countryList;
	}

	@Override
	public List<CompanySizeDetails> getCompanySizeDetails() {
		System.out.println("--------------DAO getCompanySizeDetails method invoked--------------");
		List<CompanySizeDetails> companySizeList=new ArrayList<CompanySizeDetails>();
		try
		{
			companySizeList=jdbcTemplate.query("call GET_COMPANY_SIZE_DETAILS();",new CompanySizeDetailsRowMapper());
		
			} catch(Exception e)
		{
				System.out.println("Exception in getCompanySizeDetails method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("companySizeList "+companySizeList);
		 
		 return companySizeList;
	}

	@Override
	public List<IndustryDetails> getIndustryDetails() {
		
		List<IndustryDetails> industryDetailsList = new ArrayList<IndustryDetails>();
		System.out.println("--------------DAO getIndustryDetails invoked--------------");
		try
		{
			industryDetailsList=jdbcTemplate.query("call GET_INDUSTRY_DETAILS();", new IndustryDetailsRowMapper());
			for (IndustryDetails industryDetails : industryDetailsList) {
			List<SicCodeDetails> SicCodeDetailsList=new ArrayList<SicCodeDetails>();
			SicCodeDetailsList=jdbcTemplate.query("call GET_SIC_CODE_DETAILS(?);", new SicCodeDetailsRowMapper(),industryDetails.getIndustryId());
			industryDetails.setSicCodeDetailsList(SicCodeDetailsList);
			}
			
		} catch(Exception e)
		{
				System.out.println("Exception in getIndustryDetails method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("industryDetailsList list "+industryDetailsList);
		 
	
		
		return industryDetailsList;
	}

	@Override
	public List<JobTitle> getJobTitleList() {
		
		List<JobTitle> jobTitleList = new ArrayList<JobTitle>();
		System.out.println("--------------DAO getJobTitleList invoked--------------");
		try
		{
			jobTitleList=jdbcTemplate.query("call GET_JOB_TITLE_DETAILS();", new JobTitleRowMapper());
			for (JobTitle jobTitle : jobTitleList) {
			List<JobFunction> jobFunctionList=new ArrayList<JobFunction>();
			jobFunctionList=jdbcTemplate.query("call GET_JOB_FUNCTION_DETAILS_TITLEWISE(?);", new JobFunctionRowMapper(),jobTitle.getJobTitleId());
			jobTitle.setJobFunction(jobFunctionList.get(0));
			}
			

		} catch(Exception e)
		{
				System.out.println("Exception in getJobTitleList method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getJobTitleList list "+jobTitleList);
		 
	
		
		return jobTitleList;
	}

	@Override
	public List<JobFunction> getJobFunctionList() {
		
		List<JobFunction> jobFunction = new ArrayList<JobFunction>();
		System.out.println("--------------DAO getJobFunctionList invoked--------------");
		try
		{
			jobFunction=jdbcTemplate.query("call GET_JOB_FUNCTION_DETAILS();", new JobFunctionRowMapper());
			
		} catch(Exception e)
		{
				System.out.println("Exception in getJobFunctionList method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getJobFunctionList list "+jobFunction);
		 
	
		
		return jobFunction;
	}

	@Override
	public List<ContactServiceOffering> getContactServiceOffering() {
		
		List<ContactServiceOffering> contactServiceOfferings = new ArrayList<ContactServiceOffering>();
		String serviceOffering = "";
		System.out.println("--------------DAO getContactServiceOffering invoked--------------");
		try
		{
			contactServiceOfferings=jdbcTemplate.query("call GET_SERVICE_OFFERING_DETAILS(?);", new ContactServiceOfferingRowMapper(), serviceOffering);
			
		} catch(Exception e)
		{
				System.out.println("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getContactServiceOffering list "+contactServiceOfferings);
		 
	
		
		return contactServiceOfferings;
	}

	@Override
	public List<CampaignStatus> getCampaignStatus() throws Exception {
		List<CampaignStatus> campaignStatusList = new ArrayList<CampaignStatus>();
		System.out.println("--------------DAO getContactServiceOffering invoked--------------");
		try
		{
			campaignStatusList = jdbcTemplate.query("call GET_CAMPAIGN_STATUS();", new CampaignStatusRowMapper());
			
		} catch(Exception e)
		{
				System.out.println("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getContactServiceOffering list "+campaignStatusList);
		 
	
		
		return campaignStatusList;
	}

	@Override
	public List<CampaignType> getCampaignType() throws Exception {
		List<CampaignType> campaignTypeList = new ArrayList<CampaignType>();
		System.out.println("--------------DAO getContactServiceOffering invoked--------------");
		try
		{
			campaignTypeList = jdbcTemplate.query("call GET_CAMPAIGN_TYPE();", new CampaignTypeRowMapper());
			
		} catch(Exception e)
		{
				System.out.println("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getContactServiceOffering list "+campaignTypeList);
		 
		return campaignTypeList;
	}

	@Override
	public List<CampaignBusinessUnit> getCampaignBusinessUnit() throws Exception {
		List<CampaignBusinessUnit> campaignBusinessUnitList = new ArrayList<CampaignBusinessUnit>();
		System.out.println("--------------DAO getContactServiceOffering invoked--------------");
		try
		{
			campaignBusinessUnitList = jdbcTemplate.query("call GET_CAMPAIGN_BUSINESS_UNIT();", new CampaignBusinessUnitRowMapper());
			
		} catch(Exception e)
		{
				System.out.println("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getContactServiceOffering list "+campaignBusinessUnitList);
		 
		return campaignBusinessUnitList;
	}

	@Override
	public List<CampaignRegion> getCampaignRegion() throws Exception {
		List<CampaignRegion> campaignRegionList = new ArrayList<CampaignRegion>();
		System.out.println("--------------DAO getContactServiceOffering invoked--------------");
		try
		{
			campaignRegionList = jdbcTemplate.query("call GET_CAMPAIGN_REGION();", new CampaignRegionRowMapper());
			
		} catch(Exception e)
		{
				System.out.println("Exception in getContactServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		 System.out.println("getContactServiceOffering list "+campaignRegionList);
		 
		return campaignRegionList;
	}


}
