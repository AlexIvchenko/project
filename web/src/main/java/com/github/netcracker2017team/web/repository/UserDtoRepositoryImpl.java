package com.github.netcracker2017team.web.repository;

import com.github.netcracker2017team.model.Credentials;
import com.github.netcracker2017team.model.UserDto;
import com.github.netcracker2017team.web.rest.api.RestApi;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alex Ivchenko
 */
public class UserDtoRepositoryImpl implements UserDtoRepository {
    private RestApi restApi;

    @Autowired
    public void setRestApi(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public UserDto findByUsername(String username) {
        return restApi.user().get(username);
    }

    @Override
    public UserDto save(Credentials credentials) {
        return restApi.user().signUp(credentials);
    }
}
