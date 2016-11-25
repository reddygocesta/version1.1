package com.sensiple.contactsrepository.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.sensiple.contactsrepository.model.CompanySizeDetails;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.IndustryDetails;
import com.sensiple.contactsrepository.model.RevenueDetails;
import com.sensiple.contactsrepository.model.SicCodeDetails;
import com.sensiple.contactsrepository.model.StateDetails;

public class UploadSessionValidation {
	
	
	public JSONObject checkIndustrycode(String industry,String siccode,HttpServletRequest request) {
 		HttpSession session=request.getSession();
 		
 		List<IndustryDetails> industryDetailslist = null;
 		ContactsSession contactsSession;
 		JSONObject countryObject = new JSONObject();
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			industryDetailslist=contactsSession.getIndustryDetails();
	 			
	    	Boolean  industryExisit=false,sicCodeExisit=false;
	         int industryCode=0,sicCode=0;
	  	    for (IndustryDetails industryDetails : industryDetailslist) {
	  		       if(industryDetails.getIndustryName().equalsIgnoreCase(industry))
	 				{	
	  		    	    industryCode=industryDetails.getIndustryId();
	 					industryExisit=true;
	 					List<SicCodeDetails> sicList=new ArrayList<SicCodeDetails>();
	 					for (SicCodeDetails siccodeList : sicList) {
	 						if(siccodeList.getSicCodeName().equalsIgnoreCase(siccode))
	 		 				{
	 							sicCode=siccodeList.getSicCodeId();
	 							sicCodeExisit=true;
	 							break;
	 		 				}
	 						else
	 						{
	 							sicCodeExisit=false;
	 						}
	 					}
	 				}
	 				else
	 				{
	 					industryExisit=false;
	 				}
	  		       
	 			}
	  	   
	  	     countryObject.put("industryExisit", industryExisit);
		     countryObject.put("countryCode", industryCode);
		     countryObject.put("sicCodeExisit", sicCodeExisit);
		     countryObject.put("stateCode", sicCode);
		   }
	 	} catch (Exception e) {
 		
 		      e.printStackTrace();
 		}
 		
 		return countryObject;
 		
 	}
	public JSONObject checkCountry(String country,String State,HttpServletRequest request) {
 		HttpSession session=request.getSession();
 		
 		List<CountryDetails> countryDetailslist = null;
 		ContactsSession contactsSession;
 		JSONObject countryObject = new JSONObject();
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			countryDetailslist=contactsSession.getCountryList();
	 			
	    	Boolean  countryExisit=false,stateExisit=false;
	        long countryCode=0,stateCode=0;
	  	    for (CountryDetails countryDetails : countryDetailslist) {
	  		       if(countryDetails.getCountryName().equalsIgnoreCase(country))
	 				{	
	 					countryCode=countryDetails.getCountryId();
	 					countryExisit=true;
	 					List<StateDetails> stateList=new ArrayList<StateDetails>();
	 					for (StateDetails stateListDetails : stateList) {
	 						if(stateListDetails.getStateName().equalsIgnoreCase(State))
	 		 				{
	 							stateCode=stateListDetails.getStateId();
	 							stateExisit=true;
	 							break;
	 		 				}
	 						else
	 						{
	 							stateExisit=false;
	 						}
	 					}
	 				}
	 				else
	 				{
	 				  countryExisit=false;
	 				}
	  		       
	 			}
	  	   
	  	     countryObject.put("countryExisit", countryExisit);
		     countryObject.put("countryCode", countryCode);
		     countryObject.put("stateExisit", stateExisit);
		     countryObject.put("stateCode", stateCode);
		   }
	 		
	 		
	 		
 		} catch (Exception e) {
 		
 		      e.printStackTrace();
 		}
 		
 		return countryObject;
 		
 	}
	
	
	public JSONObject checkRevenveRange(String revenueName,HttpServletRequest request) {
 	    HttpSession session=request.getSession();
 		List<RevenueDetails> revenueDetailslist = null;
 		ContactsSession contactsSession;
 		JSONObject revenueObject = new JSONObject();
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			revenueDetailslist=contactsSession.getRevenueDetailslist();
	 			
	    	Boolean  revenueExisit=false;
	        int revenueId=0;
	  	    for (RevenueDetails revenueDetails : revenueDetailslist) {
	  		       if(revenueDetails.getRevenueName().equalsIgnoreCase(revenueName))
	 				{	
	  		    	    revenueId=revenueDetails.getRevenueId();
	  		    	    revenueExisit=true;
	 					break;
	 				}
	 				else
	 				{
	 					revenueExisit=false;
	 				}
	 			}
	  	   
	  	  revenueObject.put("revnueExisit", revenueExisit);
	  	  revenueObject.put("revnueCode", revenueId);
		    
		   }
 		} catch (Exception e) {
 		
 		      e.printStackTrace();
 		}
 		
 		return revenueObject;
 		
 	}
	
	public JSONObject checkCompanySize(String companySize,HttpServletRequest request) {
 	    HttpSession session=request.getSession();
 		List<CompanySizeDetails> companySizeDetailslist = null;
 		ContactsSession contactsSession;
 		JSONObject companySizeObject = new JSONObject();
 		try {
	 		if(session.getAttribute("contactsSession")!=null){
	 			contactsSession=(ContactsSession)session.getAttribute("contactsSession");
	 			companySizeDetailslist=contactsSession.getCompanySizeDetails();
	 			
	    	Boolean  companySizeExisit=false;
	        int companySizeId=0;
	  	    for (CompanySizeDetails companyDetails : companySizeDetailslist) {
	  		       if(companyDetails.getCompanySizeName().equalsIgnoreCase(companySize))
	 				{	
	  		    	 companySizeId=companyDetails.getSizeId();
	  		    	 companySizeExisit=true;
	 					break;
	 				}
	 				else
	 				{
	 				 companySizeExisit=false;
	 				}
	 			}
	  	   
	  	   companySizeObject.put("companySizeExisit", companySizeExisit);
	  	   companySizeObject.put("companySizeId", companySizeId);
		    
		   }
 		} catch (Exception e) {
 		
 		      e.printStackTrace();
 		}
 		
 		return companySizeObject;
 		
 	}
	
	
	
	
	

}
