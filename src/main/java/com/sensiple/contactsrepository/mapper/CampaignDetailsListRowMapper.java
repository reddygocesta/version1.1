package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.CampaignStatus;
import com.sensiple.contactsrepository.model.CampaignType;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.utils.DateUtils;

public class CampaignDetailsListRowMapper implements RowMapper<CampaignDetails> {
	@Override
	public CampaignDetails mapRow(ResultSet rs, int arg1) throws SQLException {

		CampaignDetails campaign = new CampaignDetails();
		campaign.setSerialNumber(rs.getInt("row"));
		campaign.setNumber(rs.getInt("campaign_number"));
		campaign.setCampaignName(rs.getString("campaign_name"));
		campaign.setDescription(rs.getString("campaign_description"));

		campaign.setPlannedRunDate(DateUtils.convertDateToString(rs.getString("campaign_planned_run_date")));
		campaign.setActualRunDate(DateUtils.convertDateToString(rs.getString("actual_run_date")));
		campaign.setContactOpenedDate(rs
				.getString("campaign_contact_opened_date"));

		campaign.setContactLimit(rs.getInt("campaign_contact_sheet_limit"));

		CampaignType campaignType = new CampaignType();
		campaignType.setCampaignTypeName(rs.getString("campaign_type_name"));
		campaign.setCampaignType(campaignType);

		CampaignStatus campaignStatus = new CampaignStatus();
		campaignStatus.setStatusName(rs.getString("campaign_status_name"));
		campaign.setCampaignStatus(campaignStatus);

		User ownerName = new User();
		ownerName.setFirstName(rs.getString("first_name"));
		ownerName.setLastName(rs.getString("last_name"));
		campaign.setCampaignOwner(ownerName);

		campaign.setTotalRecords(rs.getInt("total_records"));

		return campaign;

	}

}
