package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.Role;

public class RoleDetailsMapper implements RowMapper<Role> {

	public Role mapRow(ResultSet rs, int rowNo) throws SQLException {
    Role role = new Role();
	role.setId(rs.getInt("role_id"));
	role.setRoleName(rs.getString("role_name"));
	return role;

	}

}

