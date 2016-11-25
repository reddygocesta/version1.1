package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignRegion;

public class CampaignRegionRowMapper implements RowMapper<CampaignRegion> {

	@Override
	public CampaignRegion mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignRegion campaignRegion = new CampaignRegion();
		campaignRegion.setId(rs.getLong("region_id"));
		campaignRegion.setRegionName(rs.getString("region_name"));
		return campaignRegion;
	}

}
