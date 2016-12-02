package com.sensiple.contactsrepository.service;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.campaignContactSearch;

/**
 * This interface contains methods about the campaing related services
 * @author narasimhareddyk
 *
 */
public interface CampaignService {

List<CampaignDetails> getCampaignList(int startRecord, int recordToShow,
		String campaignName, int campaignNumber, int businessUnit,
		int campaignTypeId, String ownerName, int region, int campaignStatusId,
		String serviceOffering, String campaignRunDate) throws Exception;
public String saveSearchDetails(campaignContactSearch contactSearch) throws Exception ;
public boolean issearchNameExist(String SaveSearchName) throws Exception;

/**
 * @param campaignDetails
 * @return it will return success and failure status
 * @throws Exception
 */
int saveFieldConfiguration(final CampaignDetails campaignDetails) throws Exception;

}
