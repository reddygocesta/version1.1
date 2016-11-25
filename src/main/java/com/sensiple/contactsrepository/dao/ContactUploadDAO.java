package com.sensiple.contactsrepository.dao;

import java.util.List;

import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;

import net.sf.json.JSONObject;

public interface ContactUploadDAO {

	public JSONObject InsertNewContact(List<ContactTemp> newContactList) ;
	List<ContactDetails> getContactList(int startRecord, int recordToShow, String contactListName, String contactStatus, 
			String countryName, String stateName, String jobFunction,String revenue,String industry,String sicCode,
			String companySize,String companyEmailId,String zipcode,String companyName,String contactSource,String website,String contactEmailId,
			String firstName,String lastName,String phoneNumber,String mobile, String jobTitle,String contactAddedBy,
			String contactAddedDate,String contactUpdateDate)throws Exception;
	ContactDetails getContactDetails(int contactId);
	boolean isContactListNameExist(String contactListName) throws Exception;
}
