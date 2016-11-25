package com.sensiple.contactsrepository.dao;
import java.util.List;

import com.sensiple.contactsrepository.model.CampaignDetails;

public interface CampaignDetailsDAO {
	
	List<CampaignDetails> getCampaignList(int startRecord, int recordToShow, String campaignName, int campaignNumber,
			int businessUnit, int campaignTypeId, String ownerName,
			int region, int campaignStatusId, String serviceOffering,
			String campaignRunDate)throws Exception;
}