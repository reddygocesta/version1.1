package com.sensiple.contactsrepository.utils;

public class SqlConstant {
	
	public static final String SQL_GET_AUTHENTICATION_DETAILS = "call GET_AUTHENTICATION_DETAILS(?)";
	public static final String SQL_USER_COUNT = "SELECT count(*) FROM users where email_address = ?";
	public static final String SQL_ROLE_LIST = "SELECT * FROM user_role";
	public static final String SQL_ROLE = "SELECT * FROM user_role WHERE ROLEID = ? )::int";
	public static final String SQL_GET_USER_DETAILS = "call GET_USER_DETAILS(?,?,?,?,?,?,?)";
	public static final String SQL_VALIDATE_PASSWORD = "SELECT password FROM users WHERE user_id =?";
	public static final String SQL_PASSWORD_LIST = "SELECT * FROM password_history WHERE user_id = ?";
	public static final String SQL_EMAIL_EXIST = "SELECT COUNT(user_id) FROM users WHERE email_address = ? and is_active=?";
	public static final String SQL_DELETE_USER = "UPDATE users SET is_active = ? WHERE user_id = ?";
	public static final String SQL_GET_CONTACT_STATUS = "call GET_CONTACT_STATUS();";
	public static final String SQL_GET_DAYS_TYPE = "call GET_DAYS_TYPE();";
	public static final String SQL_GET_STATUS_MAPPING = "call GET_STATUS_MAPPING();";
	public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password = ?, is_active_temp_password = ? WHERE email_address = ?";
	public static final String ADD_USER_DETAILS = "Add_User_Details";
	public static final String CHANGE_PASSWORD_DETAILS = "CHANGE_PASSWORD_DETAILS";
	public static final String SQL_GET_CAMPAIGN_REGION = "call GET_CAMPAIGN_REGION();";
	public static final String SQL_GET_REVENUE_DETAILS = "call GET_REVENUE_DETAILS();";
	public static final String SQL_GET_COUNTRY_DETAILS = "call GET_COUNTRY_DETAILS();";
	public static final String SQL_GET_ALLSTATE_DETAILS = "call GET_ALL_STATE_DETAILS();";
	public static final String SQL_GET_STATE_DETAILS = "call GET_STATE_DETAILS(?);";
	public static final String SQL_GET_COMPANY_SIZE_DETAILS = "call GET_COMPANY_SIZE_DETAILS();";
	public static final String SQL_GET_INDUSTRY_DETAILS = "call GET_INDUSTRY_DETAILS();";
	public static final String SQL_GET_SIC_CODE_DETAILS = "call GET_SIC_CODE_DETAILS(?);";
	public static final String SQL_GET_JOB_TITLE_DETAILS = "call GET_JOB_TITLE_DETAILS();";
	public static final String SQL_GET_JOB_FUNCTION_DETAILS_TITLEWISE = "call GET_JOB_FUNCTION_DETAILS_TITLEWISE(?);";
	public static final String SQL_GET_JOB_FUNCTION_DETAILS = "call GET_JOB_FUNCTION_DETAILS();";
	public static final String SQL_GET_SERVICE_OFFERING_DETAILS = "call GET_SERVICE_OFFERING_DETAILS(?);";
	public static final String SQL_GET_CAMPAIGN_STATUS = "call GET_CAMPAIGN_STATUS();";
	public static final String SQL_GET_CAMPAIGN_TYPE = "call GET_CAMPAIGN_TYPE();";
	public static final String SQL_GET_CAMPAIGN_BUSINESS_UNIT = "call GET_CAMPAIGN_BUSINESS_UNIT();";
	public static final String SQL_VIEW_CONTACT_DETAILS = "call View_CONTACT_DETAILS(?);";
	public static final String SQL_GET_CONTACT_DETAILS = "call GET_CONTACT_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	public static final String ADD_CONTACT_DETAILS = "ADD_CONTACT_DETAILS";
	public static final String SQL_CONTACT_EMAIL_EXIST = "SELECT COUNT(contact_id) FROM contacts_details WHERE contact_email_id = ?";
	
	public static final String GET_CAMPAIGN_DETAILS = "call GET_CAMPAIGN_DETAILS(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String SQL_DELETE_CONTACT = "UPDATE contacts_details SET is_active = ? WHERE contact_id = ?";
	
	public static final String SQL_GET_CAMPAIGN_CATEGORY = "call GET_CAMPAIGN_CATEGORY();";
	public static final String GET_ADDRESS_DETAILS = "call GET_ADDRESS_DETAILS(?)";
	public static final String GET_COMPANY_DETAILS = "call GET_COMPANY_DETAILS(?)";
	public static final String SQL_COMPANY_VALIDATION = "SELECT COUNT(*) FROM company_details WHERE company_name = ? AND website = ?";
	public static final String GET_CONFIGURATION_CAMPAIGN_STATUS = "call GET_CONFIGURATION_CAMPAIGN_STATUS(?)";
	
	
}
