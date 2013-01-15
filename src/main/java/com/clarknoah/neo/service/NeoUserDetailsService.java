package com.clarknoah.neo.service;


import com.clarknoah.neo.domain.User;
import com.clarknoah.neo.service.NeoUserDetails;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mh
 * @since 08.11.11
 */
public interface NeoUserDetailsService extends UserDetailsService {
    @Override
    NeoUserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException;

    User getUserFromSession();


    @Transactional
    User register(String login, String name, String password);
}

 
