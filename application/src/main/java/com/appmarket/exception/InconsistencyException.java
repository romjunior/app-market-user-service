package com.appmarket.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Value
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class InconsistencyException extends BusinessException{

    static String code = "INCONSISTENCY";

    public InconsistencyException(Map<String, String> body) {
        super(code, body);
    }

    public InconsistencyException(String message) {
        super(message, code);
    }

}
