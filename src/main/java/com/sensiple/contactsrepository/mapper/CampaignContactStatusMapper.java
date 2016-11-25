package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignContactStatus;

public class CampaignContactStatusMapper implements RowMapper<CampaignContactStatus>{

	@Override
	public CampaignContactStatus mapRow(ResultSet rs, int rowNo) throws SQLException {
		CampaignContactStatus campaignContactStatus=new CampaignContactStatus();
	
		campaignContactStatus.setCampaignStatus(rs.getString("status_name"));
		campaignContactStatus.setCampaignContactStatusId(rs.getInt("campaign_contact_status_id"));
		 return campaignContactStatus;
		
	}

}
