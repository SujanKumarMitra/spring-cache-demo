package com.github.mitrakumarsujan.springcachedemo.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        List<RequestBodyFieldError> fieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(this::createFieldError)
                .collect(toList());

        List<RequestGlobalError> globalErrors = bindingResult
                .getGlobalErrors()
                .stream()
                .map(this::createGlobalError)
                .collect(toList());


        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Request contains errors");
        response.put("errorCount", bindingResult.getErrorCount());
        response.put("fieldErrors", fieldErrors);
        response.put("globalErrors", globalErrors);

        return ResponseEntity.badRequest()
                            .body(response);
    }

    RequestBodyFieldError createFieldError(FieldError error) {
        String fieldName = error.getField();
        String value = String.valueOf(error.getRejectedValue());
        String message = error.getDefaultMessage();

        return new RequestBodyFieldError(fieldName, value, message);
    }

    RequestGlobalError createGlobalError(ObjectError error) {
        String objectName = error.getObjectName();
        String message = error.getDefaultMessage();

        return new RequestGlobalError(objectName, message);
    }

}
