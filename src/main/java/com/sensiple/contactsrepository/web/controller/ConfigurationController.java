package com.sensiple.contactsrepository.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.service.ConfigurationService;
import com.sensiple.contactsrepository.utils.Constants;

/**
 * 
 * @author narasimhareddyk
 * This controller is used to maintain the service about the configuration screen
 *
 */
@RestController
@RequestMapping("/configuration/")
public class ConfigurationController { 
	private Logger LOGGER = Logger.getLogger(ConfigurationController.class);
	
	@Inject
	private ConfigurationService configurationService;
	
	
	/**
	 * This method is used to save the Field configuration information about user for campaign
	 * @param configuration
	 * @return
	 */
	@RequestMapping(value = "saveFieldCongiguration", method = RequestMethod.POST)
	public int saveFieldCongiguration(@RequestBody Configuration configuration) {
		LOGGER.info("Initialize the saveFieldCongiguration method in controller");
		int response = 0;
		try {
			response = configurationService.saveFieldConfiguration(configuration);
			LOGGER.debug("Response from save Field configuration"+response);
		} catch (Exception e) {
			
			LOGGER.error("Exception in Save Field Configuration method"+ExceptionUtils.getStackTrace(e));
		}
		return response;
	}
	
	@RequestMapping(value = "getFieldCongiguration", method = RequestMethod.POST)
	public String saveFieldCongiguration(@RequestParam("userId") long userId) {
		LOGGER.info("Initialize the getFieldCongiguration method in controller");
		
		Configuration configuration = null;
		try {
			configuration = configurationService.getFieldCongiguration(userId);
			List<ContactStatus> canBeReachedStatus = configurationService.getContactStatus(Constants.CAN_BE_REACHED);
			configuration.setCanBeReachedStatusIds(canBeReachedStatus);
			List<ContactStatus> canNotBeReachedStatus = configurationService.getContactStatus(Constants.CAN_NOT_BE_REACHED);
			configuration.setCanNotBeReachedStatusIds(canNotBeReachedStatus);
			LOGGER.debug("Response from save Field configuration"+configuration);
		} catch (Exception e) {
			LOGGER.error("Exception in get Field Configuration method"+ExceptionUtils.getStackTrace(e));
		}
		String response = new Gson().toJson(configuration);
		
		return response;
	}
	
	@RequestMapping(value = "getCampaignOwner", method = RequestMethod.POST)
	public String getCampaignOwner(@RequestParam("ownerName") String campaignOwnerName) {
		LOGGER.info("Initialize the getCampaignOwner method in controller");
		
		List<User> campaingDetails = new ArrayList<User>();
		try {
			campaingDetails = configurationService.getCampaignOwner(campaignOwnerName);
			LOGGER.debug("Response from save Field configuration"+campaingDetails);
		} catch (Exception e) {
			LOGGER.error("Exception in get Field Configuration method"+ExceptionUtils.getStackTrace(e));
		}
		String response = new Gson().toJson(campaingDetails);
		
		return response;
	}
	
	@RequestMapping(value = "getServiceOffering", method = RequestMethod.POST)
	public String getServiceOffering(@RequestParam("serviceOfferingId") String serviceOfferingName) {
		LOGGER.info("Initialize the getServiceOffering method in controller");
		
		List<CampaignServiceOffering> serviceOfferingDetails = new ArrayList<CampaignServiceOffering>();
		try {
			serviceOfferingDetails = configurationService.getServiceOffering(serviceOfferingName);
			LOGGER.debug("Response from getServiceOffering"+serviceOfferingDetails);
		} catch (Exception e) {
			LOGGER.error("Exception in getServiceOffering method"+ExceptionUtils.getStackTrace(e));
		}
		String response = new Gson().toJson(serviceOfferingDetails);
		
		return response;
	}
	
	@RequestMapping(value = "saveCampaignConfiguration", method = RequestMethod.POST)
	public int saveCampaingConfiguration(@RequestBody CampaignConfiguration campaignConfiguration) {
		LOGGER.info("Initialize the saveCampaingConfiguration method in controller");
		
		int response = 0;
		try {
			response = configurationService.saveCampaingConfiguration(campaignConfiguration);
			LOGGER.debug("Response from campaing Configuration configuration"+response);
		} catch (Exception e) {
			
			LOGGER.error("Exception in Save campaing Configuration method"+ExceptionUtils.getStackTrace(e));
		}
		return response;
	}
	
	@RequestMapping(value = "getCampaingConfiguration", method = RequestMethod.GET)
	public @ResponseBody String getCampaingConfiguration(@RequestParam(value="startRecord") int startRecord,
												@RequestParam(value="recordToShow") int recordToShow) {

		JSONObject contactObject = new JSONObject();
		long numberOfRecords = 0;
		
		List<CampaignConfiguration> configurationList = new ArrayList<CampaignConfiguration>();
		try {
			configurationList = configurationService.getCampaingConfiguration(startRecord, recordToShow);
			if (configurationList != null
					&& !configurationList.isEmpty()) {
				 numberOfRecords = configurationList.get(0)
						.getTotalRecords();
			}
			contactObject.put("totalCount", numberOfRecords);
			contactObject.put("configurationList", configurationList);
			
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}
				
		return new Gson().toJson(contactObject);
	}
	
	@RequestMapping(value = "deConfigureCampaign", method = RequestMethod.POST)
	public String deConfigureCampaign(@RequestParam("configurationIds") String configurationIds) {
		LOGGER.info("Initialize the deConfigureCampaign method in controller");
		
		int result = 0;
		try {
			result = configurationService.deConfigureCampaign(configurationIds);
			LOGGER.debug("Response from deConfigureCampaign"+result);
		} catch (Exception e) {
			LOGGER.error("Exception in deConfigureCampaign method"+ExceptionUtils.getStackTrace(e));
		}
		String response = new Gson().toJson(result);
		
		return response;
	}
	
	
	
}
