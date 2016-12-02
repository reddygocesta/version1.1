package com.sensiple.contactsrepository.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.model.campaignContactSearch;
import com.sensiple.contactsrepository.service.CampaignService;
import com.sensiple.contactsrepository.utils.ContactsSession;

/**
 * 
 * @author saranya S
 * This controller is used to maintain the service about the campaign screen
 *
 */
@RestController
@RequestMapping(value = "/campaign/")
public class CampaignController {
	
	private Logger LOGGER = Logger.getLogger(CampaignController.class);

	@Inject
	private CampaignService campaignService;
	
	@RequestMapping(value = "getCampaignList", method = RequestMethod.GET)
	public @ResponseBody String getCampaignList(@RequestParam(value="startRecord") int startRecord,
												@RequestParam(value="recordToShow") int recordToShow,
												@RequestParam(value="campaignName") String campaignName, 
												@RequestParam(value="campaignNumber") int campaignNumber, 
												@RequestParam(value="businessUnit") int businessUnit,
												@RequestParam(value="campaignTypeId") int campaignTypeId,
												@RequestParam(value="ownerName") String ownerName, 
												@RequestParam(value="region") int region, 
												@RequestParam(value="campaignStatusId") int campaignStatusId,
												@RequestParam(value="serviceOffering") String serviceOffering,
												@RequestParam(value="campaignRunDate") String campaignRunDate
								) {

		JSONObject campainObject = new JSONObject();
		long numberOfRecords = 0;
		LOGGER.info("getCampaignContacts method invoked");
		List<CampaignDetails> campaignList = new ArrayList<CampaignDetails>();
		try {
			campaignList = campaignService.getCampaignList(startRecord, recordToShow, campaignName, campaignNumber, businessUnit, campaignTypeId, ownerName, region, campaignStatusId, serviceOffering, campaignRunDate);
			if (campaignList != null
					&& !campaignList.isEmpty()) {
				 numberOfRecords = campaignList.get(0).getTotalRecords();
			}
			campainObject.put("totalCount", numberOfRecords);
			campainObject.put("campaignList", campaignList);
			
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			
		}
		
		
		return new Gson().toJson(campainObject);
	}
	/**
	 * this method is used to save campaign contact search name 
	 * @param contactsSearch
	 * @return
	 * @author siddheshwaranm
	 */
	@RequestMapping(value = "/campaign/saveSearchName", method = RequestMethod.POST)
	public @ResponseBody String SaveCampaignSearchName(@RequestBody campaignContactSearch contactsSearch,HttpServletRequest request) {

		JSONObject searchNameObject = new JSONObject();
		LOGGER.info("SaveCampaignSearchName method invoked");
		String successMsg="";
		HttpSession session=request.getSession();
		User user=new User();
		ContactsSession contactsSession;
		String createdBy="";
		try {
			 
			if(session.getAttribute("contactsSession")!=null){
		 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
		 			user=contactsSession.getUser();
		 			createdBy=String.valueOf(user.getId());
			 }
			contactsSearch.setContactCreatedBy(createdBy);;
			
			successMsg = campaignService.saveSearchDetails(contactsSearch);
			searchNameObject.put("successMsg", successMsg);
			
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			
		}
		return new Gson().toJson(searchNameObject);
	}
	/**
 	 * This method used to check the already contact name list exist or not
 	 * @param contactListName
 	 * @return
 	 * @throws Exception
 	 */
  @RequestMapping(value = "/campaign/isContactSearchNameExist", method = RequestMethod.POST)
	public @ResponseBody boolean isCampaignContactSearchNameExist(@RequestBody String searchName) throws Exception  {
		boolean isExist = false;
		org.codehaus.jettison.json.JSONObject contactObject = null;
		contactObject = new org.codehaus.jettison.json.JSONObject(searchName);
		isExist = campaignService.issearchNameExist(contactObject.getString("searchSaveName"));
		return isExist;
	}
	@RequestMapping(value = "saveCampaignDetails", method = RequestMethod.POST)
	public int saveFieldCongiguration(@RequestBody CampaignDetails campaignDetails) {
		LOGGER.info("Initialize the saveCampaignDetails method in controller");
		
		int response = 0;
		try {
			response = campaignService.saveFieldConfiguration(campaignDetails);
			LOGGER.debug("Response from save campaign "+response);
		} catch (Exception e) {
			
			LOGGER.error("Exception in save campaign details method"+ExceptionUtils.getStackTrace(e));
		}
		return response;
	}
	
	
}

	