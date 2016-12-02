package com.sensiple.contactsrepository.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.sensiple.contactsrepository.dao.CampaignDetailsDAO;
import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.campaignContactSearch;
import com.sensiple.contactsrepository.service.CampaignService;

@Named
public class CampaignServiceImpl implements CampaignService {

	@Inject
	private CampaignDetailsDAO CampaignDetailsDao;

	@Override
	public List<CampaignDetails> getCampaignList(int startRecord, int recordToShow,
			String campaignName, int campaignNumber, int businessUnit,
			int campaignTypeId, String ownerName, int region, int campaignStatusId,
			String serviceOffering, String campaignRunDate) throws Exception {
		return CampaignDetailsDao.getCampaignList(startRecord, recordToShow, campaignName, campaignNumber, businessUnit, campaignTypeId, ownerName, region, campaignStatusId, serviceOffering, campaignRunDate);
	}
	
	public String saveSearchDetails(campaignContactSearch contactSearch) throws Exception {
		return CampaignDetailsDao.saveSearchDetails(contactSearch);
	}
	public boolean issearchNameExist(String SaveSearchName) throws Exception{
		return CampaignDetailsDao.issearchNameExist(SaveSearchName);
	}
	
	@Override
	public int saveFieldConfiguration(CampaignDetails campaignDetails) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}