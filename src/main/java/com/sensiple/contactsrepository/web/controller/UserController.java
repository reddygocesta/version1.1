package com.sensiple.contactsrepository.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sensiple.contactsrepository.model.CampaignContactStatus;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.DaysType;
import com.sensiple.contactsrepository.model.MailConfig;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.service.UserDetailsService;
import com.sensiple.contactsrepository.utils.Constants;
import com.sensiple.contactsrepository.utils.MailUtil;
import com.sensiple.contactsrepository.utils.PasswordGenerator;

@RestController
public class UserController {

	private Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserDetailsService userDetails;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Autowired
	private VelocityEngine velocityEngine;

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public String forgetPassWord(@RequestBody String emailAddress) {
		
		int result = 0;
		Gson gson = new Gson();
		String jsonResult = Constants.ERROR;
		String password;
		String status = null;
		Session session = null;
		String[] bodyContent = new String[10];
		boolean isMailSent = false;
		String encrypedPassword = null;

		try {
			if (null != emailAddress) {

				result = userDetails.forgotPassword(emailAddress);
				if (result > 0) {
					password = PasswordGenerator.geRandomtPassword();
					String[] toAddress = { emailAddress };

					MailUtil mailUtil = new MailUtil();
					MailConfig mailConfig = new MailConfig();

					session = javaMailSenderImpl.getSession();
					String senderAddress = session
							.getProperty(Constants.USERNAME);

					mailConfig.setFromAddress(senderAddress);
					mailConfig.setToAddress(toAddress);
					mailConfig
							.setSubject(Constants.CHANGE_PASSWORD_EMAIL_SUBJECT);
					mailConfig
							.setBodyTemplate(Constants.FORGOT_PASSWORD_EMAIL_CONTENT_TEMPLATE);

					bodyContent[0] = password == null ? "" : password;
					encrypedPassword = new BCryptPasswordEncoder()
							.encode(password);

					mailConfig.setBodyContent(bodyContent);

					isMailSent = mailUtil.sendMail(velocityEngine,
							javaMailSenderImpl, mailConfig);

					if (isMailSent) {

						int response = userDetails.updatePassword(
								encrypedPassword, emailAddress);
						
						if (response > 0) {
							status = Constants.SUCCESS;
						}

					} else {
						status = Constants.EMAIL_INVITATION_FAILED;
					}
				} else {
					status = Constants.EMAIL_ADDRESS_NOT_EXIT;
				}
			}
			jsonResult = gson.toJson(status);
		} catch (Exception e) {
			LOGGER.error("Exception occured in forgotPassword Service in User Controller"
					+ ExceptionUtils.getStackTrace(e));
		}
		return jsonResult;
	}

	@RequestMapping(value = "/users/addUser", method = RequestMethod.POST)
	public int addUser(@RequestBody User user) {

		int response = 0;
		Session session = null;
		String[] bodyContent = new String[10];
		String randamPassword = null;
		String encrptedPassword = null;

		try {

			long userId = user.getId();
			if (userId <= 0) {
				randamPassword = PasswordGenerator.geRandomtPassword();
				encrptedPassword = new BCryptPasswordEncoder()
						.encode(randamPassword);
				user.setPassword(encrptedPassword);
			}

			response = userDetails.addUser(user);
			if (response == 1 && userId <= 0) {
				String[] toAddress = { user.getEmail() };

				MailUtil mailUtil = new MailUtil();
				MailConfig mailConfig = new MailConfig();

				session = javaMailSenderImpl.getSession();
				String senderAddress = session.getProperty(Constants.USERNAME);

				mailConfig.setFromAddress(senderAddress);
				mailConfig.setToAddress(toAddress);
				mailConfig.setSubject(Constants.ADD_USER_EMAIL_SUBJECT);
				mailConfig
						.setBodyTemplate(Constants.ADD_USER_EMAIL_CONTENT_TEMPLATE);

				bodyContent[0] = randamPassword == null ? "" : randamPassword;

				mailConfig.setBodyContent(bodyContent);

				mailUtil.sendMail(velocityEngine, javaMailSenderImpl,
						mailConfig);

			}
		} catch (Exception e) {

			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}

		return response;
	}

	@RequestMapping(value = "/users/getRole", method = RequestMethod.GET)
	public List<Role> getRole() {

		List<Role> roleList = new ArrayList<Role>();

		try {

			roleList = userDetails.getRole();

		} catch (Exception e) {

			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}

		return roleList;
	}

	@RequestMapping(value = "/users/getUsers", method = RequestMethod.GET)
	public @ResponseBody String getUsersList(
			@RequestParam(value = "startRecord") int startRecord,
			@RequestParam(value = "recordToShow") int recordToShow,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName,
			@RequestParam(value = "emailAddress") String emailAddress,
			@RequestParam(value = "roleName") String roleName,
			@RequestParam(value = "phoneNumber") String phoneNumber) {

		JSONObject userObject = new JSONObject();
		long numberOfRecords = 0;
		List<User> usersList = new ArrayList<User>();
		try {
			usersList = userDetails.getUsersList(startRecord, recordToShow,
					firstName, lastName, emailAddress, roleName, phoneNumber);
			if (usersList != null && !usersList.isEmpty()) {
				numberOfRecords = usersList.get(0).getTotalRecords();
			}
			userObject.put("totalCount", numberOfRecords);
			userObject.put("userList", usersList);

		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}

		return new Gson().toJson(userObject);
	}

	@RequestMapping(value = "/users/changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestBody String passwordRequest) {

		LOGGER.info("Initialize the changePassword method");
		long userId = 0;
		String oldPassword = null;
		String newPassword = null;
		String result = Constants.ERROR;
		int status = 0;
		Gson gson = new Gson();
		String jsonResult = Constants.ERROR;
		;
		boolean passwordStatus = false;
		boolean passwordhistoryStatus = false;
		JSONObject passwordObject = null;

		try {

			passwordObject = new JSONObject(passwordRequest);
			oldPassword = passwordObject.getString(Constants.OLD_PASSWORD);

			newPassword = passwordObject.getString(Constants.NEW_PASSWORD);

			userId = passwordObject.getLong(Constants.USER_ID);

			passwordStatus = userDetails.validatePassword(userId, oldPassword);

			if (!passwordStatus) {
				result = Constants.INVALID_OLD_PASSWORD;
			} else {
				passwordhistoryStatus = userDetails.getPasswordList(userId,
						newPassword);

				if (passwordhistoryStatus) {
					result = Constants.PASSWORD_EXIST_IN_HISTORY;
				}
				if (!oldPassword.equalsIgnoreCase(newPassword)
						&& passwordStatus && !passwordhistoryStatus) {

					status = userDetails.updatePassword(newPassword, userId);

					if (status == 1) {
						result = Constants.SUCCESS;
					}
				} else {
					result = Constants.PASSWORDS_SAME;
				}
			}
			jsonResult = gson.toJson(result);
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}

		return jsonResult;
	}

	@RequestMapping(value = "/users/isEmailExist", method = RequestMethod.POST)
	public @ResponseBody boolean isEmailExist(@RequestBody String emailAddress) {

		boolean isExist = false;
		JSONObject emailObj = null;

		try {

			emailObj = new JSONObject(emailAddress);
			String email = emailObj.getString(Constants.EMAIL_ADDRESS);
			isExist = userDetails.isEmailExist(email);

		} catch (Exception e) {

			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}

		return isExist;
	}

	@RequestMapping(value = "users/deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteUser(@RequestBody User user) {

		boolean isDeleted = false;

		try {

			isDeleted = userDetails.deleteUser(user);

		} catch (Exception e) {

			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}

		return isDeleted;
	}

	@RequestMapping(value = "/users/searchfname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String searchUser(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("Email") String email,
			@RequestParam("Phone") String phone,
			@RequestParam("Role") String role) {

		User user = new User();
		List<User> users = null;
		Role role1 = new Role();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhoneNumber(phone);

		role1.setRoleName(role);
		user.setRole(role1);

		try {
			users = userDetails.searchByFirstName(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = new Gson().toJson(users);

		return json;
	}

	@RequestMapping(value = "/users/getstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getStatus() {

		List<ContactStatus> contactStatusList = new ArrayList<ContactStatus>();
		List<CampaignContactStatus> campaignContactStatusList = null;
		try {
			contactStatusList = userDetails.getStatus();
			for (ContactStatus contactStatus : contactStatusList) {
				campaignContactStatusList = userDetails
						.getCompaignContactStatusList(contactStatus.getId());
				contactStatus
						.setCampaignContactStatus(campaignContactStatusList);
			}
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}

		String json = new Gson().toJson(contactStatusList);

		return json;
	}

	@RequestMapping(value = "/users/getdaystype", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDays() {

		List<DaysType> list = new ArrayList<>();
		try {
			list = userDetails.getDays();
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));

		}
		String json = new Gson().toJson(list);

		return json;
	}

}
