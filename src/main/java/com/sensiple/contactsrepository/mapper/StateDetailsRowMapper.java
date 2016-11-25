package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.StateDetails;

public class StateDetailsRowMapper  implements RowMapper<StateDetails> {

	@Override
	public StateDetails mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		StateDetails state=new StateDetails();
		state.setStateId(rs.getLong("state_id"));
		state.setStateName(rs.getString("state_name"));
		state.setShortName(rs.getString("state_short_name"));
		return state;
	}

}