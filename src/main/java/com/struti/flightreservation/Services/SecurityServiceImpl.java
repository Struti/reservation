package com.struti.flightreservation.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements ISecurityService {

    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    public SecurityServiceImpl(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                               AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean login(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
                userDetails.getAuthorities());

        authenticationManager.authenticate(token);

        boolean result = token.isAuthenticated();

        if (result) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return result;
    }
}
