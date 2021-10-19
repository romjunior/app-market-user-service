package com.appmarket.exception;

import java.util.Map;

public class InconsistencyException extends BusinessException{

    static String CODE = "INCONSISTENCY";

    public InconsistencyException(Map<String, String> body) {
        super(CODE, body);
    }

    public InconsistencyException(String message) {
        super(message, CODE);
    }

}
