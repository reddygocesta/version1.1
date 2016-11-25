package com.sensiple.contactsrepository.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sensiple.contactsrepository.dao.CampaignDetailsDAO;
import com.sensiple.contactsrepository.mapper.CampaignDetailsListRowMapper;
import com.sensiple.contactsrepository.model.CampaignDetails;

@Named
public class CampaignDAOImpl implements CampaignDetailsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CampaignDetails> getCampaignList(int startRecord, int recordToShow,
			String campaignName, int campaignNumber, int businessUnit,
			int campaignTypeId, String ownerName, int region, int campaignStatusId,
			String serviceOffering, String campaignRunDate) throws Exception {
		
		List<CampaignDetails> campaign = new ArrayList<CampaignDetails>();

		campaign = jdbcTemplate.query("call GET_CAMPAIGN_DETAILS(?,?,?,?,?,?,?,?,?,?,?)",
				new CampaignDetailsListRowMapper(), startRecord, recordToShow, campaignName, 
				campaignNumber, businessUnit, campaignTypeId, ownerName, region, campaignStatusId, serviceOffering,
				campaignRunDate);
		
		return campaign;
		
		
	}

}
