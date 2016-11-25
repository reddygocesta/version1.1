package com.sensiple.contactsrepository.dao;

import java.util.List;

import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.User;

public interface ConfigurationDAO {

	int saveFieldConfiguration(final Configuration configuration) throws Exception;

	Configuration getFieldCongiguration(final long userId) throws Exception;

	List<User> getCampaignOwner(final String campaignOwnerName) throws Exception;

	List<CampaignServiceOffering> getServiceOffering(final String serviceOfferingName) throws Exception;

	int saveCampaingConfiguration(final CampaignConfiguration campaignConfiguration) throws Exception;

	List<CampaignConfiguration> getCampaingConfiguration(final int startRecord,final int recordToShow)throws Exception;

	int deConfigureCampaign(final String configurationIds)throws Exception;

}
