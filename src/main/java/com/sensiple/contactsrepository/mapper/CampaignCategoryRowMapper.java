package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CampaignCategory;

/**
 *This rowmapper is used to get the Campaign Category
 * @author narasimhareddyk
 *
 */
public class CampaignCategoryRowMapper implements RowMapper<CampaignCategory> {

	@Override
	public CampaignCategory mapRow(ResultSet rs, int arg1) throws SQLException {
		CampaignCategory campaignCategory = new CampaignCategory();
		campaignCategory.setId(rs.getLong("campaign_category_id"));
		campaignCategory.setCampaignName(rs.getString("campaign_category_name"));
		return campaignCategory;
	}

	

}
