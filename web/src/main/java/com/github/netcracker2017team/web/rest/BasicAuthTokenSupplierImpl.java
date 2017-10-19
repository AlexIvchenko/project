package com.github.netcracker2017team.web.rest;

import com.github.netcracker2017team.web.rest.api.BasicAuthTokenSupplier;
import com.github.netcracker2017team.web.security.BasicAuthToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Alex Ivchenko
 */
public class BasicAuthTokenSupplierImpl implements BasicAuthTokenSupplier {
    @Override
    public BasicAuthToken get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getPrincipal().toString();
        String password = auth.getCredentials().toString();
        return new BasicAuthToken(username, password);
    }
}
