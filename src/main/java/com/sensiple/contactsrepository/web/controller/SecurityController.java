package com.sensiple.contactsrepository.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sensiple.contactsrepository.dao.CommonDAO;
import com.sensiple.contactsrepository.dao.UserDetailsDAO;
import com.sensiple.contactsrepository.model.CampaignBusinessUnit;
import com.sensiple.contactsrepository.model.CampaignCategory;
import com.sensiple.contactsrepository.model.CampaignRegion;
import com.sensiple.contactsrepository.model.CampaignStatus;
import com.sensiple.contactsrepository.model.CampaignType;
import com.sensiple.contactsrepository.model.CompanySizeDetails;
import com.sensiple.contactsrepository.model.ContactServiceOffering;
import com.sensiple.contactsrepository.model.ContactStatus;
import com.sensiple.contactsrepository.model.CountryDetails;
import com.sensiple.contactsrepository.model.IndustryDetails;
import com.sensiple.contactsrepository.model.JobFunction;
import com.sensiple.contactsrepository.model.JobTitle;
import com.sensiple.contactsrepository.model.RevenueDetails;
import com.sensiple.contactsrepository.model.Role;
import com.sensiple.contactsrepository.model.StateDetails;
import com.sensiple.contactsrepository.model.User;
import com.sensiple.contactsrepository.security.SecurityUtils;
import com.sensiple.contactsrepository.utils.ContactsSession;

@RestController
public class SecurityController {	

	private final Logger LOGGER = LoggerFactory
			.getLogger(SecurityController.class);

	@Autowired
	private UserDetailsDAO userDetailsDAO;

	@Autowired
	private CommonDAO commonDAO;

	@RequestMapping(value = "/security/account", method = RequestMethod.GET)
	public @ResponseBody User getUserAccount(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = null;
		ContactsSession contactsSession = new ContactsSession();

		try {

			user = userDetailsDAO.findByLogin(SecurityUtils.getCurrentLogin());
			user.setPassword(null);

			if (session.getAttribute("contactsSession") == null) {

				List<Role> roleList = commonDAO
						.getRole();
				contactsSession.setRoleList(roleList);
				
				List<CountryDetails> countryList = commonDAO
						.getCountryDetails();
				contactsSession.setCountryList(countryList);
				
				List<StateDetails> stateList = commonDAO.getStateDetails();
				contactsSession.setStateList(stateList);

				List<ContactStatus> contactStatuslist = commonDAO.getStatus();
				contactsSession.setContactStatuslist(contactStatuslist);

				List<RevenueDetails> revenueDetailslist = commonDAO
						.getRevenueDetails();
				contactsSession.setRevenueDetailslist(revenueDetailslist);

				List<CompanySizeDetails> companySizeDetails = commonDAO
						.getCompanySizeDetails();
				contactsSession.setCompanySizeDetails(companySizeDetails);

				List<IndustryDetails> industryDetails = commonDAO
						.getIndustryDetails();
				contactsSession.setIndustryDetails(industryDetails);

				List<JobTitle> jobTitleList = commonDAO.getJobTitleList();
				contactsSession.setJobTitleList(jobTitleList);

				List<JobFunction> jobFunctionList = commonDAO
						.getJobFunctionList();
				contactsSession.setJobFunctionList(jobFunctionList);

				List<ContactServiceOffering> contactServiceOfferingList = commonDAO
						.getContactServiceOffering();
				contactsSession
						.setContactServiceOfferingList(contactServiceOfferingList);

				List<CampaignStatus> campaignStatusList = commonDAO
						.getCampaignStatus();
				contactsSession.setCampaignStatusList(campaignStatusList);

				List<CampaignType> campaignTypeList = commonDAO
						.getCampaignType();
				contactsSession.setCampaignTypeList(campaignTypeList);

				List<CampaignBusinessUnit> campaignBusinessUnitList = commonDAO
						.getCampaignBusinessUnit();
				contactsSession
						.setCampaignBusinessUnitList(campaignBusinessUnitList);

				List<CampaignRegion> campaignRegionList = commonDAO
						.getCampaignRegion();
				contactsSession.setCampaignRegionList(campaignRegionList);

				List<CampaignCategory> campaignCategoryList = commonDAO.getCampaignCategory();
				contactsSession.setCampaignCategoryList(campaignCategoryList);
				

			} else {
				
				contactsSession = (ContactsSession) session.getAttribute("contactsSession");
				
			}

			contactsSession.setUser(user);
			session.setAttribute("contactsSession", contactsSession);
			

		} catch (Exception e) {
			LOGGER.error("Exception in findBy Login Method"
					+ ExceptionUtils.getStackTrace(e));
		}

		return user;
	}
}
