package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.ContactServiceOffering;

public class ContactServiceOfferingRowMapper implements RowMapper<ContactServiceOffering> {

	@Override
	public ContactServiceOffering mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		ContactServiceOffering contactServiceOffering=new ContactServiceOffering();
		contactServiceOffering.setSeriveOfferingId(rs.getInt("service_offering_id"));
		contactServiceOffering.setServiceOfferingName(rs.getString("service_offering_name"));
		
		return contactServiceOffering;
	}

}
