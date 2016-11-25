package com.sensiple.contactsrepository.model;

public class AddressDetails {
	
	
	private long addressId;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private CountryDetails country;
	
	private StateDetails state;
	
	private String city;
	
	private String zipCode;
	
	private boolean headQuarters;
	
	private String suite;
	
	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public CountryDetails getCountry() {
		return country;
	}

	public void setCountry(CountryDetails country) {
		this.country = country;
	}

	public StateDetails getState() {
		return state;
	}

	public void setState(StateDetails state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean isHeadQuarters() {
		return headQuarters;
	}

	public void setHeadQuarters(boolean headQuarters) {
		this.headQuarters = headQuarters;
	}

	
	
	
}
