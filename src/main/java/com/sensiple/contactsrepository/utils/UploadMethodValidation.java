package com.sensiple.contactsrepository.utils;

public class UploadMethodValidation {
	
    
    public static String checkContactEmail(String contactEmail){	
    	
    	String contactEmailMessage = "";
    	if(!UploadValidation.getNullAndEmpty(contactEmail)){
    		contactEmailMessage = contactEmailMessage + "Contact EmailId is not empty";
	    }else if(!UploadValidation.getEmailOrNot(contactEmail)){
	    	contactEmailMessage = contactEmailMessage + "Enter valid Contact EmailId";
	    }else if(!UploadValidation.getFieldLength(contactEmail, UploadValidation.TWOFIFETYFIVE)){
	    	contactEmailMessage = contactEmailMessage + "The Contact EmailId entered exceeds the maximum length";			    	 
	    }else if(contactEmail.startsWith(".")){
	    	contactEmailMessage = contactEmailMessage + "Contact EmailId shouldn't starts with '.'";			    	 
	    }
    	return contactEmailMessage;
    }
    
    public static String checkFirstName(String firstName){
    	String firstNameMessage = "";
    	if(!UploadValidation.getNullAndEmpty(firstName)){
    		firstNameMessage = firstNameMessage + "Firstname is not empty";
	    }else if(!UploadValidation.getContactNameRegex(firstName)){
	    	firstNameMessage = firstNameMessage + "Enter valid Firstname";
	    }else if(!UploadValidation.getFieldLength(firstName, UploadValidation.FIFETY)){
	    	firstNameMessage = firstNameMessage + "The Firstname entered exceeds the maximum length";			    	 
	    }
    	return firstNameMessage;
    }
    
    public static String checkMiddleName(String middleName){
    	String middleNameMessage = "";
    	if(UploadValidation.getNullAndEmpty(middleName)){
		     if(!UploadValidation.getContactNameRegex(middleName)){
		    	 middleNameMessage = middleNameMessage + "Enter valid Middlename";
		     }else if(!UploadValidation.getFieldLength(middleName, UploadValidation.FIFETY)){
		    	 middleNameMessage = middleNameMessage + "The Middlename entered exceeds the maximum length";			    	 
		     }
	    }
    	return middleNameMessage;
    }
    
    public static String checkLastName(String lastName){
	    String lastNameMessage = "";
	    if(!UploadValidation.getNullAndEmpty(lastName)){
	    	lastNameMessage = lastNameMessage + "Lastname is not empty";
	    }else if(!UploadValidation.getContactNameRegex(lastName)){
	    	lastNameMessage = lastNameMessage + "Enter valid Lastname";
	    }else if(!UploadValidation.getFieldLength(lastName, UploadValidation.FIFETY)){
	    	lastNameMessage = lastNameMessage + "The Lastname entered exceeds the maximum length";			    	 
	    }
	    return lastNameMessage;
    }
    
    public static String checkJobTitle(String jobTitle){			     
	    String jobTitleMessage = "";
	    if(!UploadValidation.getNullAndEmpty(jobTitle)){
	    	jobTitleMessage = jobTitleMessage + "Jobtitle is not empty";
	    }/*else if(!UploadValidation.checkAlphanumeric(jobTitle)){
	   	 ErrorMessage = ErrorMessage + "Enter valid Jobtitle";
	    }*/else if(!UploadValidation.getFieldLength(jobTitle, UploadValidation.FIFETY)){
	    	jobTitleMessage = jobTitleMessage + "The Jobtitle entered exceeds the maximum length";			    	 
	    }
	    return jobTitleMessage;
    }
    
    public static String checkJobFunction(String jobFunction){			     
	    String jobFunctionMessage = "";
	    if(!UploadValidation.getNullAndEmpty(jobFunction)){
	    	jobFunctionMessage = jobFunctionMessage + "Jobfunction is not empty";
	    }/*else if(!UploadValidation.checkAlphanumeric(jobFunction)){
	   	 ErrorMessage = ErrorMessage + "Enter valid Jobfunction";
	    }*/else if(!UploadValidation.getFieldLength(jobFunction, UploadValidation.FIFETY)){
	    	jobFunctionMessage = jobFunctionMessage + "The Jobfunction entered exceeds the maximum length";			    	 
	    }
	    return jobFunctionMessage;
    }
    
    public static String checkContactSource(String contactSource){			     
	    String contactSourceMessage = "";
	    if(!UploadValidation.getNullAndEmpty(contactSource)){
	    	contactSourceMessage = contactSourceMessage + "ContactSource is not empty";
	    }else if(!UploadValidation.checkAlphanumeric(contactSource)){
	    	contactSourceMessage = contactSourceMessage + "Enter valid ContactSource";
	    }else if(!UploadValidation.getFieldLength(contactSource, UploadValidation.HUNDRED)){
	    	contactSourceMessage = contactSourceMessage + "The ContactSource entered exceeds the maximum length";			    	 
	    }
	    return contactSourceMessage;
    }
    
    public static String checkPhoneNumber(String phoneNumber){			     
	    String phoneNumberMessage = "";
	    if(!UploadValidation.getNullAndEmpty(phoneNumber)){
	    	phoneNumberMessage = phoneNumberMessage + "Phone number is not empty";
	    }else if(!UploadValidation.checkPhoneNumber(phoneNumber)){
	    	phoneNumberMessage = phoneNumberMessage + "Enter valid Phone number";
	    }else if(!UploadValidation.getFieldLength(phoneNumber, UploadValidation.TWENTY)){
	    	phoneNumberMessage = phoneNumberMessage + "The Phone number entered exceeds the maximum length";			    	 
	    }
	    return phoneNumberMessage;
    }
    			     
    public static String checkExtension(String extension){			     
	    String extensionMessage = "";
	    if(UploadValidation.getNullAndEmpty(extension)){
		     if(!UploadValidation.checkPhoneNumber(extension)){
		    	 extensionMessage = extensionMessage + "Enter valid Extension";
		     }else if(!UploadValidation.getFieldLength(extension, UploadValidation.TEN)){
		    	 extensionMessage = extensionMessage + "The Extension entered exceeds the maximum length";			    	 
		     }
	    }
	    return extensionMessage;
    }
    
    public static String checkMobileNo(String mobileNo){			     
	    String mobileNoMessage = "";
	    if(UploadValidation.getNullAndEmpty(mobileNo)){
		     if(!UploadValidation.checkPhoneNumber(mobileNo)){
		    	 mobileNoMessage = mobileNoMessage + "Enter valid Mobile number";
		     }else if(!UploadValidation.getFieldLength(mobileNo, UploadValidation.TEN)){
		    	 mobileNoMessage = mobileNoMessage + "The Mobile number entered exceeds the maximum length";			    	 
		     }
	    }
	    return mobileNoMessage;
    }
    
    public static String checkCompanyName(String companyName){			     
	    String companyNameMessage = "";
	    if(!UploadValidation.getNullAndEmpty(companyName)){
	    	companyNameMessage = companyNameMessage + "Company name is not empty";
	    }else if(!UploadValidation.checkCompanyName(companyName)){
	    	companyNameMessage = companyNameMessage + "Enter valid Company name";
	    }else if(!UploadValidation.getFieldLength(companyName, UploadValidation.HUNDRED)){
	    	companyNameMessage = companyNameMessage + "The Company name entered exceeds the maximum length";			    	 
	    }
	    return companyNameMessage;
    }
    
    public static String checkWebSite(String webSite){			     
	    String webSiteMessage = "";
	    if(!UploadValidation.getNullAndEmpty(webSite)){
	    	webSiteMessage = webSiteMessage + "Website is not empty";
	    }else if(!UploadValidation.checkAlphanumeric(webSite)){
	    	webSiteMessage = webSiteMessage + "Enter valid Website";
	    }else if(!UploadValidation.getFieldLength(webSite, UploadValidation.FIVEHUNDRED)){
	    	webSiteMessage = webSiteMessage + "The Website entered exceeds the maximum length";			    	 
	    }else if(!UploadValidation.getWebSite(webSite)){
	    	webSiteMessage = webSiteMessage + "Enter valid Website";			    	 
	    }
	    return webSiteMessage;
    }
    
    public static String checkRevenue(String revenue){			     
	    String revenueMessage = "";
	    if(!UploadValidation.getNullAndEmpty(revenue)){
	    	revenueMessage = revenueMessage + "Revenue is not empty";
	    }else if(!UploadValidation.checkNumberOrNot(revenue)){
	    	revenueMessage = revenueMessage + "Enter valid Revenue";
	    }else if(!UploadValidation.getFieldLength(revenue, UploadValidation.TWELVE)){
	    	revenueMessage = revenueMessage + "The Revenue entered exceeds the maximum length";			    	 
	    }
	    return revenueMessage;
    }
    
    public static String checkCompanySize(String companySize){			     
	    String companySizeMessage = "";
	    if(!UploadValidation.getNullAndEmpty(companySize)){
	    	companySizeMessage = companySizeMessage + "Company size is not empty";
	    }else if(!UploadValidation.checkNumberOrNot(companySize)){
	    	companySizeMessage = companySizeMessage + "Enter valid Company size";
	    }else if(!UploadValidation.getFieldLength(companySize, UploadValidation.TWELVE)){
	    	companySizeMessage = companySizeMessage + "The Company size entered exceeds the maximum length";			    	 
	    }
	    return companySizeMessage;
    }
    
    public static String checkIndustry(String industry){			     
	    String industryMessage = "";
	    if(!UploadValidation.getNullAndEmpty(industry)){
	    	industryMessage = industryMessage + "Industry is not empty";
	    }else if(!UploadValidation.getFieldLength(industry, UploadValidation.FIFETY)){
	    	industryMessage = industryMessage + "The Industry entered exceeds the maximum length";			    	 
	    }
	    return industryMessage;
    }
    
    public static String checkSICCode(String sicCode){			     
	    String sicCodeMessage = "";
	    if(UploadValidation.getNullAndEmpty(sicCode)){
		     if(!UploadValidation.getFieldLength(sicCode, UploadValidation.FIFETY)){
		    	 sicCodeMessage = sicCodeMessage + "The SIC code entered exceeds the maximum length";			    	 
		     }
	    }
	    return sicCodeMessage;
    }
    
    public static String checkCompanyEmailId(String companyEmailId){			     
	    String companyEmailIdMessage = "";
	    if(UploadValidation.getNullAndEmpty(companyEmailId)){
		     if(!UploadValidation.getEmailOrNot(companyEmailId)){
		    	 companyEmailIdMessage = companyEmailIdMessage + "Enter valid Company emailid";
		     }else if(!UploadValidation.getFieldLength(companyEmailId, UploadValidation.FIFETY)){
		    	 companyEmailIdMessage = companyEmailIdMessage + "The Company emailid entered exceeds the maximum length";			    	 
		     }
	    }
	    return companyEmailIdMessage;
    }
    		                  
    public static String checkAddressLine1(String addressLine1){			     
	    String addressLine1Message = "";
	    if(UploadValidation.getNullAndEmpty(addressLine1)){
		     if(!UploadValidation.checkAlphanumeric(addressLine1)){
		    	 addressLine1Message = addressLine1Message + "Enter valid Address line1";
		     }else if(!UploadValidation.getFieldLength(addressLine1, UploadValidation.HUNDRED)){
		    	 addressLine1Message = addressLine1Message + "The Address line1 entered exceeds the maximum length";			    	 
		     }
	    }
	    return addressLine1Message;
    }
    
    public static String checkAddressLine2(String addressLine2){			     
	    String addressLine2Message = "";
	    if(UploadValidation.getNullAndEmpty(addressLine2)){
		     if(!UploadValidation.checkAlphanumeric(addressLine2)){
		    	 addressLine2Message = addressLine2Message + "Enter valid Address line2";
		     }else if(!UploadValidation.getFieldLength(addressLine2, UploadValidation.HUNDRED)){
		    	 addressLine2Message = addressLine2Message + "The Address line2 entered exceeds the maximum length";			    	 
		     }				    				     
	    }
	    return addressLine2Message;
    }
    
    public static String checkSuite(String suite){			     
	    String suiteMessage = "";
	    if(UploadValidation.getNullAndEmpty(suite)){
		     if(!UploadValidation.checkAlphanumeric(suite)){
		    	 suiteMessage = suiteMessage + "Enter valid Suite";
		     }else if(!UploadValidation.getFieldLength(suite, UploadValidation.HUNDRED)){
		    	 suiteMessage = suiteMessage + "The Suite entered exceeds the maximum length";			    	 
		     }				     				     
	    }
	    return suiteMessage;
    }
    
    public static String checkCountry(String country){			     
	    String countryMessage = "";
	    if(!UploadValidation.getNullAndEmpty(country)){
	    	countryMessage = countryMessage + "Country is not empty";
	    }else if(!UploadValidation.getFieldLength(country, UploadValidation.FIFETY)){
	    	countryMessage = countryMessage + "The Country entered exceeds the maximum length";			    	 
	    }
	    return countryMessage;
    }
    
    public static String checkState(String state){			     
	    String stateMessage = "";
	    if(!UploadValidation.getNullAndEmpty(state)){
	    	stateMessage = stateMessage + "State is not empty";
	    }else if(!UploadValidation.getFieldLength(state, UploadValidation.FIFETY)){
	    	stateMessage = stateMessage + "The State entered exceeds the maximum length";			    	 
	    }
	    return stateMessage;
    }
    
    
    public static String checkCity(String city){			     
	    String cityMessage = "";
	    if(UploadValidation.getNullAndEmpty(city)){
		     if(!UploadValidation.getFieldLength(city, UploadValidation.FIFETY)){
		    	 cityMessage = cityMessage + "The City entered exceeds the maximum length";			    	 
		     }				    
		 }
	    return cityMessage;
    }
    
    public static String checkZipcode(String zipcode){			     
	    String zipcodeMessage = "";
	    if(UploadValidation.getNullAndEmpty(zipcode)){
		     if(!UploadValidation.checkNumberOrNot(zipcode)){
		    	 zipcodeMessage = zipcodeMessage + "Enter valid Zip code";
		     }else if(!UploadValidation.getFieldLength(zipcode, UploadValidation.TEN)){
		    	 zipcodeMessage = zipcodeMessage + "The Zip code entered exceeds the maximum length";			    	 
		     }				    				     
	    }
	    return zipcodeMessage;
    }
}
