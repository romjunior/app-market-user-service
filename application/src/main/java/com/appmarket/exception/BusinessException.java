package com.appmarket.exception;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BusinessException extends Exception{
    String code;
    Map<String, String> body;

    public BusinessException(final String message, final String code) {
        this.code = code;
        this.body = Collections.singletonMap("message", message);
    }
}
