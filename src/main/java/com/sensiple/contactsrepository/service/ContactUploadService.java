package com.sensiple.contactsrepository.service;

import java.util.List;

import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;

import net.sf.json.JSONObject;

public interface ContactUploadService {
	
	public JSONObject insertNewContact(List<ContactTemp> newContactList) ;
	
	List<ContactDetails> getContatcList(int startRecord, int recordToShow, String contactListName, String contactStatus, 
			String countryName, String stateName, String jobFunction,String revenue,String industry,String sicCode,
			String companySize,String companyEmailId,String zipcode,String companyName,String contactSource,String website,String contactEmailId,
			String firstName,String lastName,String phoneNumber,String mobile, String jobTitle,String contactAddedBy,
			String contactAddedDate,String contactUpdateDate) throws Exception;
	
	ContactDetails getContactDetails(int contactId);
	
	boolean isContactListNameExist(String contactListName) throws Exception;

	public List<CompanyDetails> getCompanyDetails(String companyName) throws Exception;

	public List<AddressDetails> getLocationDetails(String companyId) throws Exception;

	public boolean deleteContact(String contactsId) throws Exception;
	
	int addContactDetails(final ContactDetails contactDetails) throws Exception;
	
	public boolean isCompanyExist(String companyName, String websiteName) throws Exception;
}
