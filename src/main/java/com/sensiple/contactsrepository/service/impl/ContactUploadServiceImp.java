package com.sensiple.contactsrepository.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.sensiple.contactsrepository.dao.ContactUploadDAO;
import com.sensiple.contactsrepository.model.AddressDetails;
import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;
import com.sensiple.contactsrepository.service.ContactUploadService;

import net.sf.json.JSONObject;
@Named
public class ContactUploadServiceImp implements ContactUploadService{

	
	@Inject
	private ContactUploadDAO contactUploadDao;
	
	public JSONObject insertNewContact( List<ContactTemp> newContactList)
	{
		return contactUploadDao.insertNewContact(newContactList);
	}
	@Override
	public List<ContactDetails> getContatcList(int startRecord, int recordToShow, String contactListName, String contactStatus, 
			String countryName, String stateName, String jobFunction,String revenue,String industry,String sicCode,
			String companySize,String companyEmailId,String zipcode,String companyName,String contactSource,String website,String contactEmailId,
			String firstName,String lastName,String phoneNumber,String mobile, String jobTitle,String contactAddedBy,
			String contactAddedDate,String contactUpdateDate) throws Exception {
		return contactUploadDao.getContactList(startRecord, recordToShow, contactListName, contactStatus, countryName, stateName,jobFunction,revenue,industry,sicCode,
				companySize,companyEmailId,zipcode,companyName,contactSource,website,contactEmailId,
				firstName,lastName,phoneNumber,mobile,jobTitle,contactAddedBy,
				contactAddedDate,contactUpdateDate);
	}
	@Override
	public ContactDetails getContactDetails(int contactId) {
		return contactUploadDao.getContactDetails(contactId);
	}
	
	public boolean isContactListNameExist(String contactListName) throws Exception{
		return contactUploadDao.isContactListNameExist(contactListName);
	}
	@Override
	public List<CompanyDetails> getCompanyDetails(String companyName) throws Exception {
		return contactUploadDao.getCompanyDetails(companyName);
	}
	@Override
	public List<AddressDetails> getLocationDetails(String companyId) throws Exception {
		return contactUploadDao.getLocationDetails(companyId);
	}
	@Override
	public boolean deleteContact(String contactsId) throws Exception {
		return contactUploadDao.deleteContact(contactsId);
	}
	@Override
	public int addContactDetails(ContactDetails contactDetails) throws Exception {
		return contactUploadDao.addContactDetails(contactDetails);
	}
	@Override
	public boolean isCompanyExist(String companyName, String websiteName)
			throws Exception {
		return contactUploadDao.isCompanyExist(companyName, websiteName);
	}
	
}
