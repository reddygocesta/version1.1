package com.sensiple.contactsrepository.dao.impl;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.sensiple.contactsrepository.dao.ContactUploadDAO;
import com.sensiple.contactsrepository.mapper.AddressDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.CompanyDetailsRowMapper;
import com.sensiple.contactsrepository.mapper.ContactDetailsListRowMapper;
import com.sensiple.contactsrepository.mapper.ContactDetailsRowMapper;
import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;
import com.sensiple.contactsrepository.utils.ProcedureConstant;
import com.sensiple.contactsrepository.utils.SqlConstant;
import com.sensiple.contactsrepository.web.controller.ContactController;

import net.sf.json.JSONObject;

@Repository
public class ContactUploadDAOImp implements ContactUploadDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleCall;
	
	private Logger LOGGER = Logger.getLogger(ContactController.class);
	
/**
 * This method is used to save the new or existing file uploaded contact
 * @param contactList
 * @return
 */
public JSONObject insertNewContact(final List<ContactTemp> contactList) {
	
	
	String contactStatus="";
	int newContact=0,exisitingContact=0;
	if(contactList.size()>0 )
	{
	for(int i=0; i<contactList.size();i++)
      {
	  contactStatus="";
	  ContactTemp contact=new ContactTemp();
	  contact.setContactEmailID(contactList.get(i).getContactEmailID());
      contact.setFirstName(contactList.get(i).getFirstName());
      contact.setMiddleName(contactList.get(i).getMiddleName());
      contact.setLastName(contactList.get(i).getLastName());
      contact.setJobTitle(contactList.get(i).getJobTitle());
      contact.setJobFunction(contactList.get(i).getJobFunction());
      contact.setContactSource(contactList.get(i).getContactSource());
      contact.setPhoneNumber(contactList.get(i).getPhoneNumber());
      contact.setExtension(contactList.get(i).getExtension());
      contact.setMobile(contactList.get(i).getMobile());     
      contact.setCompanyName(contactList.get(i).getCompanyName());
      contact.setWebSite(contactList.get(i).getWebSite());
      contact.setRevenue(contactList.get(i).getRevenue());
      contact.setCompanySize(contactList.get(i).getCompanySize());
      contact.setIndustry(contactList.get(i).getIndustry());
      contact.setSicCode(contactList.get(i).getSicCode());
      contact.setCompanyEmailId(contactList.get(i).getCompanyEmailId());
      contact.setAdressLine1(contactList.get(i).getAdressLine1());
      contact.setAddressLine2(contactList.get(i).getAddressLine2());
      contact.setSuite(contactList.get(i).getSuite());
      contact.setCountry(contactList.get(i).getCountry());
      contact.setState(contactList.get(i).getState());
      contact.setCity(contactList.get(i).getCity());
      contact.setZipCode(contactList.get(i).getZipCode());
      contact.setHeadQuarters(contactList.get(i).getHeadQuarters());
      contact.setCreatedBy(contactList.get(i).getCreatedBy());
	  contact.setContactListName(contactList.get(i).getContactListName());
	 
        
        Map<String, Object> in= new HashMap<String, Object>();
		in.put(ProcedureConstant.CONTACT_EMAILID,contact.getContactEmailID() );
		in.put(ProcedureConstant.CONTACT_FIRSTNAME,contact.getFirstName() );
		in.put(ProcedureConstant.CONTACT_MIDDLENAME,contact.getMiddleName() );
		in.put(ProcedureConstant.CONTACT_LASTNAME, contact.getLastName());
		in.put(ProcedureConstant.CONTACT_JOBTITTLE, contact.getJobTitle());
		in.put(ProcedureConstant.CONTACT_JOBFUNTION, contact.getJobFunction());
		in.put(ProcedureConstant.CONTACT_SOURCE,contact.getContactSource() );
		in.put(ProcedureConstant.CONTACT_PHONENUMBER,contact.getPhoneNumber() );
		in.put(ProcedureConstant.CONTACT_EXTENSION,contact.getExtension() );
		in.put(ProcedureConstant.CONTACT_MOBILE,contact.getMobile());
		in.put(ProcedureConstant.CONTACT_COMPANYNAME,contact.getCompanyName() );
		in.put(ProcedureConstant.CONTACT_COMAPANYWEBSITE,contact.getWebSite());
		in.put(ProcedureConstant.CONTACT_REVENUE,contact.getRevenue() );
		in.put(ProcedureConstant.CONTACT_COMAPNYSIZE, contact.getCompanySize());
		in.put(ProcedureConstant.CONTACT_INDUSTRY,contact.getIndustry() );
		in.put(ProcedureConstant.CONTACT_SICCODE,contact.getSicCode() );
		in.put(ProcedureConstant.CONTACT_COMAPANYEMAILID,contact.getCompanyEmailId());
		in.put(ProcedureConstant.CONTACT_ADDRESS1,contact.getAdressLine1() );
		in.put(ProcedureConstant.CONTACT_ADDRESS2,contact.getAddressLine2() );
		in.put(ProcedureConstant.CONTACT_SUITE,contact.getSuite() );
		in.put(ProcedureConstant.CONTACT_COUNTRY,contact.getCountry());
		in.put(ProcedureConstant.CONTACT_STATE,contact.getState() );
		in.put(ProcedureConstant.CONTACT_CITYID, contact.getCity());
		in.put(ProcedureConstant.CONTACT_ZIPCODE,contact.getZipCode());
		in.put(ProcedureConstant.CONTACT_HEADQUARTERS,contact.getHeadQuarters());
		in.put(ProcedureConstant.CONTACT_CREATEDBY,contact.getCreatedBy());
		in.put(ProcedureConstant.CONTACT_CONTACTLISTNAME,contact.getContactListName());
		   
	    simpleCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName(ProcedureConstant.UPLOAD_CONTACT_DETAILS);
	    Map<String,Object> out=simpleCall.execute(in);
	    //get status of uploaded contact new or existing 
	    contactStatus= (String)out.get(ProcedureConstant.CONTACT_UPLOADOUT);
		if(contactStatus.equalsIgnoreCase(ProcedureConstant.CONTACT_UPLOADSTATUS))
			newContact=newContact+1;
		else
			exisitingContact=exisitingContact+1;
		
		 }
    }
	   //set the counts , no of contact newly inserted and exist contact updated
	   JSONObject userObject = new JSONObject();	   
	   userObject.put(ProcedureConstant.CONTACT_NEW, newContact);
	   userObject.put(ProcedureConstant.CONTACT_UPDATE, exisitingContact);
	     
	
	return userObject;
}
/**
 * This method used to check the already contact name list exist or not
 * @param contactListName
 * @return
 * @throws Exception
 */
 @Override
	public boolean isContactListNameExist(String contactListName) throws Exception {
		String sql = ProcedureConstant.CONTACT_LIST_NAME;
		boolean response = false;
		int result = jdbcTemplate.queryForObject(sql, new Object[] { contactListName }, Integer.class);
		if(result > 0) {
			response = true;
		} 
		return response;
	}
	@Override
	public List<ContactDetails> getContactList(int startRecord, int recordToShow, String contactListName, String contactStatus, 
			String countryName, String stateName, String jobFunction,String revenue,String industry,String sicCode,
			String companySize,String companyEmailId,String zipcode,String companyName,String contactSource,String website,String contactEmailId,
			String firstName,String lastName,String phoneNumber,String mobile, String jobTitle,String contactAddedBy,
			String contactAddedDate,String contactUpdateDate) throws Exception{
		
		String campaignName="";
		String contactServiceOffering="";
		Date createdDate=null;
		Date updatedDate=null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		if(contactAddedDate !=null && contactAddedDate !=""){
			createdDate =formatter.parse(contactAddedDate);
			
		}
		if(contactUpdateDate !=null && contactUpdateDate !=""){
			updatedDate =formatter.parse(contactUpdateDate);
			
		}
		 
			
		
		LOGGER.info("--------------getUsersList  dao method invoked--------------");
		List<ContactDetails> contact = new ArrayList<ContactDetails>();
		try {

			contact = jdbcTemplate.query(SqlConstant.SQL_GET_CONTACT_DETAILS, new ContactDetailsListRowMapper(),
					startRecord, recordToShow, contactListName,campaignName, contactStatus, countryName, stateName, jobFunction,revenue,industry,sicCode,
					companySize,contactServiceOffering,companyEmailId,zipcode,companyName,contactSource,website,contactEmailId,
					firstName,lastName,phoneNumber,mobile,jobTitle,contactAddedBy,
					createdDate,updatedDate);

		} catch (Exception e) {
			LOGGER.error("Exception in getContactsList method " + ExceptionUtils.getStackTrace(e));
		}
		
		return contact;
	}

	@Override
	public ContactDetails getContactDetails(int contactId) {
		LOGGER.info("--------------getContactDetails  dao method invoked--------------");
		List<ContactDetails> contactDetails=new ArrayList<ContactDetails>();
		try{
			
			contactDetails = jdbcTemplate.query(SqlConstant.SQL_VIEW_CONTACT_DETAILS,new ContactDetailsRowMapper(),contactId);
			
		}catch(Exception e){
			LOGGER.error("Exception in getContactDetails method " + ExceptionUtils.getStackTrace(e));
		}
		
		return contactDetails.get(0);
	}
	
	
	/**
	 * This method is used to get the company Name
	 * @param companyName
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CompanyDetails> getCompanyDetails(String companyName) throws Exception {
		
		LOGGER.info("Initiatite the getCompanyNames Details DAO Method");
		
		List<CompanyDetails> companyDetails = new ArrayList<CompanyDetails>();
		try{
			
			companyDetails = jdbcTemplate.query(SqlConstant.GET_COMPANY_DETAILS, new CompanyDetailsRowMapper(), companyName);
			
		}catch(Exception e){
			LOGGER.error("Exception in getCompany method "+ExceptionUtils.getStackTrace(e));
		}
		return companyDetails;
	}
	
	
	/**
	 * This method is used to get the Address Details.
	 * @param companyName
	 * @return list of address
	 * @throws Exception
	 */
	@Override
	public List<AddressDetails> getLocationDetails(String companyId) throws Exception {
		LOGGER.info("Initiatite the getLocation Details DAO Method");
		List<AddressDetails> addressDetails = new ArrayList<AddressDetails>();
		try{
			
			addressDetails = jdbcTemplate.query(SqlConstant.GET_ADDRESS_DETAILS, new AddressDetailsRowMapper(), companyId);
			
		}catch(Exception e){
			LOGGER.error("Exception in getLocation method "+ExceptionUtils.getStackTrace(e));
		}
		return addressDetails;
	}
	
	/**
	 * This method is used to delete the Contact.
	 * @param contactDetails
	 * @return true or false
	 * @throws Exception
	 */
	@Override
	public boolean deleteContact(String contactsId) throws Exception {
		
		LOGGER.info("Initiatite the deleteContact Details DAO Method");
		
		boolean isDeleted = false;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;
		try{
			
			params.addValue(ProcedureConstant.CONTACT_IDS, contactsId);
			
			
	
			final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(ProcedureConstant.DELETE_CONTACT);
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CONTACT_IDS, Types.VARCHAR));
			
			final java.util.Map<String, Object> result = simpleJdbcCall.execute(params);
			response = Integer.parseInt(String.valueOf(result.get(ProcedureConstant.RESULT)));
			LOGGER.debug("response after insert"+response);
			
			if (response > 0) {
				isDeleted = true;
			}
			
		}catch(Exception e){
			LOGGER.error("Exception in deleteContact method "+ExceptionUtils.getStackTrace(e));
		}
		return isDeleted;
	}
	
	@Override
	public int addContactDetails(ContactDetails contactDetails) throws Exception {
		
		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;
		
		params.addValue(ProcedureConstant.CONTACT_EMAILID, contactDetails.getContactEmailId());
		params.addValue(ProcedureConstant.CONTACT_FIRSTNAME, contactDetails.getFirstName());
		params.addValue(ProcedureConstant.CONTACT_MIDDLENAME, contactDetails.getMiddleName());
		params.addValue(ProcedureConstant.CONTACT_LASTNAME, contactDetails.getLastName());
		params.addValue(ProcedureConstant.CONTACT_JOBTITTLE, contactDetails.getJobTitleObj().getJobTitleId());
		params.addValue(ProcedureConstant.CONTACT_JOBFUNTION, contactDetails.getJobFunctionObj().getJobFunctionId());
		params.addValue(ProcedureConstant.CONTACT_SOURCE, contactDetails.getContactSource());
		params.addValue(ProcedureConstant.CONTACT_PHONENUMBER, contactDetails.getPhoneNumber());
		params.addValue(ProcedureConstant.CONTACT_EXTENSION,contactDetails.getExtension() );
		params.addValue(ProcedureConstant.CONTACT_MOBILE,contactDetails.getMobile());
		params.addValue(ProcedureConstant.COMPANY_ID,contactDetails.getCompanyDetails().getCompanyId());
		params.addValue(ProcedureConstant.CONTACT_COMPANYNAME,contactDetails.getCompanyDetails().getCompanyName() );
		params.addValue(ProcedureConstant.CONTACT_COMAPANYWEBSITE,contactDetails.getCompanyDetails().getWebsite());
		params.addValue(ProcedureConstant.CONTACT_REVENUE,contactDetails.getCompanyDetails().getRevenueDetails().getRevenueId() );
		params.addValue(ProcedureConstant.CONTACT_COMAPNYSIZE, contactDetails.getCompanyDetails().getCompanySizeDetails().getSizeId());
		params.addValue(ProcedureConstant.CONTACT_INDUSTRY,contactDetails.getCompanyDetails().getIndustryDetails().getIndustryId() );
		params.addValue(ProcedureConstant.CONTACT_SICCODE,contactDetails.getCompanyDetails().getSicCodeDetails().getSicCodeId() );
		params.addValue(ProcedureConstant.CONTACT_COMAPANYEMAILID,contactDetails.getCompanyDetails().getCompanyEmailId());
		params.addValue(ProcedureConstant.ADDRESS_ID,contactDetails.getCompanyDetails().getAddressDetail().getAddressId());
		params.addValue(ProcedureConstant.CONTACT_ADDRESS1,contactDetails.getCompanyDetails().getAddressDetail().getAddressLine1() );
		params.addValue(ProcedureConstant.CONTACT_ADDRESS2,contactDetails.getCompanyDetails().getAddressDetail().getAddressLine2() );
		params.addValue(ProcedureConstant.CONTACT_SUITE,contactDetails.getCompanyDetails().getAddressDetail().getSuite() );
		params.addValue(ProcedureConstant.CONTACT_COUNTRY,contactDetails.getCompanyDetails().getAddressDetail().getCountry().getCountryId());
		params.addValue(ProcedureConstant.CONTACT_STATE,contactDetails.getCompanyDetails().getAddressDetail().getState().getStateId() );
		params.addValue(ProcedureConstant.CONTACT_CITYID, contactDetails.getCompanyDetails().getAddressDetail().getCity());
		params.addValue(ProcedureConstant.CONTACT_ZIPCODE,contactDetails.getCompanyDetails().getAddressDetail().getZipCode());
		params.addValue(ProcedureConstant.CONTACT_HEADQUARTERS,contactDetails.getCompanyDetails().getAddressDetail().isHeadQuarters());
		params.addValue(ProcedureConstant.CONTACT_CREATEDBY,contactDetails.getCreatedBy());
		params.addValue(ProcedureConstant.CONTACT_CONTACTLISTNAME,contactDetails.getContactListName());
		
		
		
		final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(SqlConstant.ADD_CONTACT_DETAILS);
		
		simpleJdbcCall.declareParameters(new SqlParameter(
				ProcedureConstant.CONTACT_HEADQUARTERS, Types.TINYINT));
		
		final java.util.Map<String, Object> result = simpleJdbcCall
				.execute(params);

		response = Integer.parseInt(String.valueOf(result
				.get(ProcedureConstant.RESULT)));	
		
		return response;
	}
	
	@Override
	public boolean isContactEmailExist(String emailAddress) throws Exception {
		
		boolean response = false;
		int result = jdbcTemplate.queryForObject(SqlConstant.SQL_CONTACT_EMAIL_EXIST,new Object[] {emailAddress },Integer.class);
		if (result > 0) {
			response = true;
		}

		return response;
	}
	@Override
	public boolean isCompanyExist(String companyName, String websiteName)
			throws Exception {
		boolean response = false;
		int result = jdbcTemplate.queryForObject(SqlConstant.SQL_COMPANY_VALIDATION,
				new Object[] { companyName, websiteName }, Integer.class);
		if (result > 0) {
			response = true;
		}

		return response;
		
	}
	
	
}
