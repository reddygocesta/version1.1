package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.StateDetails;

public class ContactDetailsRowMapper implements RowMapper<ContactDetails>{

	public ContactDetails mapRow(ResultSet rs, int rowNo) throws SQLException {

		ContactDetails contact = new ContactDetails();
		contact.setContactEmailId(rs.getString("contact_email_id"));
		contact.setFirstName(rs.getString("first_name"));
		contact.setLastName(rs.getString("last_name"));
		contact.setMiddleName(rs.getString("middle_name"));
		contact.setContactStatus(rs.getString("contact_status"));
		contact.setJobTitle(rs.getString("job_title_value"));
		contact.setContactSource(rs.getString("contact_source"));
		contact.setCreatedBy(rs.getString("created_by"));
		contact.setPhoneNumber(rs.getString("phone_number"));
		contact.setExtension(rs.getInt("extension"));
		contact.setMobile(rs.getString("mobile"));
		
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try{
		 contact.setCreatedDate(formatter.format(rs.getDate("created_date")));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		contact.setApprovedBy(rs.getString("approved_by"));
		
		CompanyDetails company=new CompanyDetails();
		company.setCompanyName(rs.getString("company_name"));
		company.setWebsite(rs.getString("website"));
		company.setCompanySize(rs.getString("company_size"));
		company.setRevenue(rs.getString("revenue_value"));
		company.setSicCode(rs.getString("sic_code_name"));
		company.setIndustry(rs.getString("industry_name"));
		company.setCompanyEmailId(rs.getString("company_email_id"));
		
		
		CountryDetails country=new CountryDetails();
		country.setCountryName(rs.getString("country_name"));
		List<StateDetails> stateList=new ArrayList<StateDetails>();
		
		StateDetails state=new StateDetails();
		state.setStateName(rs.getString("state_name"));
		stateList.add(state);
		
		country.setStates(stateList);
		
		List<AddressDetails> addressDetails=new ArrayList<AddressDetails>();
		
		AddressDetails address=new AddressDetails();
		address.setAddressLine1(rs.getString("address_line1"));
		address.setAddressLine2(rs.getString("address_line2"));
		address.setCity(rs.getString("city"));
		address.setState(state);
		address.setCountry(country);
		address.setZipCode(rs.getString("zipcode"));
		address.setHeadQuarters(rs.getBoolean("head_quarters"));
		address.setSuite(rs.getString("suite"));
		
		addressDetails.add(address);
		
		company.setAddressDetails(addressDetails);
		contact.setCompanyId(company);
		
		return contact;
	}
}
