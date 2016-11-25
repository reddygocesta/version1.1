package com.sensiple.contactsrepository.security;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sensiple.contactsrepository.dao.UserDetailsDAO;
import com.sensiple.contactsrepository.model.Authority;
import com.sensiple.contactsrepository.model.User;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        User user = null;
        try{
        	user = userDetailsDAO.findByLogin(login);
        }catch(Exception e){
        	log.error("Exception in loadUserBy User name"+ExceptionUtils.getStackTrace(e));
        }
        
    
        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } /*else if (!user.getEnabled()) {
            throw new UserNotEnabledException("User " + login + " was not enabled");
        }*/

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (Authority authority : user.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        return new org.springframework.security.core.userdetails.User(login, user.getPassword(),
                grantedAuthorities);
    }
}
