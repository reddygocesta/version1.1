package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.DaysType;

public class DaysTypeMapper implements RowMapper<DaysType> {
	
	@Override
	public DaysType mapRow(ResultSet rs, int rowNum)
	                throws SQLException {
			 DaysType days=new DaysType();
			 days.setName(rs.getString("days_type_name"));
			 days.setId(rs.getInt("days_id"));
			 
			 return days;
		 }
	

}
