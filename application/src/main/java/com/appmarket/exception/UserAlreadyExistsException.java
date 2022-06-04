package com.appmarket.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserAlreadyExistsException extends BusinessException {

    static String code = "USER_ALREADY_EXISTS";

    public UserAlreadyExistsException(Map<String, String> body) {
        super(code, body);
    }

    public UserAlreadyExistsException(String message) {
        super(message, code);
    }

    public UserAlreadyExistsException() {
        super(code);
    }
}
