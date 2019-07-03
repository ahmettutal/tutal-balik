package com.ahmettutal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidCategoryException extends Exception {
    public InvalidCategoryException(String message) {
        super(message);
    }

    public InvalidCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
