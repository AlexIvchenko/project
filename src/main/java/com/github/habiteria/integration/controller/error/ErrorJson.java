package com.github.habiteria.integration.controller.error;

import lombok.Getter;

import java.util.Map;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ErrorJson {
    private Integer status;
    private String message;
    private String timeStamp;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.message = (String) errorAttributes.get("message");
        this.timeStamp = errorAttributes.get("timestamp").toString();
    }
}
