package com.sensiple.contactsrepository.model;

public class StateDetails {

	private long stateId;
	
	private String stateName;
	
	//private CountryDetails country;
	
	private String shortName;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
/*
	public CountryDetails getCountry() {
		return country;
	}

	public void setCountry(CountryDetails country) {
		this.country = country;
	}*/

	 
}
