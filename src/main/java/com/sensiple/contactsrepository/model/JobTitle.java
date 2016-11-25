package com.sensiple.contactsrepository.model;

public class JobTitle {
	
	private int jobTitleId;
	
	private String jobTitleName;
	
	private JobFunction jobFunction;

	public JobFunction getJobFunction() {
		return jobFunction;
	}

	public void setJobFunction(JobFunction jobFunction) {
		this.jobFunction = jobFunction;
	}

	public int getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

}
