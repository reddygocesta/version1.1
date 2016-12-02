package com.sensiple.contactsrepository.dao;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.PasswordHistory;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;

public interface UserDetailsDAO {
	
	User findByLogin(String login) throws Exception;
	
	int forgotPassword(final String emailAddress) throws Exception;
	
	public int addUser(final User user) throws Exception; 

	Role role(int roleid) throws Exception;

	List<User> getUsersList(int startRecord, int recordToShow, String firstName, String lastName, String emailAddress, String roleName, String phoneNumber)throws Exception;
	
	int updatePassword(final String newPassword,final long userId) throws Exception;
	
	boolean validatePassword(final long userId,final String oldPassword)throws Exception;
	
	List<PasswordHistory> getPasswordList(final long userId)throws Exception;
	
	boolean isEmailExist(String emailAddress) throws Exception;
	
	boolean deleteUser(User user) throws Exception;
	
	List<ContactStatus> getStatus() throws Exception;
	
	List<DaysType> getDaysType() throws Exception;
	
	List<CampaignContactStatus> getStatusMappings() throws Exception;
	
	int updatePassword(String password, String emailAddress) throws Exception;
}
