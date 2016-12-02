package com.sensiple.contactsrepository.dao.impl;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.sensiple.contactsrepository.dao.CampaignDetailsDAO;
import com.sensiple.contactsrepository.mapper.CampaignDetailsListRowMapper;
import com.sensiple.contactsrepository.model.CampaignDetails;
import com.sensiple.contactsrepository.model.campaignContactSearch;
import com.sensiple.contactsrepository.utils.ProcedureConstant;
import com.sensiple.contactsrepository.utils.SqlConstant;

/**
 * This class is used to DAO implementation for campaign Details
 * @author narasimhareddyk
 *
 */
@Named
public class CampaignDAOImpl implements CampaignDetailsDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * This method to  GET Campaign Details with Pagination
	 * @param startRecord
	 * @param recordToShow
	 * @param campaignName
	 * @param campaignNumber
	 * @param businessUnit
	 * @param campaignTypeId
	 * @param ownerName
	 * @param region
	 * @param campaignStatusId
	 * @param serviceOffering
	 * @param campaignRunDate
	 * @return campaign details list
	 * @throws Exception
	 */
	
	@Override
	public List<CampaignDetails> getCampaignList(int startRecord, int recordToShow,
			String campaignName, int campaignNumber, int businessUnit,
			int campaignTypeId, String ownerName, int region, int campaignStatusId,
			String serviceOffering, String campaignRunDate) throws Exception {
		
		List<CampaignDetails> campaign = new ArrayList<CampaignDetails>();

		campaign = jdbcTemplate.query(SqlConstant.GET_CAMPAIGN_DETAILS,
				new CampaignDetailsListRowMapper(), startRecord, recordToShow, campaignName, 
				campaignNumber, businessUnit, campaignTypeId, ownerName, region, campaignStatusId, serviceOffering,
				campaignRunDate);
		
		return campaign;
		
		
	}
	
	public String saveSearchDetails(campaignContactSearch contactSearch) throws Exception {
		
		 String successMsg="";
		 Date createdDate=null;
			Date updatedDate=null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			if(contactSearch.getContactAddedDate() !=null && contactSearch.getContactAddedDate() !=""){
				createdDate =formatter.parse(contactSearch.getContactAddedDate());
				
			}
			if(contactSearch.getContactUpdateDate() !=null && contactSearch.getContactUpdateDate() !=""){
				updatedDate =formatter.parse(contactSearch.getContactUpdateDate());
				
			}
			try {
				final MapSqlParameterSource params = new MapSqlParameterSource();
				if(null != contactSearch.getContactsearchName()){
					params.addValue(ProcedureConstant.CONTACT_SEARCH_NAME,contactSearch.getContactsearchName() );
				}
				else{
					params.addValue(ProcedureConstant.CONTACT_SEARCH_NAME, "");
				}
				if(null != contactSearch.getCountryName()){
					params.addValue(ProcedureConstant.SEARCH_COUNTRY_ID,contactSearch.getCountryName().getCountryId());
				}
				else{
					params.addValue(ProcedureConstant.SEARCH_COUNTRY_ID,0);
				}
				if(null != contactSearch.getStateName()){
				  params.addValue(ProcedureConstant.SEARCH_STATE_ID,contactSearch.getStateName().getStateId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_STATE_ID,0 );
				}
				if(null != contactSearch.getCompanyName()){
				  params.addValue(ProcedureConstant.SEARCH_COMPANY_NAME, contactSearch.getCompanyName());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_COMPANY_NAME, "");
				}
				if(null != contactSearch.getWebsite()){
				  params.addValue(ProcedureConstant.SEARCH_WEBSITE, contactSearch.getWebsite());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_WEBSITE,"");
				}
				if(null != contactSearch.getRevenue()){
				  params.addValue(ProcedureConstant.SEARCH_REVENUE, contactSearch.getRevenue().getRevenueId());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_REVENUE, 0);	
				}
				if(null != contactSearch.getCompanySize()){
				  params.addValue(ProcedureConstant.SEARCH_COMPANY_SIZE,contactSearch.getCompanySize().getSizeId() );
				  }
				else{
				  params.addValue(ProcedureConstant.SEARCH_COMPANY_SIZE,0 );
				}
				if(null != contactSearch.getIndustry()){
				  params.addValue(ProcedureConstant.SEARCH_INDUSTRY,contactSearch.getIndustry().getIndustryId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_INDUSTRY,0 );
				}
				if(null != contactSearch.getSicCode()){
				  params.addValue(ProcedureConstant.SEARCH_SICCODE,contactSearch.getSicCode().getSicCodeId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_SICCODE,0 );	
				}
				if(null != contactSearch.getCompanyEmailId()){
				  params.addValue(ProcedureConstant.SEARCH_COMAPANY_EMAIL_ID,contactSearch.getCompanyEmailId());
				  }
				else{
				  params.addValue(ProcedureConstant.SEARCH_COMAPANY_EMAIL_ID,"");
				}
				if(null != contactSearch.getContactEmailId()){
				  params.addValue(ProcedureConstant.SEARCH_CONTACT_EMAIL_ID,contactSearch.getContactEmailId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_CONTACT_EMAIL_ID,"");
				}
				if(null != contactSearch.getFirstName()){
				  params.addValue(ProcedureConstant.SEARCH_FIRST_NAME,contactSearch.getFirstName());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_FIRST_NAME,"");
				}
				if(null != contactSearch.getLastName()){
				  params.addValue(ProcedureConstant.SEARCH_LAST_NAME,contactSearch.getLastName() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_LAST_NAME,"" );
				}
				if(null != contactSearch.getJobTitle()){
				  params.addValue(ProcedureConstant.SEARCH_JOB_TITTLE, contactSearch.getJobTitle().getJobTitleId());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_JOB_TITTLE, 0);
				}
				if(null != contactSearch.getJobFunction()){
				  params.addValue(ProcedureConstant.SEARCH_JOB_FUNTION,contactSearch.getJobFunction().getJobFunctionId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_JOB_FUNTION,0 );
				}
				if(null != contactSearch.getZipcode()){
				  params.addValue(ProcedureConstant.SEARCH_ZIPCODE,contactSearch.getZipcode() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_ZIPCODE,"" );
				}
				if(null != contactSearch.getPhoneNumber()){
				  params.addValue(ProcedureConstant.SEARCH_PHONE,contactSearch.getPhoneNumber());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_PHONE,"");
				}
				if(null != contactSearch.getMobile()){
				  params.addValue(ProcedureConstant.SEARCH_MOBILE,contactSearch. getMobile());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_MOBILE,"");	
				}
				if(null != contactSearch.getContactSource()){
				  params.addValue(ProcedureConstant.SEARCH_CONTACT_SOURCE,contactSearch.getContactSource() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_CONTACT_SOURCE,"" );
				}
				if(null != contactSearch.getServiceOffering()){
				  params.addValue(ProcedureConstant.SEARCH_SERVICE_OFFERING,contactSearch.getServiceOffering().getId() );
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_SERVICE_OFFERING,0);	
				}
				
				if(null != contactSearch.getContactAddedBy()){
				  params.addValue(ProcedureConstant.SEARCH_ADDED_BY,contactSearch.getContactAddedBy());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_ADDED_BY,"");
				}
				 
				  
				params.addValue(ProcedureConstant.SEARCH_ADDED_DATE,createdDate);
				
				params.addValue(ProcedureConstant.SEARCH_UPDATED_DATE,updatedDate );
				
				if(null != contactSearch.getContactCreatedBy()){
				  params.addValue(ProcedureConstant.SEARCH_CREATED_BY,contactSearch.getContactCreatedBy());
				}
				else{
				  params.addValue(ProcedureConstant.SEARCH_CREATED_BY,0);
				}
				   
				final SimpleJdbcCall simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate).withProcedureName("ADD_CONTACTSEARCH_DETAILS");
				
				
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.CONTACT_SEARCH_NAME, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_COUNTRY_ID, Types.BIGINT));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_STATE_ID, Types.BIGINT));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_COMPANY_NAME, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_WEBSITE, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_REVENUE, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_COMPANY_SIZE, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_INDUSTRY, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_SICCODE, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_COMAPANY_EMAIL_ID, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_CONTACT_EMAIL_ID, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_FIRST_NAME, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_LAST_NAME, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_JOB_TITTLE, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_JOB_FUNTION, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_ZIPCODE, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_PHONE, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_MOBILE, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_CONTACT_SOURCE, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_SERVICE_OFFERING, Types.INTEGER));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_ADDED_BY, Types.VARCHAR));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_ADDED_DATE, Types.DATE));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_UPDATED_DATE, Types.DATE));
				simpleJdbcCall.declareParameters(new SqlParameter(
						ProcedureConstant.SEARCH_CREATED_BY, Types.BIGINT));
				
				
				
				Map<String,Object> out=simpleJdbcCall.execute(params);
				successMsg= (String) out.get("p_result");
			} catch (Exception e) {
					e.printStackTrace();
			}
		    return  successMsg;
	}
	
	
	public boolean issearchNameExist(String SaveSearchName) throws Exception {
		String sql = "SELECT COUNT(serach_id) FROM contact_search_details WHERE  search_name = ?";
		boolean response = false;
		int result = jdbcTemplate.queryForObject(sql, new Object[] { SaveSearchName }, Integer.class);
		if(result > 0) {
			response = true;
		} 
		return response;
	}
	

}
