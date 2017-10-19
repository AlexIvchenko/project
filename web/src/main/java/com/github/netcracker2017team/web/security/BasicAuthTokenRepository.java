package com.github.netcracker2017team.web.security;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface BasicAuthTokenRepository extends CrudRepository<BasicAuthToken, String> {

}
