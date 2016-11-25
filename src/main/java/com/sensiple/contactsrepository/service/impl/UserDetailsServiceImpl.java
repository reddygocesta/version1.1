package com.sensiple.contactsrepository.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sensiple.contactsrepository.dao.UserDetailsDAO;
import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.PasswordHistory;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.service.UserDetailsService;

@Named
public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	private UserDetailsDAO userDetailsDao;

	@Override
	public int addUser(final User user) throws Exception {
		return userDetailsDao.addUser(user);
	}

	@Override
	public int forgotPassword(final String emailAddress) throws Exception {
		return userDetailsDao.forgotPassword(emailAddress);
	}

	@Override
	public List<Role> getRole() throws Exception {

		return userDetailsDao.getRole();
	}

	@Override
	public Role role(int roleid) throws Exception {
		return userDetailsDao.role(roleid);
	}

	@Override
	public List<User> getUsersList(int startRecord, int recordToShow,
			String firstName, String lastName, String emailAddress,
			String roleName, String phoneNumber) throws Exception {
		return userDetailsDao.getUsersList(startRecord, recordToShow,
				firstName, lastName, emailAddress, roleName, phoneNumber);
	}

	@Override
	public int updatePassword(final String newPassword, final long userId)
			throws Exception {
		return userDetailsDao.updatePassword(newPassword, userId);
	}

	@Override
	public boolean validatePassword(final long userId, String oldPassword)
			throws Exception {
		return userDetailsDao.validatePassword(userId, oldPassword);
	}

	@Override
	public boolean getPasswordList(final long userId, final String newPassword)
			throws Exception {
		boolean isValidPassword = false;
		List<PasswordHistory> passwordList = null;
		passwordList = userDetailsDao.getPasswordList(userId);

		for (PasswordHistory password : passwordList) {
			isValidPassword = new BCryptPasswordEncoder().matches(newPassword,
					password.getPassword());

			if (isValidPassword) {
				break;
			}
		}

		return isValidPassword;
	}

	@Override
	public List<User> searchByFirstName(User user) throws Exception {
		return userDetailsDao.searchByFirstName(user);
	}

	@Override
	public boolean isEmailExist(String emailAddress) throws Exception {
		return userDetailsDao.isEmailExist(emailAddress);
	}

	@Override
	public boolean deleteUser(User user) throws Exception {
		return userDetailsDao.deleteUser(user);
	}

	@Override
	public List<ContactStatus> getStatus() throws Exception {
		return userDetailsDao.getStatus();
	}

	@Override
	public List<DaysType> getDays() throws Exception {
		return userDetailsDao.getDaysType();
	}

	@Override
	public List<CampaignContactStatus> getCompaignContactStatusList(int id)
			throws Exception {
		return userDetailsDao.getStatusMappings(id);
	}

	@Override
	public int updatePassword(String password, String emailAddress)
			throws Exception {
		
		return userDetailsDao.updatePassword( password,  emailAddress);
	}
}
