package com.appmarket.config;

import com.appmarket.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static java.util.stream.Collectors.toMap;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest webRequest) {
        final var errors = ex.getConstraintViolations()
                .stream()
                .collect(toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));

        log.error(ex.toString());
        return handleExceptionInternal(
                ex,
                new RestErrorResponse(ex.getClass().getName(), errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest
        );
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Object> handleBusinessException(final BusinessException ex, final WebRequest webRequest) {
        return handleExceptionInternal(
                ex,
                new RestErrorResponse(ex.getCode(), ex.getBody()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest
        );
    }

}
