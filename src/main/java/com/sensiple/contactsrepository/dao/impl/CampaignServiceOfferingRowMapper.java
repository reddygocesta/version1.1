package com.sensiple.contactsrepository.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignServiceOffering;

public class CampaignServiceOfferingRowMapper implements RowMapper<CampaignServiceOffering> {

	@Override
	public CampaignServiceOffering mapRow(ResultSet rs,int arg0) throws SQLException {
		CampaignServiceOffering serviceOfferingDetails = new CampaignServiceOffering();
		serviceOfferingDetails.setId(rs.getLong("service_offering_id"));
		serviceOfferingDetails.setServiceName(rs.getString("service_offering_name"));
		return serviceOfferingDetails;
	}

}
