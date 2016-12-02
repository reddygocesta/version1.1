package com.sensiple.contactsrepository.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.sensiple.contactsrepository.dao.ConfigurationDAO;
import com.sensiple.contactsrepository.model.CampaignConfiguration;
import com.sensiple.contactsrepository.model.CampaignServiceOffering;
import com.sensiple.contactsrepository.model.Configuration;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.service.ConfigurationService;
@Named
public class ConfigurationServiceImpl implements ConfigurationService {

	
	@Inject
	private ConfigurationDAO configurationDAO;
	
	@Override
	public int saveFieldConfiguration(final Configuration configuration)throws Exception {
		return configurationDAO.saveFieldConfiguration(configuration);
	}

	@Override
	public Configuration getFieldCongiguration(final long userId) throws Exception {
	
		return configurationDAO.getFieldCongiguration(userId);
	}

	@Override
	public List<User> getCampaignOwner(final String campaignOwnerName) throws Exception {
		return configurationDAO.getCampaignOwner(campaignOwnerName);
	}

	@Override
	public List<CampaignServiceOffering> getServiceOffering(final String serviceOfferingName) throws Exception {
		return  configurationDAO.getServiceOffering(serviceOfferingName);
	}

	@Override
	public int saveCampaingConfiguration(final CampaignConfiguration campaignConfiguration) throws Exception {
	
		return configurationDAO.saveCampaingConfiguration(campaignConfiguration);
	}

	@Override
	public List<CampaignConfiguration> getCampaingConfiguration(final int startRecord,final int recordToShow) throws Exception {
		return configurationDAO.getCampaingConfiguration(startRecord,recordToShow);
	}

	@Override
	public int deConfigureCampaign(final String configurationIds) throws Exception {
		return configurationDAO.deConfigureCampaign(configurationIds);
	}

	@Override
	public List<ContactStatus> getContactStatus(final String status) throws Exception {
		return configurationDAO.getContactStatus(status);
	}
	

}
