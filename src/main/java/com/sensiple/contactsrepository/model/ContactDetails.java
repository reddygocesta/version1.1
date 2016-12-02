package com.sensiple.contactsrepository.model;


public class ContactDetails {
	
	private long contactId;
	
	private String contactEmailId;
	
	private CompanyDetails companyDetails;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String jobTitle;
	
	private String jobFunction;
	
	private String contactListName;
	
	private String phoneNumber;
	
	private int extension;
	
	private String mobile;
	
	private String contactStatus;
	
	private String approvedBy;
	
	private int totalRecords;
	
	private String contactSource;
	
	private String createdDate;
	
	private int row;
	
	private String createdBy;
	
	private JobFunction jobFunctionObj;
	
	private JobTitle jobTitleObj;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getContactSource() {
		return contactSource;
	}

	public void setContactSource(String contactSource) {
		this.contactSource = contactSource;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getContactEmailId() {
		return contactEmailId;
	}

	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobFunction() {
		return jobFunction;
	}

	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}

	public String getContactListName() {
		return contactListName;
	}

	public void setContactListName(String contactListName) {
		this.contactListName = contactListName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the companyDetails
	 */
	public CompanyDetails getCompanyDetails() {
		return companyDetails;
	}

	/**
	 * @param companyDetails the companyDetails to set
	 */
	public void setCompanyDetails(CompanyDetails companyDetails) {
		this.companyDetails = companyDetails;
	}

	public JobFunction getJobFunctionObj() {
		return jobFunctionObj;
	}

	public void setJobFunctionObj(JobFunction jobFunctionObj) {
		this.jobFunctionObj = jobFunctionObj;
	}

	public JobTitle getJobTitleObj() {
		return jobTitleObj;
	}

	public void setJobTitleObj(JobTitle jobTitleObj) {
		this.jobTitleObj = jobTitleObj;
	}

}
