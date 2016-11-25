package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.PasswordHistory;

public class PassWordHistoryRowMapper implements RowMapper<PasswordHistory>{

	public PasswordHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PasswordHistory passHistory = new PasswordHistory();
		
		passHistory.setPassword(rs.getString("password"));
		
		return passHistory;
	}
	
}
