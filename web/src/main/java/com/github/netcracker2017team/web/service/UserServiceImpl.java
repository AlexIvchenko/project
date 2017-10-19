package com.github.netcracker2017team.web.service;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.web.repository.UserDtoRepository;
import com.github.netcracker2017team.web.security.BasicAuthToken;
import com.github.netcracker2017team.web.security.BasicAuthTokenRepository;
import com.github.netcracker2017team.web.ui.beans.SignUpBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class UserServiceImpl implements UserService {
    private UserDtoRepository userDtoRepository;
    private BasicAuthTokenRepository basicAuthTokenRepository;
    private AuthenticationProvider authenticationProvider;

    @Autowired
    public void setRepository(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }

    @Autowired
    public void setBasicAuthTokenRepository(BasicAuthTokenRepository basicAuthTokenRepository) {
        this.basicAuthTokenRepository = basicAuthTokenRepository;
    }

    @Autowired
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void signUp(SignUpBean bean) {
        Credentials credentials = Credentials.builder()
                .username(bean.getUsername())
                .email(bean.getEmail())
                .firstName(bean.getFirstName())
                .lastName(bean.getLastName())
                .password(bean.getPassword())
                .build();
        userDtoRepository.save(credentials);
    }

    @Override
    public void signIn(String username, String password) {
        log.info("signing in: " + username + " " + password);
        basicAuthTokenRepository.save(new BasicAuthToken(username, password));
        AbstractAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
