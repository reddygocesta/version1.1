package com.sensiple.contactsrepository.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sensiple.contactsrepository.dao.UserDetailsDAO;
import com.sensiple.contactsrepository.model.User;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	 private final Logger LOGGER = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);
    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
    	User user = null;
    	try{
    		 user = userDetailsDAO.findByLogin(authentication.getName());
    	}catch(Exception e){
    		LOGGER.error("Exception in findByLogin method"+ExceptionUtils.getStackTrace(e));
    	}
       
        SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
    }
}
