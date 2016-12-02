package com.sensiple.contactsrepository.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.sensiple.contactsrepository.dao.ConfigurationDAO;
import com.sensiple.contactsrepository.mapper.CampaignOwnerRowMapper;
import com.sensiple.contactsrepository.mapper.CampaignServiceOfferingRowMapper;
import com.sensiple.contactsrepository.mapper.CampaingConfigurationRowMapper;
import com.sensiple.contactsrepository.mapper.ConfigurationRowMapper;
import com.sensiple.contactsrepository.mapper.ContactsStatusMapper;
import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.utils.ProcedureConstant;
import com.sensiple.contactsrepository.utils.SqlConstant;

/**
 * This class to implement the DAO implementation methods for configuration
 * @author narasimhareddyk
 *
 */
@Named
public  class ConfigurationDAOImpl implements ConfigurationDAO {

	private Logger LOGGER = Logger.getLogger(ConfigurationDAOImpl.class);
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	
	/**This method is used to save field configuration
	 * @param configuration
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public int saveFieldConfiguration(final Configuration configuration) {
		LOGGER.info("saveFieldConfiguration method invoked ");
		String canBeReachedArray = null;
		String canNotBeReachedArray = null;
		for(ContactStatus canBeReachedStatus: configuration.getCanBeReachedStatusIds()){
			if(null == canBeReachedArray ){
				canBeReachedArray = canBeReachedStatus.getId()+"";
			}else{
				canBeReachedArray = canBeReachedArray+","+canBeReachedStatus.getId();
			}
		}
		for(ContactStatus canNotBeReachedStatus: configuration.getCanNotBeReachedStatusIds()){
			if(null == canNotBeReachedArray ){
				canNotBeReachedArray = canNotBeReachedStatus.getId()+"";
			}else{
				canNotBeReachedArray = canNotBeReachedArray+","+canNotBeReachedStatus.getId();
			}
		}
		
		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;
		try {
			params.addValue(ProcedureConstant.USERID, configuration.getUserId());
			params.addValue(ProcedureConstant.CAMPAIGN_LEAD_TIME, configuration.getCampaignLeadTime());
			//params.addValue(ProcedureConstant.CAMPAIGN_LEAD_TIME_TYPE, configuration.getCampaignLeadTimeType().getId());
			params.addValue(ProcedureConstant.ALLOW_ASSIGN_CONTACTS, configuration.getAllowAssignContacts());
			//params.addValue(ProcedureConstant.ALLOW_ASSIGN_CONTACTS_TYPE, configuration.getAllowAssignContactsType().getId());
			params.addValue(ProcedureConstant.DELETE_THRESHOLD, configuration.getDeleteThreshold());
			//params.addValue(ProcedureConstant.DELETE_THRESHOLD_TYPE, configuration.getDeleteThresholdType().getId());
			params.addValue(ProcedureConstant.SAVE_SEARCH_THRESHOLD, configuration.getSaveSearchThreshold());
			//params.addValue(ProcedureConstant.SAVE_SEARCH_THRESHOLD_TYPE, configuration.getSaveSearchThresholdType().getId());
			params.addValue(ProcedureConstant.MAXIMUM_CAMPAIGN_ALLOWED, configuration.getMaximumCompaignsAllowed());
			//params.addValue(ProcedureConstant.CONTACT_AVAILABILITY, configuration.getContactAvailability());
			params.addValue(ProcedureConstant.CAMPAIGN_CAN_BE_REACHED_STATUS,canBeReachedArray);
			params.addValue(ProcedureConstant.CAMPAIGN_CAN_NOT_BE_REACHED_STATUS, canNotBeReachedArray);
			
	
			final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(ProcedureConstant.SAVE_FILED_CONFIGURATION);
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.USERID, Types.BIGINT));
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CAMPAIGN_LEAD_TIME, Types.INTEGER));
			/*simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CAMPAIGN_LEAD_TIME_TYPE, Types.INTEGER));*/
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.ALLOW_ASSIGN_CONTACTS, Types.INTEGER));
			/*simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.ALLOW_ASSIGN_CONTACTS_TYPE, Types.INTEGER));*/
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.DELETE_THRESHOLD, Types.INTEGER));
	       /* simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.DELETE_THRESHOLD_TYPE, Types.INTEGER));*/
	        simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.SAVE_SEARCH_THRESHOLD, Types.INTEGER));
	      /*  simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.SAVE_SEARCH_THRESHOLD_TYPE, Types.INTEGER));*/
	        simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.MAXIMUM_CAMPAIGN_ALLOWED, Types.INTEGER));
	       /* simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CONTACT_AVAILABILITY, Types.VARCHAR));
	        simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CAMPAIGN_CONTACT_STATUS, Types.INTEGER));*/
	        simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CAMPAIGN_CAN_BE_REACHED_STATUS, Types.VARCHAR));
	        simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.CAMPAIGN_CAN_NOT_BE_REACHED_STATUS, Types.VARCHAR));
			
			final java.util.Map<String, Object> result = simpleJdbcCall.execute(params);
			response = Integer.parseInt(String.valueOf(result.get(ProcedureConstant.RESULT)));
			LOGGER.debug("response after insert"+response);
			
		}catch(Exception e){
			LOGGER.error("Exception in saveFieldConfiguration method"+ ExceptionUtils.getFullStackTrace(e));
			//throw e;
		}
		return response;
	}

	
	/**
	 * this method is used to get field configuration
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Configuration getFieldCongiguration(long userId) throws Exception {
			LOGGER.info("getFieldCongiguration method  in ConfigurationDAOImpl invoked");
			String sql = null;
			List<Configuration> configurationList = null;
			Configuration configuration = null;
			try{
					sql = "SELECT * FROM configuration";
					configurationList= jdbcTemplate.query(sql, new ConfigurationRowMapper());
					if(null != configurationList){
						configuration = configurationList.get(0);
					}
				
			}catch (EmptyResultDataAccessException e) {
				configuration = new Configuration();
				throw e;
			}catch(Exception e){
				LOGGER.error("Exception in getFieldCongiguration method"+ExceptionUtils.getStackTrace(e));
			}
			LOGGER.info("End getPasswordList in getFieldCongiguration method");
			return configuration;
	}
	
	/**
	 * this method to get campaign owner details for auto complete
	 * @param campaignOwnerName
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<User> getCampaignOwner(final String campaignOwnerName) {
		LOGGER.info("Initiatite the getCampaignOwner Details DAO Method");
		List<User> campaignDetails = new ArrayList<User>();
		try{
			
			campaignDetails = jdbcTemplate.query("call GET_CAMPAIGN_OWNER_DETAILS(?)", new CampaignOwnerRowMapper(),campaignOwnerName);
			
		}catch(Exception e){
			LOGGER.error("Exception in getCampaignOwner method "+ExceptionUtils.getStackTrace(e));
		}
		return campaignDetails;
	}
	
	/**
	 * this method to get service offering for autocomplete
	 * @param serviceOfferingName
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<CampaignServiceOffering> getServiceOffering(final String serviceOfferingName) {
		LOGGER.info("Initiatite the getServiceOffering Details DAO Method");
		List<CampaignServiceOffering> serviceOfferingDetails = new ArrayList<CampaignServiceOffering>();
		try{
			
			serviceOfferingDetails = jdbcTemplate.query("call GET_SERVICE_OFFERING_DETAILS(?)", new CampaignServiceOfferingRowMapper(), serviceOfferingName);
			
		}catch(Exception e){
			LOGGER.error("Exception in getServiceOffering method "+ExceptionUtils.getStackTrace(e));
		}
		return serviceOfferingDetails;
	}
	
	/**
	 * this method to save compaign configuration
	 * @param campaignConfiguration
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveCampaingConfiguration(final CampaignConfiguration campaignConfiguration) throws Exception {
		LOGGER.info("saveFieldConfiguration method invoked ");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		int response = 0;
		try {
			params.addValue(ProcedureConstant.USERID, campaignConfiguration.getCampaingOwner().getId());
			params.addValue(ProcedureConstant.SERVICE_OFFERING, campaignConfiguration.getServiceOffering().getId());
			params.addValue(ProcedureConstant.STATE_ID, campaignConfiguration.getState().getStateId());
			params.addValue(ProcedureConstant.COUNTRY_ID, campaignConfiguration.getCountry().getCountryId());
			
	
			final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(ProcedureConstant.SAVE_CAMPAING_CONFIGURATION);
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.USERID, Types.BIGINT));
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.SERVICE_OFFERING, Types.INTEGER));
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.STATE_ID, Types.BIGINT));
			simpleJdbcCall.declareParameters(new SqlParameter(
					ProcedureConstant.COUNTRY_ID, Types.BIGINT));
			
			final java.util.Map<String, Object> result = simpleJdbcCall.execute(params);
			response = Integer.parseInt(String.valueOf(result.get(ProcedureConstant.RESULT)));
			LOGGER.debug("response after insert"+response);
			
		}catch(Exception e){
			LOGGER.error("Exception in saveFieldConfiguration method"+ ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
		return response;
	}
	
	/**
	 * this method to get campaign configuration list
	 * @param startRecord
	 * @param recordToShow
	 * @return
	 * @throws Exception
	 */

	@Override
	public List<CampaignConfiguration> getCampaingConfiguration(final int startRecord,final int recordToShow) throws Exception {
			LOGGER.info("getCampaingConfiguration  dao method invoked");
			List<CampaignConfiguration> contact = new ArrayList<CampaignConfiguration>();
			try {

				contact = jdbcTemplate.query("call GET_CAMPAIGN_CONFIGURATION_INFORMATION(?,?)", new CampaingConfigurationRowMapper(),
						startRecord, recordToShow);

			} catch (Exception e) {
				LOGGER.error("Exception in getCampaingConfiguration method " + ExceptionUtils.getStackTrace(e));
			}
		
			return contact;
	}
	
	/**
	 * deconfigure campaign
	 * @param configurationIds
	 * @return
	 * @throws Exception
	 */

	@Override
	public int deConfigureCampaign(final String configurationIds) throws Exception {
		
			int response = 0;
			String configureIds[]	=configurationIds.split(",");
			final List<String> deConfigureList = Arrays.asList(configureIds);
			try{
				final String deleteQuery = "DELETE FROM campaign_configuration WHERE campaign_configuration_id = ?";
	
				jdbcTemplate.batchUpdate(deleteQuery, 
		                new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					int id = Integer.parseInt(deConfigureList.get(i));
					ps.setInt(1, id);
				}
		
				public int getBatchSize() {
					return deConfigureList.size();
				}
				});
				response = 1;
			}catch(Exception e){
				response = 0;
				LOGGER.error("Exception in deConfigureCampaign method " + ExceptionUtils.getStackTrace(e));
				throw e;
			}
		return response;
	}


	@Override
	public List<ContactStatus> getContactStatus(String status) throws Exception {
	
		List<ContactStatus> statusList = new ArrayList<ContactStatus>();
		try{
			statusList = jdbcTemplate.query(SqlConstant.GET_CONFIGURATION_CAMPAIGN_STATUS,
					new ContactsStatusMapper(),status);
		}catch (EmptyResultDataAccessException e) {
			statusList = new ArrayList<ContactStatus>();
			throw e;
		} catch (Exception e) {
			throw e;
		}

		return statusList;
	}
	
	

}
