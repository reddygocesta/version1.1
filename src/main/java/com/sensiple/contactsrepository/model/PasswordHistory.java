package com.sensiple.contactsrepository.model;

public class PasswordHistory {
	int password_hostory_id;
	int user_id;
	String password;
	String inserted_by;
	
	
	public int getPassword_hostory_id() {
		return password_hostory_id;
	}
	public void setPassword_hostory_id(int password_hostory_id) {
		this.password_hostory_id = password_hostory_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getInserted_by() {
		return inserted_by;
	}
	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}
}
