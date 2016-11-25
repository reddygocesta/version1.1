package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.ContactStatus;

public class ContactsStatusMapper implements RowMapper<ContactStatus> {

	@Override
	public ContactStatus mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		ContactStatus contactStatus=new ContactStatus();
		contactStatus.setId(rs.getInt("status_id"));
		contactStatus.setStatus(rs.getString("status_name"));
		
		return contactStatus;
	}

}
