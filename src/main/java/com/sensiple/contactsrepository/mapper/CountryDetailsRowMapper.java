package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CountryDetails;

public class CountryDetailsRowMapper  implements RowMapper<CountryDetails> {

	@Override
	public CountryDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		CountryDetails country=new CountryDetails();
		country.setCountryId(rs.getLong("country_id"));
		country.setCountryName(rs.getString("country_name"));
		
		
		return country;
	}

}