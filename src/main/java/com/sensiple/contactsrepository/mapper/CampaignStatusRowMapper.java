package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignStatus;

public class CampaignStatusRowMapper implements RowMapper<CampaignStatus> {

	@Override
	public CampaignStatus mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignStatus campaignStatus = new CampaignStatus();
		campaignStatus.setId(rs.getLong("campaign_status_id"));
		campaignStatus.setStatusName(rs.getString("campaign_status_name"));
		return campaignStatus;
	}

	

}
