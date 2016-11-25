package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CompanySizeDetails;

public class CompanySizeDetailsRowMapper implements RowMapper<CompanySizeDetails> {

	@Override
	public CompanySizeDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		CompanySizeDetails companySizeDetails=new CompanySizeDetails();
		companySizeDetails.setSizeId(rs.getInt("company_size_id"));
		companySizeDetails.setCompanySizeName(rs.getString("company_size"));
		
		return companySizeDetails;
	}

}
