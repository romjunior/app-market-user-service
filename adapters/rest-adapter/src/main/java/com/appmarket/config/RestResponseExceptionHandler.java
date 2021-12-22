package com.appmarket.config;

import com.appmarket.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest webRequest) {
        final var errors = ex.getConstraintViolations()
                .stream()
                .collect(groupingBy(ConstraintViolation::getPropertyPath, mapping(ConstraintViolation::getMessage, joining(", "))));

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
                new RestErrorResponse(ex.getCode(), getMessageIfBodyIsEmpty(ex)),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                webRequest
        );
    }

    Map<?, ?> getMessageIfBodyIsEmpty(BusinessException ex) {
        if(ex.getBody().isEmpty())
            return Map.of("message", messageSource.getMessage(ex.getCode().replace("_", ".").toLowerCase(), null, Locale.getDefault()));
        return ex.getBody();
    }

}
