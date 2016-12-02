package com.sensiple.contactsrepository.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String convertDateToString(String dateString){
		if(null == dateString){
			return null;
		}
		SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = parseFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyy");
		String result = format.format(date);
		
			
		return result;
	}
}
