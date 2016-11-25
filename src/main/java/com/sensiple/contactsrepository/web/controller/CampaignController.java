package com.sensiple.contactsrepository.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.service.CampaignService;

import net.sf.json.JSONObject;

/**
 * 
 * @author saranya S
 * This controller is used to maintain the service about the campaign screen
 *
 */
@RestController
public class CampaignController {
	
	private Logger LOGGER = Logger.getLogger(CampaignController.class);

	@Autowired
	private CampaignService campaignService;
	
	@RequestMapping(value = "/campaign/getCampaignList", method = RequestMethod.GET)
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
		System.out
				.println("--------------getCampaignContacts method invoked--------------");
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
			System.out.println(ExceptionUtils.getStackTrace(e));
		}
		System.out
				.println("--------------getCampaignContacts method invoked-------------- "
						+ campainObject.toString());
		
		return new Gson().toJson(campainObject);
	}
	
}

	