package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.DaysType;

public class ConfigurationRowMapper implements RowMapper<Configuration> {

	@Override
	public Configuration mapRow(ResultSet rs, int rowNum) throws SQLException {
		Configuration configuration = new Configuration();
		configuration.setConfigurationId(rs.getLong("configuration_id"));
		configuration.setUserId(rs.getLong("user_id"));
		
		configuration.setCampaignLeadTime(rs.getInt("campaign_lead_time"));
		DaysType campaignLeadTimeType = new DaysType();
		campaignLeadTimeType.setId(rs.getInt("campaign_lead_time_type"));
		configuration.setCampaignLeadTimeType(campaignLeadTimeType);
		
		configuration.setAllowAssignContacts(rs.getInt("allow_assign_contacts"));
		DaysType allowAssignContactsType = new DaysType();
		allowAssignContactsType.setId(rs.getInt("allow_assign_contacts_type"));
		configuration.setAllowAssignContactsType(allowAssignContactsType);
		
		configuration.setDeleteThreshold(rs.getInt("delete_threshold"));
		DaysType deleteThresholdType = new DaysType();
		deleteThresholdType.setId(rs.getInt("delete_threshold_type"));
		configuration.setDeleteThresholdType(deleteThresholdType);
		
		configuration.setSaveSearchThreshold(rs.getInt("save_search_threshold"));
		DaysType saveSearchThresholdType = new DaysType();
		saveSearchThresholdType.setId(rs.getInt("save_search_threshold_type"));
		configuration.setSaveSearchThresholdType(saveSearchThresholdType);
		
		configuration.setMaximumCompaignsAllowed(rs.getInt("maximum_campaign_allowed"));
		configuration.setContactAvailability(rs.getString("contact_availability"));
		CampaignContactStatus campaignContactStatus = new CampaignContactStatus();
		campaignContactStatus.setCampaignContactStatusId(rs.getInt("campaign_contact_status"));
		configuration.setCampaignContactStatus(campaignContactStatus);
		
		return configuration;
	}

}
