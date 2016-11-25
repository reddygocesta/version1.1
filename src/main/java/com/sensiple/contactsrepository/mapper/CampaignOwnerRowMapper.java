package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.User;

public class CampaignOwnerRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User campaignOwner = new User();
		campaignOwner.setId(rs.getLong("user_id"));
		campaignOwner.setFirstName(rs.getString("first_name"));
		campaignOwner.setLastName(rs.getString("last_name"));
		return campaignOwner;
	}

}
