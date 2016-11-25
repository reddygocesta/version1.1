package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.StateDetails;
import com.sensiple.contactsrepository.model.User;

public class CampaingConfigurationRowMapper implements RowMapper<CampaignConfiguration> {

	@Override
	public CampaignConfiguration mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignConfiguration campaignConfiguration = new CampaignConfiguration();
		campaignConfiguration.setRow(rs.getInt("row"));
		campaignConfiguration.setTotalRecords(rs.getInt("total_records"));
		campaignConfiguration.setCampaignConfigurationId(rs.getLong("campaign_configuration_id"));
		User campaingOwner = new User();
		campaingOwner.setId(rs.getLong("user_id"));
		campaingOwner.setFirstName(rs.getString("first_name"));
		campaingOwner.setLastName(rs.getString("last_name"));
		campaignConfiguration.setCampaingOwner(campaingOwner);
		StateDetails stateDetails = new StateDetails();
		stateDetails.setStateName(rs.getString("state_name"));
		campaignConfiguration.setState(stateDetails);
		CountryDetails countryDetails = new CountryDetails();
		countryDetails.setCountryName(rs.getString("country_name"));
		campaignConfiguration.setCountry(countryDetails);
		CampaignServiceOffering campaignServiceOffering = new CampaignServiceOffering();
		campaignServiceOffering.setServiceName(rs.getString("service_offering_name"));
		campaignConfiguration.setServiceOffering(campaignServiceOffering);
		
		return campaignConfiguration;
	}

	

}
