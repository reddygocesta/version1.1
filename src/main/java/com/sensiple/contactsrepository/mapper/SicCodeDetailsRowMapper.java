package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.SicCodeDetails;

public class SicCodeDetailsRowMapper  implements RowMapper<SicCodeDetails> {

	@Override
	public SicCodeDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		SicCodeDetails sicCodeDetails=new SicCodeDetails();
		sicCodeDetails.setSicCodeId(rs.getInt("sic_code_id"));
		sicCodeDetails.setSicCodeName(rs.getString("sic_code_name"));
		return sicCodeDetails;
	}

}