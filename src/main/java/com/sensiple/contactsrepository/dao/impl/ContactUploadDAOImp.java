package com.sensiple.contactsrepository.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.sensiple.contactsrepository.dao.ContactUploadDAO;
import com.sensiple.contactsrepository.mapper.ContactDetailsListRowMapper;
import com.sensiple.contactsrepository.mapper.ContactDetailsRowMapper;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;
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
public JSONObject InsertNewContact(final List<ContactTemp> contactList) {
	
	
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
		in.put("ContactEmailID",contact.getContactEmailID() );
		in.put("firstName",contact.getFirstName() );
		in.put("middleName",contact.getMiddleName() );
		in.put("lastName", contact.getLastName());
		in.put("jobTitle", contact.getJobTitle());
		in.put("jobFunction", contact.getJobFunction());
		in.put("contactSource",contact.getContactSource() );
		in.put("phoneNumber",contact.getPhoneNumber() );
		in.put("extension",contact.getExtension() );
		in.put("mobile",contact.getMobile());
		in.put("companyName",contact.getCompanyName() );
		in.put("companyWebSite",contact.getWebSite());
		in.put("revenue",contact.getRevenue() );
		in.put("companySize", contact.getCompanySize());
		in.put("industry",contact.getIndustry() );
		in.put("sicCode",contact.getSicCode() );
		in.put("companyEmailId",contact.getCompanyEmailId());
		in.put("adressLine1",contact.getAdressLine1() );
		in.put("addressLine2",contact.getAddressLine2() );
		in.put("Suite_no",contact.getSuite() );
		in.put("country",contact.getCountry());
		in.put("state",contact.getState() );
		in.put("City_id", contact.getCity());
		in.put("ZipCode_id",contact.getZipCode());
		in.put("headQuarters",contact.getHeadQuarters());
		in.put("createdBy","temp_user");
		in.put("contactListName",contact.getContactListName());
		   
	    simpleCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("UPLOAD_CONTACT_DETAILS");
	    Map<String,Object> out=simpleCall.execute(in);
	    //get status of uploaded contact new or existing 
	    contactStatus= (String)out.get("out_status");
		if(contactStatus.equalsIgnoreCase("new"))
			newContact=newContact+1;
		else
			exisitingContact=exisitingContact+1;
		
		 }
	
    }
	   JSONObject userObject = new JSONObject();	   
	   userObject.put("successCount", newContact);
	   userObject.put("updateCount", exisitingContact);
	     
	
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
		String sql = "SELECT COUNT(contact_id) FROM contacts_details WHERE  contact_list_name = ? ";
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
		 
		List<ContactDetails> contact = new ArrayList<ContactDetails>();
		try {
			contact = jdbcTemplate.query("call GET_CONTACT_DETAILS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new ContactDetailsListRowMapper(),
					startRecord, recordToShow, contactListName,campaignName, contactStatus, countryName, stateName, jobFunction,revenue,industry,sicCode,
					companySize,contactServiceOffering,companyEmailId,zipcode,companyName,contactSource,website,contactEmailId,
					firstName,lastName,phoneNumber,mobile,jobTitle,contactAddedBy,
					createdDate,updatedDate);

		} catch (Exception e) {
			
		}
		return contact;
	}

	@Override
	public ContactDetails getContactDetails(int contactId) {
		
		List<ContactDetails> contactDetails=new ArrayList<ContactDetails>();
		try{
			
			contactDetails = jdbcTemplate.query("call View_CONTACT_DETAILS(?)",new ContactDetailsRowMapper(),contactId);
			
		}catch(Exception e){
		}
		return contactDetails.get(0);
	}

}
