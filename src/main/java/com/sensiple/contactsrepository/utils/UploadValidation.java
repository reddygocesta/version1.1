package com.sensiple.contactsrepository.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UploadValidation {

	public static int TWOFIFETYFIVE = 255;
	public static int FIFETY = 50;
	public static int HUNDRED = 100;
	public static int TWENTY = 20;
	public static int TEN = 10;
	public static int FIVEHUNDRED = 500;
	public static int TWELVE = 12;
	
	public static boolean getNullAndEmpty(String text){
		
		if(text == null || text.length() == 0 || text.equals("null") || text.equals("")){
			return false;			
		}
		return true;
	}

 	public static boolean getEmailOrNot(String emailId){
		String regex = "^[A-Za-z0-9_.]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
 		Matcher matcher = pattern.matcher(emailId);
		if(matcher.matches()){
			return true;			
		}
		return false;
	}
 	
 	public static boolean getFieldLength(String FieldValue,int fieldLength){
		
 		int tempFieldLen = FieldValue.length();
 		if(tempFieldLen <= fieldLength){
 			return true;
 		} 		
		return false;
	}
 	
 	public static boolean getContactNameRegex(String fieldName){
		String regex = "^[A-Za-z0-9.' ]*$";
        Pattern pattern = Pattern.compile(regex);
 		Matcher matcher = pattern.matcher(fieldName);
		if(matcher.matches()){
			return true;			
		}
		return false;
	}
 	
 	public static boolean checkAlphanumeric(String fieldName){
		String regex = "^[a-zA-Z0-9][\\$#\\+{}:\\?\\.,~@\"a-zA-Z0-9// ]+$";
        Pattern pattern = Pattern.compile(regex);
 		Matcher matcher = pattern.matcher(fieldName);
		if(matcher.matches()){
			return true;			
		}
		return false;
	}
 	
 	public static boolean checkPhoneNumber(String num){		
		
		String regex = "^[0-9()-]+$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(num).matches()) {
			return true;
		} 
		return false;		
	}

	public static boolean checkCompanyName(String num){		
		
		String regex = "^[A-Za-z0-9-.@ ]+$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(num).matches()) {
			return true;
		} 
		return false;		
	}
	
	public static boolean checkNumberOrNot(String num){		
		
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		if(pattern.matcher(num).matches()) {
			return true;
		} 
		return false;		
	}

	public static boolean getWebSite(String webSite){		
		
		if(webSite.startsWith("www.") || webSite.startsWith("http://") || webSite.startsWith("https://")) {
			return true;
		} 
		return false;		
	}
 
}