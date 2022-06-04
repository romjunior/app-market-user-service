package com.appmarket.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleNotExistsException extends BusinessException {

    static String code = "ROLE_NOT_EXISTS";

    public RoleNotExistsException(Map<String, String> body) {
        super(code, body);
    }

    public RoleNotExistsException(String message) {
        super(message, code);
    }

    public RoleNotExistsException() {
        super(code);
    }
}
