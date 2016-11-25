package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.IndustryDetails;

public class IndustryDetailsRowMapper  implements RowMapper<IndustryDetails> {

	@Override
	public IndustryDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		IndustryDetails industryDetails=new IndustryDetails();
		industryDetails.setIndustryId(rs.getInt("industry_id"));
		industryDetails.setIndustryName(rs.getString("industry_name"));
		
		
		return industryDetails;
	}

}