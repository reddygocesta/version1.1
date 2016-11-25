package com.sensiple.contactsrepository.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.sensiple.contactsrepository.model.ContactDetails;
import com.sensiple.contactsrepository.model.ContactTemp;
import com.sensiple.contactsrepository.service.ContactUploadService;
import com.sensiple.contactsrepository.utils.Constants;
import com.sensiple.contactsrepository.utils.ContactsSearch;
import com.sensiple.contactsrepository.utils.UploadMethodValidation;
import com.sensiple.contactsrepository.utils.UploadValidation;

import net.sf.json.JSONObject;

 @RestController
 public class ContactController {

	private Logger LOGGER = Logger.getLogger(ContactController.class);
	List<ContactTemp> successContactList = null;
	List<ContactTemp> errorContactList = null;
	private static final int BUFFER_SIZE = 4096;
	@Autowired
	private ContactUploadService contactUploadservice;
	
	@Inject
	private Environment environment;
	
	/*
	 * GetContactUpload is used to read the csv file from UI. 
	 * We will the check file validation for empty,regex and length of the field
	 * once validation done we will split the record in two file (error and success) 
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/contact/contactUpload", method = RequestMethod.POST)
	public @ResponseBody String getContactUpload(			
		   @RequestParam("file") MultipartFile file,
		   @RequestParam("templateName") String templateName)  {
		
		 LOGGER.info("*******Enter in getContactUpload*****************");   
		 String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		 Gson gson = new Gson();
		 String jsonResult = Constants.ERROR;
		 JSONObject userObject = new JSONObject();
		 successContactList = new ArrayList<ContactTemp>();
		 errorContactList = new ArrayList<ContactTemp>();
		 
		 try{
			 File origionalFile = multipartToFile(file);
			 if(fileExtension.equals("csv")){
				 
				 LOGGER.info("*****uploaded file .csv*********");
				CSVReader reader = null;
				int i = 0;
		       
				reader = new CSVReader(new FileReader(origionalFile));
				String[] line;
				while ((line = reader.readNext()) != null) {
					
			      if(i > 0){
		             ContactTemp contact = new ContactTemp();
		             String ErrorMessage = ""; 
				     String serialNo = line[0];
				     int serialInt = 0;
				     if(UploadValidation.getNullAndEmpty(serialNo)){
				    	 serialInt = Integer.parseInt(serialNo);
				    	 contact.setsNo(serialInt);
				     }	
				      
				     String contactEmail = line[1];
				     String contactError = UploadMethodValidation.checkContactEmail(contactEmail);			     
				     contact.setContactEmailID(contactEmail);			     
				     ErrorMessage = ErrorMessage + contactError;
				       
				     String firstName = line[2];
				     String firstNameError = UploadMethodValidation.checkFirstName(firstName);			     			     
				     ErrorMessage = ErrorMessage + firstNameError;
				     contact.setFirstName(firstName);
				      			     
				     String middleName = line[3];
				     String middleNameError = UploadMethodValidation.checkMiddleName(middleName);			     			     
				     ErrorMessage = ErrorMessage + middleNameError;
				     contact.setMiddleName(middleName);
				     
				     String lastName = line[4];
				     String lastNameError = UploadMethodValidation.checkLastName(lastName);			     			     
				     ErrorMessage = ErrorMessage + lastNameError;
				     contact.setLastName(lastName);
				      			     
				     String jobTitle = line[5];
				     String jobTitleError = UploadMethodValidation.checkJobTitle(jobTitle);		     			     
				     ErrorMessage = ErrorMessage + jobTitleError;
				     contact.setJobTitle(jobTitle);
				     
				     String jobFunction = line[6];
				     String jobFunctionError = UploadMethodValidation.checkJobFunction(jobFunction);			     			     
				     ErrorMessage = ErrorMessage + jobFunctionError;
				     contact.setJobFunction(jobFunction);
				     
				     String contactSource = line[7];
				     String contactSourceError = UploadMethodValidation.checkContactSource(contactSource);		     			     
				     ErrorMessage = ErrorMessage + contactSourceError;
				     contact.setContactSource(contactSource);
				     
				     String phoneNumber = line[8];
				     String phoneNumberError = UploadMethodValidation.checkPhoneNumber(phoneNumber);			     			     
				     ErrorMessage = ErrorMessage + phoneNumberError;
				     contact.setPhoneNumber(phoneNumber);
				     			     
				     String extension = line[9];
				     String extensionError = UploadMethodValidation.checkExtension(extension);			     			     
				     ErrorMessage = ErrorMessage + extensionError;
				     contact.setExtension(extension);
				     
				     String mobileNo = line[10];
				     String mobileNoError = UploadMethodValidation.checkMobileNo(mobileNo);			     			     
				     ErrorMessage = ErrorMessage + mobileNoError;
				     contact.setMobile(mobileNo);
				     
				     String companyName = line[11];
				     String companyNameError = UploadMethodValidation.checkCompanyName(companyName);			     			     
				     ErrorMessage = ErrorMessage + companyNameError;
				     contact.setCompanyName(companyName);
				     
				     String webSite = line[12];
				     String webSiteError = UploadMethodValidation.checkWebSite(webSite);			     			     
				     ErrorMessage = ErrorMessage + webSiteError;
				     contact.setWebSite(webSite);
				     
				     String revenue = line[13];
				     String revenueError = UploadMethodValidation.checkRevenue(revenue);		     			     
				     ErrorMessage = ErrorMessage + revenueError;
				     contact.setRevenue(revenue);
				     
				     String companySize = line[14];
				     String companySizeError = UploadMethodValidation.checkCompanySize(companySize);			     			     
				     ErrorMessage = ErrorMessage + companySizeError;
				     contact.setCompanySize(companySize);
				     
				     String industry = line[15];
				     String industryError = UploadMethodValidation.checkIndustry(industry);			     			     
				     ErrorMessage = ErrorMessage + industryError;
				     contact.setIndustry(industry);
				     
				     String sicCode = line[16];
				     String sicCodeError = UploadMethodValidation.checkSICCode(sicCode);		     			     
				     ErrorMessage = ErrorMessage + sicCodeError;
				     contact.setSicCode(sicCode);
				     
				     String companyEmailId = line[17];
				     String companyEmailIdError = UploadMethodValidation.checkCompanyEmailId(companyEmailId);			     			     
				     ErrorMessage = ErrorMessage + companyEmailIdError;
				     contact.setCompanyEmailId(companyEmailId);
				     		                  
				     String addressLine1 = line[18];
				     String addressLine1Error = UploadMethodValidation.checkAddressLine1(addressLine1);			     			     
				     ErrorMessage = ErrorMessage + addressLine1Error;
				     contact.setAdressLine1(addressLine1);
				     
				     String addressLine2 = line[19];
				     String addressLine2Error = UploadMethodValidation.checkAddressLine2(addressLine2);			     			     
				     ErrorMessage = ErrorMessage + addressLine2Error;
				     contact.setAddressLine2(addressLine2);
				     
				     String suite = line[20];
				     String suiteError = UploadMethodValidation.checkSuite(suite);			     			     
				     ErrorMessage = ErrorMessage + suiteError;
				     contact.setSuite(suite);
				     
				     String country = line[21];
				     String countryError = UploadMethodValidation.checkCountry(country);			     			     
				     ErrorMessage = ErrorMessage + countryError;
				     contact.setCountry(country);
				     
				     String state = line[22];
				     String stateError = UploadMethodValidation.checkState(state);		     			     
				     ErrorMessage = ErrorMessage + stateError;
				     contact.setState(state);
				     			     
				     String city = line[23];
				     String cityError = UploadMethodValidation.checkCity(city);			     			     
				     ErrorMessage = ErrorMessage + cityError;
				     contact.setCity(city);
				     
				     String zipcode = line[24];
				     String zipcodeError = UploadMethodValidation.checkZipcode(zipcode);			     			     
				     ErrorMessage = ErrorMessage + zipcodeError;
				     contact.setZipCode(zipcode);			    
				     
				     contact.setHeadQuarters(line[25]);			     
				     contact.setErrorMessage(ErrorMessage);
				     contact.setContactListName(templateName);
				     
				     if(ErrorMessage.equals("")){
				    	 successContactList.add(contact);
				     }else{
				    	 errorContactList.add(contact);
				     }
				  }
		        i++;
		       }	              
			 }
			 else if(fileExtension.equals("xls") || fileExtension.equals("xlsx")){
				 LOGGER.info("*****uploaded file .xls or .xlsx*********");
				 validateCustomerUploadFile(origionalFile,templateName);
			 }
			 
			String tomcatDir = "";
			if(errorContactList.size()>0)
			   tomcatDir = generateFile(errorContactList,templateName);	        
	        
			if(successContactList.size()>0)
	    	   userObject = contactUploadservice.InsertNewContact(successContactList);
		 
	       userObject.put("errorCount", errorContactList.size());
		   userObject.put("errorPath", tomcatDir);
	  	   jsonResult = gson.toJson(userObject);
	  	   
	   }catch(Exception e){
		   LOGGER.info("Exception in getContactUpload"+e.getMessage());
	   }
	   return jsonResult;
	 }
	
	/*
	 * ValidateCustomerUploadFile is used to read the xls and xlsx file from UI. 
	 * We will the check file validation for empty,regex and length of the field
	 * once validation done we will split the record in two file (error and success) 
	 */
	@SuppressWarnings("deprecation")
	private void validateCustomerUploadFile(File convertedFile,String templateName)throws Exception {
		 
			LOGGER.info("ENTER INTO validateCustomerUploadFile");			
			int j = 0;			
			FileInputStream inputStream = new FileInputStream(convertedFile);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);		
			Iterator<Sheet> iteratorSheet = workbook.iterator();
			successContactList = new ArrayList<ContactTemp>();
			errorContactList = new ArrayList<ContactTemp>();
			
			try{
				
				List<List<HSSFCell>> sheetData = new ArrayList<List<HSSFCell>>();
				while(iteratorSheet.hasNext()){
					
					HSSFSheet firstSheet = (HSSFSheet) iteratorSheet.next();
					Iterator<Row> iterator = firstSheet.iterator();	
					
					if(j == 0){
						int maxNumOfCells = firstSheet.getRow(0).getLastCellNum();
						
						while (iterator.hasNext()) {
							HSSFRow nextRow = (HSSFRow) iterator.next();
							if(nextRow.getRowNum()==0){
								   continue; 
							}
							
							List<HSSFCell> data = new ArrayList<HSSFCell>();
							for (int cellCounter = 0; cellCounter < maxNumOfCells; cellCounter++) {
								
								HSSFCell cell;
								if (nextRow.getCell(cellCounter) == null) {
									cell = nextRow.createCell(cellCounter);
								} else {
									cell = nextRow.getCell(cellCounter);
								}

								data.add(cell);
							}
							sheetData.add(data);							
						}						
					}
					j++;
				}
				
				for (int i = 0; i < sheetData.size(); i++) {
		            List<HSSFCell> list = (List<HSSFCell>) sheetData.get(i);
		            String ErrorMessage = "";
		            ContactTemp contact = new ContactTemp();
		            
		            for (int h = 0; h < list.size(); h++) {
		            	
		                Cell cell = (Cell) list.get(h);
		                String cellTextValue = "";
		                
		                if(cell.getCellType() == cell.CELL_TYPE_NUMERIC) { 
							Double contactInt = cell.getNumericCellValue();
					    	cellTextValue = NumberToTextConverter.toText(contactInt);								    	
						}else if(cell.getCellType() == cell.CELL_TYPE_STRING){
							cellTextValue =  cell.getStringCellValue();
						} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
		                    Boolean booleanValue = cell.getBooleanCellValue();
		                    cellTextValue = String.valueOf(booleanValue);
		                } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
		                	cellTextValue = "";
		                }
		                
		                switch (h) {
						
						    case 0:
		                    	if(UploadValidation.getNullAndEmpty(cellTextValue)){
		                    		int serInt = Integer.parseInt(cellTextValue);
		          			   		contact.setsNo(serInt);
		          			    }
		                        break;
		                    case 1:
			       			     String contactError = UploadMethodValidation.checkContactEmail(cellTextValue);			     
			       			     contact.setContactEmailID(cellTextValue);			     
			       			     ErrorMessage = ErrorMessage + contactError;
		                        break;
		                    case 2:
			       			     String firstNameError = UploadMethodValidation.checkFirstName(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + firstNameError;
			       			     contact.setFirstName(cellTextValue);
		                        break;
		                    case 3:
			       			     String middleNameError = UploadMethodValidation.checkMiddleName(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + middleNameError;
			       			     contact.setMiddleName(cellTextValue);
			       			    break;
		                    case 4:
			       			     String lastNameError = UploadMethodValidation.checkLastName(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + lastNameError;
			       			     contact.setLastName(cellTextValue);
			       			    break;
		                    case 5:
			       			     String jobTitleError = UploadMethodValidation.checkJobTitle(cellTextValue);		     			     
			       			     ErrorMessage = ErrorMessage + jobTitleError;
			       			     contact.setJobTitle(cellTextValue);
			       			    break;
		                    case 6:
			       			     String jobFunctionError = UploadMethodValidation.checkJobFunction(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + jobFunctionError;
			       			     contact.setJobFunction(cellTextValue);
			       			    break;
		                    case 7:
			       			     String contactSourceError = UploadMethodValidation.checkContactSource(cellTextValue);		     			     
			       			     ErrorMessage = ErrorMessage + contactSourceError;
			       			     contact.setContactSource(cellTextValue);
			       			    break;
		                    case 8:
			       			     String phoneNumberError = UploadMethodValidation.checkPhoneNumber(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + phoneNumberError;
			       			     contact.setPhoneNumber(cellTextValue);
			       			    break;
		                    case 9:
			       			     String extensionError = UploadMethodValidation.checkExtension(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + extensionError;
			       			     contact.setExtension(cellTextValue);
			       			    break;
		                    case 10:
			       			     String mobileNoError = UploadMethodValidation.checkMobileNo(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + mobileNoError;
			       			     contact.setMobile(cellTextValue);
			       			    break;
		                    case 11:
			       			     String companyNameError = UploadMethodValidation.checkCompanyName(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + companyNameError;
			       			     contact.setCompanyName(cellTextValue);
			       			    break;
		                    case 12:
			       			     String webSiteError = UploadMethodValidation.checkWebSite(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + webSiteError;
			       			     contact.setWebSite(cellTextValue);
			       			    break;
		                    case 13:
			       			     String revenueError = UploadMethodValidation.checkRevenue(cellTextValue);		     			     
			       			     ErrorMessage = ErrorMessage + revenueError;
			       			     contact.setRevenue(cellTextValue);
			       			    break;
		                    case 14:
			       			     String companySizeError = UploadMethodValidation.checkCompanySize(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + companySizeError;
			       			     contact.setCompanySize(cellTextValue);
			       			    break;
		                    case 15:
			       			     String industryError = UploadMethodValidation.checkIndustry(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + industryError;
			       			     contact.setIndustry(cellTextValue);
			       			    break;
		                    case 16:
			       			     String sicCodeError = UploadMethodValidation.checkSICCode(cellTextValue);		     			     
			       			     ErrorMessage = ErrorMessage + sicCodeError;
			       			     contact.setSicCode(cellTextValue);
			       			    break;
		                    case 17:
			       			     String companyEmailIdError = UploadMethodValidation.checkCompanyEmailId(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + companyEmailIdError;
			       			     contact.setCompanyEmailId(cellTextValue);
			       			    break;
		                    case 18:
			       			     String addressLine1Error = UploadMethodValidation.checkAddressLine1(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + addressLine1Error;
			       			     contact.setAdressLine1(cellTextValue);
			       			    break;
		                    case 19:
			       			     String addressLine2Error = UploadMethodValidation.checkAddressLine2(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + addressLine2Error;
			       			     contact.setAddressLine2(cellTextValue);
			       			    break;
		                    case 20:
			       			     String suiteError = UploadMethodValidation.checkSuite(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + suiteError;
			       			     contact.setSuite(cellTextValue);
			       			    break;
		                    case 21:
			       			     String countryError = UploadMethodValidation.checkCountry(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + countryError;
			       			     contact.setCountry(cellTextValue);
			       			    break;
		                    case 22:
			       			     String stateError = UploadMethodValidation.checkState(cellTextValue);		     			     
			       			     ErrorMessage = ErrorMessage + stateError;
			       			     contact.setState(cellTextValue);
			       			    break;
		                    case 23:
			       			     String cityError = UploadMethodValidation.checkCity(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + cityError;
			       			     contact.setCity(cellTextValue);
			       			    break;
		                    case 24:
			       			     String zipcodeError = UploadMethodValidation.checkZipcode(cellTextValue);			     			     
			       			     ErrorMessage = ErrorMessage + zipcodeError;
			       			     contact.setZipCode(cellTextValue);
			       			    break;
		                    case 25:
		                    	 String headQuarters = cellTextValue;
			       			     contact.setHeadQuarters(headQuarters);
			       			    break;
	                	}
		                
		            }
		            contact.setErrorMessage(ErrorMessage);
				    contact.setContactListName(templateName);
					if(ErrorMessage.equals("")){
				    	 successContactList.add(contact);
				    }else{
				    	 errorContactList.add(contact);
				    }
		        }				
			}catch(Exception e){
				LOGGER.error("Exception in validateCustomerUploadFile"+e.getMessage());
			}
		}
	 
	   /*
		* MultipartToFile is used convert from mutlipartfile to file.  
		*/
	 	public File multipartToFile(MultipartFile multipart)throws IllegalStateException, IOException {
			
	 		LOGGER.info("Enter in multipartToFile");
	 		File convFile = new File(multipart.getOriginalFilename());
			multipart.transferTo(convFile);
			return convFile;
		}
	 	
	   /*
		* GenerateFile is used to xls file generate for error contact record with error
		* message
		*/
	 	public String generateFile(List<ContactTemp> contactFileList,String templateName){	 		
			
	 		LOGGER.info("Enter in generateFile");		
			int hssRow = 0;
			int  cellIndex = 0;
			HSSFWorkbook wb=new HSSFWorkbook();
		      
		    HSSFSheet sheet = null;
		    HSSFRow row = null;
		    HSSFCell cell = null;
		    String tomcatDir = "";
		    
		    try{
			    sheet = wb.createSheet("Default Template");
			    HSSFCellStyle headerCellStyle = wb.createCellStyle();
			    
			    HSSFFont boldFont = wb.createFont();
			    boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			    headerCellStyle.setFont(boldFont);
			    
			    row = sheet.createRow(hssRow);
			    
			    for (int i = 0 ; i < 10 ; i++){
			        sheet.setColumnWidth(i, 6*756);  
			    }
		       
		   	 	cell = row.createCell(0);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("S.No"));
		        cell = row.createCell(1);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Contact Email ID"));
		        cell = row.createCell(2);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("First Name"));
		        cell = row.createCell(3);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Middle Name"));
		        cell = row.createCell(4);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Last Name"));
		        cell = row.createCell(5);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Job Title"));
		        cell = row.createCell(6);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Job Function"));
		        cell = row.createCell(7);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Contact Source"));
		        cell = row.createCell(8);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Phone Number"));
		        cell = row.createCell(9);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Extension"));
		        cell = row.createCell(10);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Mobile"));
		        cell = row.createCell(11);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Company"));
		        cell = row.createCell(12);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Website"));
		        cell = row.createCell(13);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Revenue"));
		        cell = row.createCell(14);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Company Size"));
		        cell = row.createCell(15);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Industry"));
		        cell = row.createCell(16);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("SIC code"));
		        cell = row.createCell(17);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Company Email Id"));
		        cell = row.createCell(18);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Adress Line 1"));
		        cell = row.createCell(19);		       
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Address Line 2"));
		        cell = row.createCell(20);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Suite"));
		        cell = row.createCell(21);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Country"));
		        cell = row.createCell(22);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("State"));
		        cell = row.createCell(23);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("City"));
		        cell = row.createCell(24);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Zip"));
		        cell = row.createCell(25);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Head Quarters"));
		        cell = row.createCell(26);
		        cell.setCellStyle(headerCellStyle);
		        cell.setCellValue(new HSSFRichTextString("Error Message"));
		       		 	
				hssRow++;			
				
				if(contactFileList.size() > 0){
					for(int i=0; i<contactFileList.size();i++){
						
						cellIndex = 0;
						
						row = sheet.createRow(hssRow);
			        	row.setHeightInPoints(18f);
			        	
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(contactFileList.get(i).getsNo());
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getContactEmailID()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getFirstName()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getMiddleName()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getLastName()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getJobTitle()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getJobFunction()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getContactSource()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getPhoneNumber()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getExtension()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getMobile()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getCompanyName()));
			        	
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getWebSite()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getRevenue()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getCompanySize()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getIndustry()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getSicCode()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getCompanyEmailId()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getAdressLine1()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getAddressLine2()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getSuite()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getCountry()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getState()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getCity()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getZipCode()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getHeadQuarters()));
			        	
			        	cellIndex++;
			        	cell = row.createCell(cellIndex);
			        	sheet.setColumnWidth(cellIndex, 6500);
			        	cell.setCellValue(new HSSFRichTextString(contactFileList.get(i).getErrorMessage()));
			        	
			        	hssRow++;
					}
				}
					
				int ind = hssRow;
				for(; ind <= hssRow+100; ind++){
					row = sheet.createRow(ind);
					row.setHeightInPoints(18f);
				}
				hssRow = ind;
				
				if(contactFileList.size()>0){
					
					tomcatDir = System.getProperty("catalina.home");
					tomcatDir = tomcatDir +File.separator+ "ContactRepository";
			        File folder = new File(tomcatDir);
			        if(!folder.exists()) {
			        	folder.mkdir();
			        }
			        	
			        Date date = new Date() ;
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			        String filePath = templateName+dateFormat.format(date)+".xlsx";
			        
			        tomcatDir = tomcatDir+File.separator+filePath;
			        File fileTemp = new File(tomcatDir);
			        if(!fileTemp.exists()) {
			        	fileTemp.createNewFile();
			        }
			        
					FileOutputStream out = new FileOutputStream(fileTemp);
					wb.write(out);
					out.close();
				}
		 	}catch(Exception e){
		 		LOGGER.error("Exception in generateFile"+e.getMessage());
		 	}	
		    return tomcatDir;
	}
	
   /*
	* DoDownload method is used to download the default template
	*/
 	@RequestMapping(value = "/contact/downloadTemplate", method = RequestMethod.GET)
	public void doDownload(Model model,HttpServletRequest request,
        HttpServletResponse response) throws IOException {
	
 		LOGGER.info("Enter in doDownload");
	    String filePath = environment.getProperty("locationpath");
	    getDownload(request,response,filePath);
    }
 	
   /*
	* ContactDownload method is used to download the error file template
	*/
 	@RequestMapping(value = "/contact/contactDownload", method = RequestMethod.GET)
	public void contactDownload(Model model,HttpServletRequest request,
        HttpServletResponse response,@RequestParam("filePath") String filePath) throws IOException {
 		
 		LOGGER.info("Enter in contactDownload");
	    getDownload(request,response,filePath);
    }
 	
   /*
	* GetDownload method is used to write the response in file
	*/
 	public void getDownload(HttpServletRequest request,HttpServletResponse response,
 			String filePath) throws IOException {
 		
 		LOGGER.info("Enter in getDownload");
 		ServletContext context = request.getServletContext();
        String fullPath = filePath;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
 	}
 	
 	@RequestMapping(value = "/contact/getContacts", method = RequestMethod.POST)
	public @ResponseBody String getContactsList(@RequestBody ContactsSearch contactsSearch) {

		JSONObject contactObject = new JSONObject();
		long numberOfRecords = 0;
		System.out
				.println("--------------getContactsList method invoked--------------");
		List<ContactDetails> contactList = new ArrayList<ContactDetails>();
		try {
			contactList = contactUploadservice.getContatcList(contactsSearch.getStartRecord(),contactsSearch.getRecordToShow(),contactsSearch.getContactListName(),contactsSearch.getContactStatus(),
					contactsSearch.getCountryName(),contactsSearch.getStateName(),contactsSearch.getJobFunction(),contactsSearch.getRevenue(),contactsSearch.getIndustry(),
					contactsSearch.getSicCode(),contactsSearch.getCompanySize(),contactsSearch.getCompanyEmailId(),contactsSearch.getZipcode(),contactsSearch.getCompanyName(),
					contactsSearch.getContactSource(),contactsSearch.getWebsite(),contactsSearch.getContactEmailId(),contactsSearch.getFirstName(),contactsSearch.getLastName(),
					contactsSearch.getPhoneNumber(),contactsSearch.getMobile(),contactsSearch.getJobTitle(),contactsSearch.getContactAddedBy(),contactsSearch.getContactAddedDate(),
					contactsSearch.getContactUpdateDate());
					
			if (contactList != null
					&& !contactList.isEmpty()) {
				 numberOfRecords = contactList.get(0)
						.getTotalRecords();
			}
			contactObject.put("totalCount", numberOfRecords);
			contactObject.put("contactList", contactList);
			
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			System.out.println(ExceptionUtils.getStackTrace(e));
		}
		System.out
				.println("--------------getContactsList method invoked-------------- "
						+ contactObject.toString());
		
		return new Gson().toJson(contactObject);
	}
 	
 	@RequestMapping(value = "/contact/viewContact/{contactId}", method = RequestMethod.GET)
	public @ResponseBody String ViewContact(@PathVariable("contactId") final int contactId) {

		JSONObject contactObject = new JSONObject();		
		ContactDetails contact = new ContactDetails();
		try {
			
			contact=contactUploadservice.getContactDetails(contactId);
			
			contactObject.put("contact", contact);
			
		} catch (Exception e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
		}
				
		return new Gson().toJson(contactObject);
	}
 	/**
 	 * This method used to check the already contact name list exist or not
 	 * @param contactListName
 	 * @return
 	 * @throws Exception
 	 */
 	
	@RequestMapping(value = "/contact/isContactListExist", method = RequestMethod.POST)
	public @ResponseBody boolean isContactListNameExist(@RequestBody String contactListName) throws Exception  {
		boolean isExist = false;
		org.codehaus.jettison.json.JSONObject passwordObject = null;
		passwordObject = new org.codehaus.jettison.json.JSONObject(contactListName);
		String contactList = passwordObject.getString("contactListName");
		isExist = contactUploadservice.isContactListNameExist(contactList);
		return isExist;
	}
	
}
