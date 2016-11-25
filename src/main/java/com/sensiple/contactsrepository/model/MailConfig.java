package com.sensiple.contactsrepository.model;

public class MailConfig {
	private String fromAddress;
	private String[] toAddress;
	private String subject;
	private String bodyTemplate;
	private String[] bodyContent;
	
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String[] getToAddress() {
		return toAddress;
	}
	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}
	public String getBodyTemplate() {
		return bodyTemplate;
	}
	public void setBodyTemplate(String bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}
	public String[] getBodyContent() {
		return bodyContent;
	}
	public void setBodyContent(String[] bodyContent) {
		this.bodyContent = bodyContent;
	}
}
