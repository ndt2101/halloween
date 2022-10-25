package com.vti.halloween.service.impl;

import com.vti.halloween.model.UserEntity;
import com.vti.halloween.repository.UserRepository;
import com.vti.halloween.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws AuthenticationException {
        UserEntity userLoginData = userRepository.findByAccount(loginName)
                .orElseThrow(() -> new InternalAuthenticationServiceException("Username is incorrect!"));
        return UserPrincipal.create(userLoginData);
    }
}