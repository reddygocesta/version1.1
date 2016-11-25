package com.sensiple.contactsrepository.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sensiple.contactsrepository.dao.UserDetailsDAO;
import com.sensiple.contactsrepository.mapper.CampaignContactStatusMapper;
import com.sensiple.contactsrepository.mapper.ContactsStatusMapper;
import com.sensiple.contactsrepository.mapper.DaysTypeMapper;
import com.sensiple.contactsrepository.mapper.PassWordHistoryRowMapper;
import com.sensiple.contactsrepository.mapper.RoleDetailsMapper;
import com.sensiple.contactsrepository.mapper.UserDetailsListRowMapper;
import com.sensiple.contactsrepository.mapper.UserDetailsRowMapper;
import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.PasswordHistory;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.utils.ProcedureConstant;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall simpleCall;

	@Override
	public User findByLogin(String userName) {
		User user = null;

		try {
			user = (User) jdbcTemplate.queryForObject(
					"call GET_AUTHENTICATION_DETAILS(?)",
					new UserDetailsRowMapper(), userName);

		} catch (EmptyResultDataAccessException e) {
			user = new User();

			throw e;
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	public int forgotPassword(final String emailAddress) throws Exception {

		int queryResult = 0;

		String sql = "SELECT count(*) FROM users where email_address = ?";
		queryResult = jdbcTemplate.queryForObject(sql, Integer.class,
				emailAddress);

		return queryResult;
	}

	@Override
	public int addUser(User user) throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;
		long roleId = 0;

		Role role = user.getRole();
		if (role != null) {
			roleId = role.getId();
		}

		params.addValue(ProcedureConstant.USERID, user.getId());
		params.addValue(ProcedureConstant.FIRST_NAME, user.getFirstName());
		params.addValue(ProcedureConstant.LAST_NAME, user.getLastName());
		params.addValue(ProcedureConstant.EMAIL_ADDRESS, user.getEmail());
		params.addValue(ProcedureConstant.PHONE_NUMBER, user.getPhoneNumber());
		params.addValue(ProcedureConstant.PASSWORD, user.getPassword());
		params.addValue(ProcedureConstant.ROLE_ID, roleId);

		final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(ProcedureConstant.ADD_USER_DETAILS);
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.USERID, Types.BIGINT));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.FIRST_NAME, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.LAST_NAME, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.EMAIL_ADDRESS, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.PHONE_NUMBER, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.PASSWORD, Types.VARCHAR));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.ROLE_ID, Types.BIGINT));

		final java.util.Map<String, Object> result = simpleJdbcCall
				.execute(params);

		response = Integer.parseInt(String.valueOf(result
				.get(ProcedureConstant.RESULT)));

		return response;

	}

	@Override
	public List<Role> getRole() throws Exception {

		String sql = "SELECT * FROM user_role";
		List<Role> roleList = new ArrayList<Role>();
		roleList = jdbcTemplate.query(sql, new RoleDetailsMapper());
		return roleList;
	}

	@Override
	public Role role(int roleid) throws Exception {
		String sql = "SELECT * FROM user_role WHERE ROLEID = ? )::int";
		Role role = new Role();
		role = jdbcTemplate.queryForObject(sql, new Object[] { roleid },
				new RoleDetailsMapper());
		return role;
	}

	@Override
	public List<User> getUsersList(int startRecord, int recordToShow,
			String firstName, String lastName, String emailAddress,
			String roleName, String phoneNumber) throws Exception {

		List<User> user = new ArrayList<User>();

		user = jdbcTemplate.query("call GET_USER_DETAILS(?,?,?,?,?,?,?)",
				new UserDetailsListRowMapper(), startRecord, recordToShow,
				firstName, lastName, emailAddress, phoneNumber, roleName);

		return user;
	}

	public int updatePassword(final String newPassword, final long userId)
			throws Exception {

		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;

		String encrptedPassword = new BCryptPasswordEncoder()
				.encode(newPassword);

		params.addValue(ProcedureConstant.USER_ID, userId);
		params.addValue(ProcedureConstant.NEW_PASSWORD, encrptedPassword);

		final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(ProcedureConstant.CHANGE_PASSWORD_DETAILS);
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.USER_ID, Types.BIGINT));
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.NEW_PASSWORD, Types.VARCHAR));

		final java.util.Map<String, Object> result = simpleJdbcCall
				.execute(params);

		response = Integer.parseInt(String.valueOf(result
				.get(ProcedureConstant.RESULT)));

		return response;
	}

	public boolean validatePassword(final long userId, final String oldPassword) {

		boolean result = false;
		String password = null;
		String sql = null;
		sql = "SELECT password FROM users WHERE user_id =?";

		password = (String) jdbcTemplate.queryForObject(sql,
				new Object[] { userId }, String.class);

		if (null != password && null != oldPassword) {
			result = new BCryptPasswordEncoder().matches(oldPassword, password);
		}

		return result;

	}

	public List<PasswordHistory> getPasswordList(final long userId)
			throws Exception {

		String sql = null;
		List<PasswordHistory> passwordHistory = null;

		if (userId > 0) {
			sql = "SELECT * FROM password_history WHERE user_id = ?";
			passwordHistory = jdbcTemplate.query(sql,
					new PassWordHistoryRowMapper(), userId);
		}

		return passwordHistory;
	}

	@Override
	public List<User> searchByFirstName(User users) {

		final List<User> userList = new ArrayList<User>();

		Map<String, Object> in = new HashMap<String, Object>();
		in.put("fname", users.getFirstName());
		in.put("lname", users.getLastName());
		in.put("email", users.getEmail());
		in.put("phone", users.getPhoneNumber());
		in.put("userrole", users.getRole().getRoleName());

		simpleCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(
				"SEARCH_USER").returningResultSet("result",
				new RowMapper<List<User>>() {
					public List<User> mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						User user = new User();
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmail(rs.getString("email_address"));
						user.setPhoneNumber(rs.getString("phone_number"));
						Role role = new Role();
						role.setRoleName(rs.getString("role_name"));
						user.setRole(role);

						userList.add(user);

						return userList;
					}
				});
		simpleCall.execute(in);

		return userList;
	}

	@Override
	public boolean isEmailExist(String emailAddress) throws Exception {

		String sql = "SELECT COUNT(user_id) FROM users WHERE email_address = ? and is_active=1";
		boolean response = false;
		int result = jdbcTemplate.queryForObject(sql,
				new Object[] { emailAddress }, Integer.class);
		if (result > 0) {
			response = true;
		}

		return response;
	}

	@Override
	public boolean deleteUser(User user) throws Exception {

		String sql = "UPDATE users SET is_active = ? WHERE user_id = ?";
		boolean response = false;
		int result = jdbcTemplate.update(sql, new Object[] { 0, user.getId() });
		if (result > 0) {
			response = true;
		}
		return response;
	}

	@Override
	public List<ContactStatus> getStatus() throws Exception {

		List<ContactStatus> statusList = new ArrayList<ContactStatus>();

		statusList = jdbcTemplate.query("call GET_CONTACT_STATUS();",
				new ContactsStatusMapper());

		return statusList;

	}

	@Override
	public List<DaysType> getDaysType() throws Exception {

		List<DaysType> daysList = new ArrayList<DaysType>();

		daysList = jdbcTemplate.query("call GET_DAYS_TYPE();",
				new DaysTypeMapper());

		return daysList;

	}

	@Override
	public List<CampaignContactStatus> getStatusMappings(int id)
			throws Exception {

		List<CampaignContactStatus> mappingList = new ArrayList<CampaignContactStatus>();

		mappingList = jdbcTemplate.query("call GET_STATUS_MAPPING(?);",
				new CampaignContactStatusMapper(), id);

		return mappingList;
	}

	@Override
	public int updatePassword(String password, String emailAddress)
			throws Exception {
		String sql = "UPDATE users SET password = ?, is_active_temp_password = ? WHERE email_address = ?";
		int result = jdbcTemplate.update(sql, password, 1, emailAddress);
		System.out.println("Result : "+result);
		return result;
	}

}
