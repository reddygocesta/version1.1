package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignBusinessUnit;

public class CampaignBusinessUnitRowMapper implements RowMapper<CampaignBusinessUnit> {

	@Override
	public CampaignBusinessUnit mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignBusinessUnit businessUnit = new CampaignBusinessUnit();
		businessUnit.setId(rs.getLong("business_unit_id"));
		businessUnit.setBusinessUnitName(rs.getString("business_unit_name"));
		return businessUnit;
	}

}
