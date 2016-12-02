package com.sensiple.contactsrepository.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import com.sensiple.contactsrepository.utils.SqlConstant;

@Named
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	/**
	 * This method is used to login the user. return success or failure.
	 */
	@Override
	public User findByLogin(String userName) {
		User user = null;

		try {

			user = (User) jdbcTemplate.queryForObject(
					SqlConstant.SQL_GET_AUTHENTICATION_DETAILS,
					new UserDetailsRowMapper(), userName);

		} catch (EmptyResultDataAccessException e) {
			user = new User();

			throw e;
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	/**
	 * This will help the user to validate the forgot password. It will validate
	 * the old password entered, new password and confirm password.
	 * 
	 * @param passwordRequest
	 * @return success or failure details.
	 */
	public int forgotPassword(final String emailAddress) throws Exception {

		int queryResult = 0;
	
		queryResult = jdbcTemplate.queryForObject(SqlConstant.SQL_USER_COUNT, Integer.class,
				emailAddress);

		return queryResult;
	}

	/**
	 * It will send mail for successful user addition and will generate
	 * temporary password.
	 * 
	 * @param user
	 * @return 0's and 1's.
	 */
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
		params.addValue(ProcedureConstant.LOGGED_BY, user.getLoggedBy());

		final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(SqlConstant.ADD_USER_DETAILS);
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
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.LOGGED_BY, Types.BIGINT));

		final java.util.Map<String, Object> result = simpleJdbcCall
				.execute(params);

		response = Integer.parseInt(String.valueOf(result
				.get(ProcedureConstant.RESULT)));

		return response;

	}

	

	/**
	 * This will validate the user and role assigned to the user and re-direct
	 * to the page accordingly.
	 * 
	 * @return list of roles.
	 */
	@Override
	public Role role(int roleid) throws Exception {
	
		Role role = new Role();
		role = jdbcTemplate.queryForObject(SqlConstant.SQL_ROLE, new Object[] { roleid },
				new RoleDetailsMapper());
		return role;
	}

	/**
	 * This method is used to get the list of user details.
	 * 
	 * @param startRecord
	 * @param recordToShow
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param roleName
	 * @param phoneNumber
	 * @return list of user.
	 */
	@Override
	public List<User> getUsersList(int startRecord, int recordToShow,
			String firstName, String lastName, String emailAddress,
			String roleName, String phoneNumber) throws Exception {

		List<User> user = new ArrayList<User>();
		
		user = jdbcTemplate.query(SqlConstant.SQL_GET_USER_DETAILS,
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
				.withProcedureName(SqlConstant.CHANGE_PASSWORD_DETAILS);
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

		password = (String) jdbcTemplate.queryForObject(SqlConstant.SQL_VALIDATE_PASSWORD,
				new Object[] { userId }, String.class);

		if (null != password && null != oldPassword) {
			result = new BCryptPasswordEncoder().matches(oldPassword, password);
		}

		return result;

	}

	public List<PasswordHistory> getPasswordList(final long userId)
			throws Exception {
		
		List<PasswordHistory> passwordHistory = null;

		if (userId > 0) {
	
			passwordHistory = jdbcTemplate.query(SqlConstant.SQL_PASSWORD_LIST,
					new PassWordHistoryRowMapper(), userId);
		}

		return passwordHistory;
	}

	/**
	 * This method is used to check whether entered mail is already available.
	 * 
	 * @param emailAddress
	 * @return true or false.
	 */
	@Override
	public boolean isEmailExist(String emailAddress) throws Exception {

		boolean response = false;
		int result = jdbcTemplate.queryForObject(SqlConstant.SQL_EMAIL_EXIST,
				new Object[] { emailAddress, 1 }, Integer.class);
		if (result > 0) {
			response = true;
		}

		return response;
	}

	/**
	 * This method is used to delete the user.
	 * 
	 * @param user
	 * @return true or false.
	 */
	@Override
	public boolean deleteUser(User user) throws Exception {
		
		boolean response = false;
		int result = jdbcTemplate.update(SqlConstant.SQL_DELETE_USER, new Object[] { 0, user.getId() });
		if (result > 0) {
			response = true;
		}
		return response;
	}

	/**
	 * This method is used to get status of the user.
	 * 
	 * @return list of campaign status.
	 */
	@Override
	public List<ContactStatus> getStatus() throws Exception {

		List<ContactStatus> statusList = new ArrayList<ContactStatus>();

		statusList = jdbcTemplate.query(SqlConstant.SQL_GET_CONTACT_STATUS,
				new ContactsStatusMapper());

		return statusList;

	}

	/**
	 * This method is used to display the days assigned for campaign.
	 * 
	 * @return list of days.
	 */
	@Override
	public List<DaysType> getDaysType() throws Exception {

		List<DaysType> daysList = new ArrayList<DaysType>();

		daysList = jdbcTemplate.query(SqlConstant.SQL_GET_DAYS_TYPE,
				new DaysTypeMapper());

		return daysList;

	}

	/**
	 * This method is used to display the days assigned for campaign.
	 * 
	 * @return list of days.
	 */
	@Override
	public List<CampaignContactStatus> getStatusMappings()
			throws Exception {

		List<CampaignContactStatus> mappingList = new ArrayList<CampaignContactStatus>();

		mappingList = jdbcTemplate.query(SqlConstant.SQL_GET_STATUS_MAPPING,
				new CampaignContactStatusMapper());

		return mappingList;
	}

	/**
	 * It will validate mail, and will send mail for success and failure.
	 * 
	 * @param emailAddress
	 * @return success or failure status.
	 */
	@Override
	public int updatePassword(String password, String emailAddress)
			throws Exception {
		int result = jdbcTemplate.update(SqlConstant.SQL_UPDATE_PASSWORD, password, 1, emailAddress);
		return result;
	}

}
