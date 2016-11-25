package com.sensiple.contactsrepository.model;

import java.util.List;

public class CountryDetails {

	private long countryId;
	
	public List<StateDetails> getStates() {
		return states;
	}
	public void setStates(List<StateDetails> states) {
		this.states = states;
	}
	private List<StateDetails> states;
	
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	private String countryName;
}
