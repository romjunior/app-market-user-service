package com.appmarket.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserNotFoundException extends BusinessException{

    static String CODE = "USER_NOT_FOUND";

    public UserNotFoundException(Map<String, String> body) {
        super(CODE, body);
    }

    public UserNotFoundException(String message) {
        super(message, CODE);
    }
}
