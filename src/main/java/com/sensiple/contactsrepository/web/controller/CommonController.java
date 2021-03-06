package com.sensiple.contactsrepository.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sensiple.contactsrepository.dao.CommonDAO;
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
import com.sensiple.contactsrepository.utils.ContactsSession;

import net.sf.json.JSONObject;

 /**
 * This Class is used for getting all the meta table data from session
 * 
 *@author marshallv
 *
 */

@RestController
@RequestMapping(value = "/common/")
 public class CommonController {
	
	private Logger LOGGER = Logger.getLogger(CommonController.class);
	 
	@Inject
	private CommonDAO commonDAO;
	
	
	/**
	 * This method is used to get the list of roles from session.
	 * 
	 * @return list of roles.
	 */
	@RequestMapping(value = "getRole", method = RequestMethod.GET)
	public @ResponseBody String getRole(HttpServletRequest request) {

		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		
		List<Role> roleList = new ArrayList<Role>();
		ContactsSession contactsSession;
		
		try {
			
			if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			roleList = contactsSession.getRoleList();
	 		}
	 		else{
	 			roleList = commonDAO.getRole();
	 		}

		} catch (Exception e) {

			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}
		contactObject.put("roleList", roleList);
 		return new Gson().toJson(contactObject);
	}

	/*
	 * This method is used to get the list of country from session. 
	 */
 	@RequestMapping(value = "getCountryList", method = RequestMethod.GET)
	public @ResponseBody String getCountryList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		
 		List<CountryDetails> countryDetailslist = null;
 		ContactsSession contactsSession;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			countryDetailslist=contactsSession.getCountryList();
	 		}
	 		else{
				countryDetailslist=commonDAO.getCountryDetails();
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCountryList method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		contactObject.put("countryList", countryDetailslist);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of contact status from session. 
	 */
 	@RequestMapping(value = "getContactStatus", method = RequestMethod.GET)
	public @ResponseBody String getContactStatus(HttpServletRequest request) {
 		
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		
 		List<ContactStatus> contactStatuslist = null;
 		ContactsSession contactsSession;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 		contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 		contactStatuslist=contactsSession.getContactStatuslist();
	 		}
	 		else{
				contactStatuslist=commonDAO.getStatus();
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getContactStatus method"+ExceptionUtils.getStackTrace(e));
		}
 		contactObject.put("contactStatuslist", contactStatuslist);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of revenues from session. 
	 */
 	@RequestMapping(value = "getRevenueList", method = RequestMethod.GET)
	public @ResponseBody String getRevenueList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<RevenueDetails> revenueDetailslist = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			revenueDetailslist=contactsSession.getRevenueDetailslist();
	 		}
	 		else{
				revenueDetailslist=commonDAO.getRevenueDetails();
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getRevenueList method"+ExceptionUtils.getStackTrace(e));
		}
 		contactObject.put("revenueDetailsList", revenueDetailslist);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of company sizes from session. 
	 */
 	@RequestMapping(value = "getCompanySizeList", method = RequestMethod.GET)
	public @ResponseBody String getCompanySizeList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CompanySizeDetails> companySizeDetails = null;
 		
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			companySizeDetails=contactsSession.getCompanySizeDetails();
	 		}
	 		else{
	 			
					companySizeDetails=commonDAO.getCompanySizeDetails();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCompanySizeList method"+ExceptionUtils.getStackTrace(e));
		}
 		contactObject.put("companySizeDetails", companySizeDetails);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of industries from session. 
	 */
 	@RequestMapping(value = "getIndustryList", method = RequestMethod.GET)
	public @ResponseBody String getIndustryList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<IndustryDetails> industryDetails = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			industryDetails=contactsSession.getIndustryDetails();
	 		}
	 		else{
					industryDetails=commonDAO.getIndustryDetails();
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getIndustryList method"+ExceptionUtils.getStackTrace(e));
		}
 		contactObject.put("industryDetails", industryDetails);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of job title from session. 
	 */
 	@RequestMapping(value = "getJobTitleList", method = RequestMethod.GET)
	public @ResponseBody String getJobTitleList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<JobTitle> jobTitleList = null;
 		
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			jobTitleList=contactsSession.getJobTitleList();
	 		}
	 		else{
	 			
					jobTitleList=commonDAO.getJobTitleList();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getJobTitleList method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		contactObject.put("jobTitleList", jobTitleList);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of job functions from session. 
	 */
 	@RequestMapping(value = "getJobFunctionList", method = RequestMethod.GET)
	public @ResponseBody String getJobFunctionList(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<JobFunction> jobFunctionList = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			jobFunctionList=contactsSession.getJobFunctionList();
	 		}
	 		else{
	 			
					jobFunctionList=commonDAO.getJobFunctionList();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getJobFunctionList method"+ExceptionUtils.getStackTrace(e));
		}
 		contactObject.put("jobFunctionList", jobFunctionList);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of contact service offerings  from session. 
	 */
 	@RequestMapping(value = "getContactServiceOfferingList", method = RequestMethod.GET)
	public @ResponseBody String getContactServiceOffering(HttpServletRequest request) {
 		JSONObject contactObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<ContactServiceOffering> serviceOfferingList = null;
 		 try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			serviceOfferingList = contactsSession.getContactServiceOfferingList();
	 		}
	 		else{
	 			
	 			serviceOfferingList = commonDAO.getContactServiceOffering();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getContactServiceOffering method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		contactObject.put("serviceOfferingList", serviceOfferingList);
 		return new Gson().toJson(contactObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of campaign status from session. 
	 */
 	@RequestMapping(value = "getCampaignStatus", method = RequestMethod.GET)
	public @ResponseBody String getCampaignStatus(HttpServletRequest request) {
 		JSONObject campaignObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CampaignStatus> campaignStatusList = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			campaignStatusList = contactsSession.getCampaignStatusList();
	 		}
	 		else{
	 			
					campaignStatusList = commonDAO.getCampaignStatus();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCampaignStatus method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		campaignObject.put("campaignStatusList", campaignStatusList);
 		return new Gson().toJson(campaignObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of campaign types from session. 
	 */
 	@RequestMapping(value = "getCampaignType", method = RequestMethod.GET)
	public @ResponseBody String getCampaignType(HttpServletRequest request) {
 		JSONObject campaignObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CampaignType> campaignTypeList = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			campaignTypeList = contactsSession.getCampaignTypeList();
	 		}
	 		else{
	 			
					campaignTypeList = commonDAO.getCampaignType();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCampaignType method"+ExceptionUtils.getStackTrace(e));
 			}
 		
 		campaignObject.put("campaignTypeList", campaignTypeList);
 		return new Gson().toJson(campaignObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of campaign business units from session. 
	 */
 	@RequestMapping(value = "getCampaignBusinessUnit", method = RequestMethod.GET)
	public @ResponseBody String getCampaignBusinessUnit(HttpServletRequest request) {
 		JSONObject campaignObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CampaignBusinessUnit> campaignBusinessUnitList = null;
 		
 		try {
			if(session.getAttribute("contactsSession")!=null){
				contactsSession=(ContactsSession)session.getAttribute("contactsSession");
				campaignBusinessUnitList = contactsSession.getCampaignBusinessUnitList();
			}
			else{
				campaignBusinessUnitList = commonDAO.getCampaignBusinessUnit();
			}
		} catch (Exception e) {
			LOGGER.error("Exception in getCampaignBusinessUnit method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		campaignObject.put("campaignBusinessUnitList", campaignBusinessUnitList);
 		return new Gson().toJson(campaignObject);
 		
 	}
 	
 	/*
	 * This method is used to get the list of campaign regions from session. 
	 */
 	@RequestMapping(value = "getCampaignRegion", method = RequestMethod.GET)
	public @ResponseBody String getCampaignRegion(HttpServletRequest request) {
 		JSONObject campaignObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CampaignRegion> campaignRegionList = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			campaignRegionList = contactsSession.getCampaignRegionList();
	 		}
	 		else{
	 			
					campaignRegionList = commonDAO.getCampaignRegion();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCampaignRegion method"+ExceptionUtils.getStackTrace(e));
		}
 		
 		campaignObject.put("campaignRegionList", campaignRegionList);
 		return new Gson().toJson(campaignObject);
 		
 	}
 	
 	
 	/*
	 * This method is used to get the list of campaign category from session. 
	 */
 	@RequestMapping(value = "getCampaignCategory", method = RequestMethod.GET)
	public @ResponseBody String getCampaignCategory(HttpServletRequest request) {
 		JSONObject campaignObject = new JSONObject();
 		HttpSession session=request.getSession();
 		ContactsSession contactsSession;
 		List<CampaignCategory> campaignCategoryList = null;
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			campaignCategoryList = contactsSession.getCampaignCategoryList();
	 		}
	 		else{
	 			
	 			campaignCategoryList = commonDAO.getCampaignCategory();
				
	 		}
 		} catch (Exception e) {
 			LOGGER.error("Exception in getCampaignType method"+ExceptionUtils.getStackTrace(e));
 			}
 		
 		campaignObject.put("campaignCategoryList", campaignCategoryList);
 		return new Gson().toJson(campaignObject);
 		
 	}
 	
}
