package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignType;

public class CampaignTypeRowMapper implements RowMapper<CampaignType> {

	@Override
	public CampaignType mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignType campaignType = new CampaignType();
		campaignType.setId(rs.getInt("campaign_type_id"));
		campaignType.setCampaignTypeName(rs.getString("campaign_type_name"));
		
		return campaignType;
	}

}
