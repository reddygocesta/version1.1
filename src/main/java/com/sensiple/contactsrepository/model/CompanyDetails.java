package com.sensiple.contactsrepository.model;

import java.util.List;

public class CompanyDetails {
	
	private long companyId;
	
	private String companyName;
	
	private String website;
	
	private String revenue;
	
	private String companySize;
	
	private String industry;
	
	private String sicCode;
	
	private String companyEmailId;
	
	private AddressDetails addressDetail;
	
	private List<AddressDetails> addressDetails;
	
	private CompanySizeDetails companySizeDetails;
	
	private RevenueDetails revenueDetails;
	
	private IndustryDetails industryDetails;
	
	private SicCodeDetails sicCodeDetails;
	
	private long previousCompanyId;

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getSicCode() {
		return sicCode;
	}

	public void setSicCode(String sicCode) {
		this.sicCode = sicCode;
	}

	public String getCompanyEmailId() {
		return companyEmailId;
	}

	public void setCompanyEmailId(String companyEmailId) {
		this.companyEmailId = companyEmailId;
	}

	/**
	 * @return the addressDetail
	 */
	public AddressDetails getAddressDetail() {
		return addressDetail;
	}

	/**
	 * @param addressDetail the addressDetail to set
	 */
	public void setAddressDetail(AddressDetails addressDetail) {
		this.addressDetail = addressDetail;
	}

	public CompanySizeDetails getCompanySizeDetails() {
		return companySizeDetails;
	}

	public void setCompanySizeDetails(CompanySizeDetails companySizeDetails) {
		this.companySizeDetails = companySizeDetails;
	}

	public RevenueDetails getRevenueDetails() {
		return revenueDetails;
	}

	public void setRevenueDetails(RevenueDetails revenueDetails) {
		this.revenueDetails = revenueDetails;
	}

	public IndustryDetails getIndustryDetails() {
		return industryDetails;
	}

	public void setIndustryDetails(IndustryDetails industryDetails) {
		this.industryDetails = industryDetails;
	}

	public SicCodeDetails getSicCodeDetails() {
		return sicCodeDetails;
	}

	public void setSicCodeDetails(SicCodeDetails sicCodeDetails) {
		this.sicCodeDetails = sicCodeDetails;
	}

	public long getPreviousCompanyId() {
		return previousCompanyId;
	}

	public void setPreviousCompanyId(long previousCompanyId) {
		this.previousCompanyId = previousCompanyId;
	}

	
}
