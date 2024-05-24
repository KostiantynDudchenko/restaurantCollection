package com.example.restaurantcollection.handler;

import com.example.restaurantcollection.exception.ArgumentNotValidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
@Slf4j
public class ArgumentNotValidExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    @Autowired
    public ArgumentNotValidExceptionHandler(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @ExceptionHandler
    public ResponseEntity<String> handlerArgumentNotValid(ArgumentNotValidException e)
            throws JsonProcessingException {
        Map<String, String> errorMap = new HashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));

        errorMap.forEach((k, v) -> log.error(k + ": " + v));

        return new ResponseEntity<>(objectMapper.writeValueAsString(errorMap), HttpStatus.BAD_REQUEST);
    }
}
