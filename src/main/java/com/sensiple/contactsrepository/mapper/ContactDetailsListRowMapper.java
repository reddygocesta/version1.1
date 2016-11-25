package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.StateDetails;

public class ContactDetailsListRowMapper implements RowMapper<ContactDetails>{

	public ContactDetails mapRow(ResultSet rs, int rowNo) throws SQLException {

		ContactDetails contact = new ContactDetails();
		contact.setRow(rs.getInt("row"));
		contact.setContactId(rs.getInt("contact_id"));
		contact.setContactEmailId(rs.getString("contact_email_id"));
		contact.setFirstName(rs.getString("first_name"));
		contact.setLastName(rs.getString("last_name"));
		contact.setJobTitle(rs.getString("job_title_value"));
		contact.setContactSource(rs.getString("contact_source"));
		
		CompanyDetails company=new CompanyDetails();
		company.setCompanyName(rs.getString("company_name"));
		
		CountryDetails country=new CountryDetails();
		country.setCountryName(rs.getString("country_name"));
		List<StateDetails> stateList=new ArrayList<StateDetails>();
		
		StateDetails state=new StateDetails();
		state.setStateName(rs.getString("state_name"));
		stateList.add(state);
		
		country.setStates(stateList);
		
		List<AddressDetails> addressDetails=new ArrayList<AddressDetails>();
		
		AddressDetails address=new AddressDetails();
		address.setCity(rs.getString("city"));
		address.setState(state);
		address.setCountry(country);
		
		addressDetails.add(address);
		
		company.setAddressDetails(addressDetails);
		company.setIndustry(rs.getString("industry_name"));
		
		contact.setCompanyId(company);
		
		contact.setTotalRecords(rs.getInt("total_records"));
		
		return contact;
	}
}
