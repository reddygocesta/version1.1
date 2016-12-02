package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.dao.impl.ConfigurationDAOImpl;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;

public class UserDetailsListRowMapper implements RowMapper<User>{
	private Logger LOGGER = Logger.getLogger(ConfigurationDAOImpl.class);
	public User mapRow(ResultSet rs, int rowNo) throws SQLException {

		LOGGER.info("--------------UserDetailsListRowMapper method invoked--------------");
		User user = new User();
		user.setId(rs.getInt("user_id"));
		user.setEmail(rs.getString("email_address"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setTotalRecords(rs.getInt("total_records"));

		Role userRole = new Role();
		userRole.setId(rs.getLong("role_id"));
		userRole.setRoleName(rs.getString("role_name"));
		user.setRole(userRole);
		return user;
	}
}
