package com.sensiple.contactsrepository.dao;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.User;

/**
 * @author narasimhareddyk
 *
 */
public interface ConfigurationDAO {

	/**
	 * @param configuration
	 * @return
	 * @throws Exception
	 */
	int saveFieldConfiguration(final Configuration configuration) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Configuration getFieldCongiguration(final long userId) throws Exception;

	/**
	 * @param campaignOwnerName
	 * @return
	 * @throws Exception
	 */
	List<User> getCampaignOwner(final String campaignOwnerName) throws Exception;

	/**
	 * @param serviceOfferingName
	 * @return
	 * @throws Exception
	 */
	List<CampaignServiceOffering> getServiceOffering(final String serviceOfferingName) throws Exception;

	/**
	 * @param campaignConfiguration
	 * @return
	 * @throws Exception
	 */
	int saveCampaingConfiguration(final CampaignConfiguration campaignConfiguration) throws Exception;

	/**
	 * @param startRecord
	 * @param recordToShow
	 * @return
	 * @throws Exception
	 */
	List<CampaignConfiguration> getCampaingConfiguration(final int startRecord,final int recordToShow)throws Exception;

	/**
	 * @param configurationIds
	 * @return
	 * @throws Exception
	 */
	int deConfigureCampaign(final String configurationIds)throws Exception;

	/**
	 * @param status
	 * @return
	 * @throws Exception
	 */
	List<ContactStatus> getContactStatus(final String status)throws Exception;

}
