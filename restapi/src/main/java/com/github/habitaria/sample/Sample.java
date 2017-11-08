package com.github.habitaria.sample;

import com.github.netcracker2017team.model.UserDto;
import com.github.habitaria.restapi.RestApi;
import com.github.habitaria.restapi.api.BasicAuthToken;
import com.github.habitaria.restapi.api.RootApi;

/**
 * @author Alex Ivchenko
 */
public class Sample {
    public static void main(final String... args) {
        RootApi api = RestApi.builder()
                .schema(RestApi.Schema.HTTP)
                .domain("localhost")
                .port("8080")
                .path("api")
                .tokens(() -> new BasicAuthToken("Alexander", "password"));
        UserDto user = api.user().get("Alexander");
        System.out.println(user);
    }
}
