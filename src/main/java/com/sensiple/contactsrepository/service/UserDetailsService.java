package com.sensiple.contactsrepository.service;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;

public interface UserDetailsService {

	int addUser(final User user) throws Exception;

	int forgotPassword(final String emailAddress) throws Exception;

	List<Role> getRole() throws Exception;

	Role role(int roleid) throws Exception;

	List<User> getUsersList(int startRecord, int recordToShow,
			String firstName, String lastName, String emailAddress,
			String roleName, String phoneNumber) throws Exception;

	int updatePassword(final String newPassword, final long userId)
			throws Exception;

	boolean validatePassword(final long userId, final String oldPassword)
			throws Exception;

	boolean getPasswordList(final long userId, final String newPassword)
			throws Exception;

	List<User> searchByFirstName(User user) throws Exception;

	boolean isEmailExist(String emailAddress) throws Exception;

	boolean deleteUser(User user) throws Exception;

	List<ContactStatus> getStatus() throws Exception;

	List<DaysType> getDays() throws Exception;

	List<CampaignContactStatus> getCompaignContactStatusList(int id)
			throws Exception;

	int updatePassword(String password, String emailAddress) throws Exception;
}
