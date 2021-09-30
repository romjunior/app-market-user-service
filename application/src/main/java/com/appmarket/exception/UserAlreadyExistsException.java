package com.appmarket.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
public class UserAlreadyExistsException extends BusinessException{

    static String CODE = "USER_ALREADY_EXISTS";

    public UserAlreadyExistsException(Map<String, String> body) {
        super(CODE, body);
    }

    public UserAlreadyExistsException(String message) {
        super(message, CODE);
    }
}
