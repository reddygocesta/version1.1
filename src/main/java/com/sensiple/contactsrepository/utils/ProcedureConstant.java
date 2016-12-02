package com.sensiple.contactsrepository.utils;

public class ProcedureConstant {

	public static final String USERID = "p_user_id";
	public static final String FIRST_NAME = "p_first_name";
	public static final String LAST_NAME = "p_last_name";
	public static final String EMAIL_ADDRESS = "p_email_address";
	public static final String PASSWORD = "p_password";
    public static final String PHONE_NUMBER = "p_phone_number";
    public static final String ROLE_ID = "p_role_id";
	public static final String EMAIL = "Email";
	public static final String LOGGED_BY = "LOGGED_BY";
	
	
	public static final String USER_ID = "userId";
	public static final String NEW_PASSWORD = "newPassword";
	public static final String RESULT = "p_result";
	
	
	//Configuration Save input parameters
	
	public static final String CAMPAIGN_LEAD_TIME = "p_campaign_lead_time";
	public static final String CAMPAIGN_LEAD_TIME_TYPE = "p_campaign_lead_time_type";
	public static final String ALLOW_ASSIGN_CONTACTS = "p_allow_assign_contacts";
	public static final String ALLOW_ASSIGN_CONTACTS_TYPE = "p_allow_assign_contacts_type";
	public static final String DELETE_THRESHOLD = "p_delete_threshold";
	public static final String DELETE_THRESHOLD_TYPE = "p_delete_threshold_type";
	public static final String SAVE_SEARCH_THRESHOLD = "p_save_search_threshold";
	public static final String SAVE_SEARCH_THRESHOLD_TYPE = "p_save_search_threshold_type";
	public static final String MAXIMUM_CAMPAIGN_ALLOWED = "p_maximum_campaign_allowed";
	public static final String CONTACT_AVAILABILITY = "p_contact_availability";
	public static final String CONTACT_STATUS_ID = "p_contact_status_id";
	public static final String CAMPAIGN_CONTACT_STATUS = "p_campaign_contact_status";
	public static final String CAMPAIGN_CAN_BE_REACHED_STATUS = "p_can_be_reached_contact_status";
	public static final String CAMPAIGN_CAN_NOT_BE_REACHED_STATUS = "p_can_not_be_reached_contact_status";
	public static final String SAVE_FILED_CONFIGURATION = "SAVE_FILED_CONFIGURATION";
	
	//common procedure parameters
	public static final String STATE_ID = "p_state_id";
	public static final String COUNTRY_ID = "p_country_id";
	
	//Campaing Configuration Fields
	public static final String SERVICE_OFFERING = "p_service_offering";
	public static final String SAVE_CAMPAING_CONFIGURATION = "SAVE_CAMPAING_CONFIGURATION";
	
	//contact upload stored procedure parameter Fields
	public static final String CONTACT_EMAILID = "ContactEmailID";
	public static final String CONTACT_FIRSTNAME = "firstName";
	public static final String CONTACT_MIDDLENAME = "middleName";
	public static final String CONTACT_LASTNAME = "lastName";
	public static final String CONTACT_JOBTITTLE = "jobTitle";
	public static final String CONTACT_JOBFUNTION = "jobFunction";
	public static final String CONTACT_SOURCE = "contactSource";
	public static final String CONTACT_PHONENUMBER = "phoneNumber";
	public static final String CONTACT_EXTENSION = "extension";
	public static final String CONTACT_MOBILE = "mobile";
	public static final String CONTACT_COMPANYNAME = "companyName";
	public static final String CONTACT_COMAPANYWEBSITE = "companyWebSite";
	public static final String CONTACT_REVENUE = "revenue";
	public static final String CONTACT_COMAPNYSIZE = "companySize";
	public static final String CONTACT_INDUSTRY = "industry";
	public static final String CONTACT_SICCODE = "sicCode";
	public static final String CONTACT_COMAPANYEMAILID= "companyEmailId";
	public static final String CONTACT_ADDRESS1 = "adressLine1";
	public static final String CONTACT_ADDRESS2 = "addressLine2";
	public static final String CONTACT_SUITE = "Suite_no";
	public static final String CONTACT_COUNTRY = "country";
	public static final String CONTACT_STATE = "state";
	public static final String CONTACT_CITYID = "City_id";
	public static final String CONTACT_ZIPCODE = "ZipCode_id";
	public static final String CONTACT_HEADQUARTERS = "headQuarters";
	public static final String CONTACT_CREATEDBY = "createdBy";
	public static final String CONTACT_CONTACTLISTNAME = "contactListName";
	public static final String CONTACT_UPLOADOUT = "out_status";
	public static final String CONTACT_UPLOADSTATUS = "new";
	public static final String CONTACT_UPLOAD_ERRORCOUNT = "errorCount";
	public static final String CONTACT_UPLOAD_ERRORPATH = "errorPath";
	public static final String UPLOAD_CONTACT_DETAILS = "UPLOAD_CONTACT_DETAILS";
	public static final String CONTACT_NEW = "successCount";
	public static final String CONTACT_UPDATE = "updateCount";
	public static final String CONTACT_UPLOAD_CSV = "csv";
	public static final String CONTACT_UPLOAD_XLS = "xls";
	public static final String CONTACT_UPLOAD_XLSX = "xlsx";
	public static final String CONTACT_LIST_NAME = "SELECT COUNT(contact_id) FROM contacts_details WHERE  contact_list_name = ?";
	
	
	//Add Contacts
	public static final String COMPANY_ID = "CompanyId";
	public static final String ADDRESS_ID = "AddressId";
	
	//contact delete
	public static final String CONTACT_IDS = "p_contact_ids";
	public static final String DELETE_CONTACT = "DELETE_CONTACT";
	
	
	//contact upload error file column headers
	public static final String HEADER_SNO = "S.No";
	public static final String HEADER_EMAILID = "Contact Email ID";
	public static final String HEADER_FIRSTNAME = "First Name";
	public static final String HEADER_MIDDLENAME = "Middle Name";
	public static final String HEADER_LASTNAME = "Last Name";
	public static final String HEADER_JOBTITTLE = "Job Title";
	public static final String HEADER_JOBFUNTION = "Job Function";
	public static final String HEADER_SOURCE = "Contact Source";
	public static final String HEADER_PHONE = "Phone Number";
	public static final String HEADER_EXTENSION = "Extension";
	public static final String HEADER_MOBILE = "Mobile";
	public static final String HEADER_COMPANY = "Company";
	public static final String HEADER_WEBSITE = "Website";
	public static final String HEADER_REVENUE = "Revenue";
	public static final String HEADER_COMPANY_SIZE = "Company Size";
	public static final String HEADER_INDUSTRY = "Industry";
	public static final String HEADER_SICCODE = "SIC code";
	public static final String HEADER_COMPANY_EMAIL = "Company Email Id";
	public static final String HEADER_ADDRESS1 = "Adress Line 1";
	public static final String HEADER_ADDRESS2 = "Address Line 2";
	public static final String HEADER_SUITE = "Suite";
	public static final String HEADER_COUNTRY = "Country";
	public static final String HEADER_STATE = "State";
	public static final String HEADER_CITY = "City";
	public static final String HEADER_ZIP = "Zip";
	public static final String HEADER_HEAD = "Head Quarters";
	public static final String HEADER_ERROR_MESSAGE = "Error Message";
	public static final String ERROR_FILE_TYPE = ".xlsx";
	
	//This section for save the campaign contact search details
	public static final String CONTACT_SEARCH_NAME = "p_search_name";
	public static final String SEARCH_COUNTRY_ID = "p_country_id";
	public static final String SEARCH_STATE_ID = "p_state_id";
    public static final String SEARCH_COMPANY_NAME = "p_company_name";
	public static final String SEARCH_WEBSITE = "p_website";
	public static final String SEARCH_REVENUE = "p_revenue";
    public static final String SEARCH_COMPANY_SIZE = "p_company_size";
	public static final String SEARCH_INDUSTRY = "p_industry";
	public static final String SEARCH_SICCODE = "p_sic_code";
    public static final String SEARCH_COMAPANY_EMAIL_ID = "p_company_email_id";
	public static final String SEARCH_CONTACT_EMAIL_ID = "p_contact_email_id";
	public static final String SEARCH_FIRST_NAME = "p_first_name";
    public static final String SEARCH_LAST_NAME = "p_last_name";
	public static final String SEARCH_JOB_TITTLE = "p_job_title";
	public static final String SEARCH_JOB_FUNTION = "p_job_function";
    public static final String SEARCH_ZIPCODE = "p_zipcode";
	public static final String SEARCH_PHONE = "p_phone_number";
	public static final String SEARCH_MOBILE = "p_mobile";
    public static final String SEARCH_CONTACT_SOURCE= "p_contact_source";
	public static final String SEARCH_SERVICE_OFFERING = "p_service_offering_id";
	public static final String SEARCH_ADDED_BY = "p_added_by";
    public static final String SEARCH_ADDED_DATE = "p_added_date";
	public static final String SEARCH_UPDATED_DATE = "p_updated_date";
	public static final String SEARCH_CREATED_BY = "p_created_by";
	
	
}
