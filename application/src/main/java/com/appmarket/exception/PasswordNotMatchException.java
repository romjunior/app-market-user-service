package com.appmarket.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
public class PasswordNotMatchException extends BusinessException{

    static String CODE = "PASSWORD_ALREADY_EXISTS";

    public PasswordNotMatchException(Map<String, String> body) {
        super(CODE, body);
    }

    public PasswordNotMatchException(String message) {
        super(message, CODE);
    }
}
