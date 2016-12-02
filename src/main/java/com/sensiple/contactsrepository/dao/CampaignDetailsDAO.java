package com.sensiple.contactsrepository.dao;
import java.util.List;

import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.campaignContactSearch;

/**
 * @author narasimhareddyk
 *
 */
public interface CampaignDetailsDAO {
	
	/**
	 * This method to  GET Campaign Details with Pagination
	 * @param startRecord
	 * @param recordToShow
	 * @param campaignName
	 * @param campaignNumber
	 * @param businessUnit
	 * @param campaignTypeId
	 * @param ownerName
	 * @param region
	 * @param campaignStatusId
	 * @param serviceOffering
	 * @param campaignRunDate
	 * @return campaign details list
	 * @throws Exception
	 */
	List<CampaignDetails> getCampaignList(int startRecord, int recordToShow, String campaignName, int campaignNumber,
			int businessUnit, int campaignTypeId, String ownerName,
			int region, int campaignStatusId, String serviceOffering,
			String campaignRunDate)throws Exception;
	public String saveSearchDetails(campaignContactSearch contactSearch) throws Exception ;
	public boolean issearchNameExist(String SaveSearchName) throws Exception;
}