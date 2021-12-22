package com.appmarket.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PasswordNotMatchException extends BusinessException{

    static String code = "PASSWORD_NOT_MATCH";

    public PasswordNotMatchException(Map<String, String> body) {
        super(code, body);
    }

    public PasswordNotMatchException(String message) {
        super(message, code);
    }

    public PasswordNotMatchException() {
        super(code);
    }
}
