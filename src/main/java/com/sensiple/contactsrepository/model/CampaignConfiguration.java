package com.sensiple.contactsrepository.model;


public class CampaignConfiguration {
		
		private long campaignConfigurationId;
		private User campaingOwner;
		private CampaignServiceOffering serviceOffering;
		private StateDetails state;
		private CountryDetails country;
		private int isconfigured;
		private int totalRecords;
		private int row;
		
		
		public StateDetails getState() {
			return state;
		}
		public void setState(StateDetails state) {
			this.state = state;
		}
		public User getCampaingOwner() {
			return campaingOwner;
		}
		public void setCampaingOwner(User campaingOwner) {
			this.campaingOwner = campaingOwner;
		}
		public CampaignServiceOffering getServiceOffering() {
			return serviceOffering;
		}
		public void setServiceOffering(CampaignServiceOffering serviceOffering) {
			this.serviceOffering = serviceOffering;
		}
		
		public CountryDetails getCountry() {
			return country;
		}
		public void setCountry(CountryDetails country) {
			this.country = country;
		}
		public int getIsconfigured() {
			return isconfigured;
		}
		public void setIsconfigured(int isconfigured) {
			this.isconfigured = isconfigured;
		}
		public int getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(int totalRecords) {
			this.totalRecords = totalRecords;
		}
		public long getCampaignConfigurationId() {
			return campaignConfigurationId;
		}
		public void setCampaignConfigurationId(long campaignConfigurationId) {
			this.campaignConfigurationId = campaignConfigurationId;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		
		
		
}
