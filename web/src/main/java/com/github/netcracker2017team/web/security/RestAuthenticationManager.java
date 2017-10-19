package com.github.netcracker2017team.web.security;

import com.github.netcracker2017team.restapi.api.RestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.rcp.RemoteAuthenticationException;
import org.springframework.security.authentication.rcp.RemoteAuthenticationManager;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class RestAuthenticationManager implements RemoteAuthenticationManager {
    private RestApi restApi;

    @Autowired
    public void setRestApi(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Collection<? extends GrantedAuthority> attemptAuthentication(String username, String password) throws RemoteAuthenticationException {
        try {
            if (restApi.user().auth(username, password)) {
                return Collections.emptyList();
            } else {
                throw new RemoteAuthenticationException("Bad credentials");
            }
        } catch (Exception e) {
            log.info("remote error", e);
            throw new RemoteAuthenticationException("Remote error");
        }
    }
}
