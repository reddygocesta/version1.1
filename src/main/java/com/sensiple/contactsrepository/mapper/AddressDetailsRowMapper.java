package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.StateDetails;

public class AddressDetailsRowMapper implements RowMapper<AddressDetails> {

	@Override
	public AddressDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		CountryDetails country = new CountryDetails();
		country.setCountryId(rs.getInt("country_id"));
		country.setCountryName(rs.getString("country_name"));

		StateDetails state = new StateDetails();
		state.setStateId(rs.getInt("state_id"));
		state.setStateName(rs.getString("state_name"));

		AddressDetails addressDetails = new AddressDetails();
		addressDetails.setAddressId(rs.getInt("address_id"));
		addressDetails.setAddressLine1(rs.getString("address_line1"));
		addressDetails.setAddressLine2(rs.getString("address_line2"));
		addressDetails.setCity(rs.getString("city"));
		addressDetails.setState(state);
		addressDetails.setCountry(country);
		addressDetails.setZipCode(rs.getString("zipcode"));
		addressDetails.setHeadQuarters(rs.getBoolean("head_quarters"));
		addressDetails.setSuite(rs.getString("suite"));
		addressDetails.setLocation(addressDetails.getAddressLine1() + ", " + addressDetails.getAddressLine2() + ", "
				+ addressDetails.getCity() + ", " + country.getCountryName() + ", " + state.getStateName() + ", "
				+ addressDetails.getSuite()+ ", " + addressDetails.getZipCode());
		return addressDetails;
	}

}
