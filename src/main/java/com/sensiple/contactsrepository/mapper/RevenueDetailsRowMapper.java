package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.RevenueDetails;

public class RevenueDetailsRowMapper implements RowMapper<RevenueDetails> {

	@Override
	public RevenueDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		RevenueDetails revenueDetails=new RevenueDetails();
		revenueDetails.setRevenueId(rs.getInt("revenue_id"));
		revenueDetails.setRevenueName(rs.getString("revenue_value"));
		
		return revenueDetails;
	}

}
