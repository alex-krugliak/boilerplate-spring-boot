package com.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 21.02.18.
 */

@ControllerAdvice(value = "com.web.controller")
public class ErrorHandler {

    private final static Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus
    public Map<Object, Object> handleError(Exception e) {
        LOGGER.error("Unexpected error", e);

        return prepareErrorMessage("Internal Server error");
    }

    private Map<Object, Object> prepareErrorMessage(String message) {
        Map<Object, Object> data = new HashMap<>();

        data.put("error", true);
        data.put("message", message);
        return data;
    }

}
