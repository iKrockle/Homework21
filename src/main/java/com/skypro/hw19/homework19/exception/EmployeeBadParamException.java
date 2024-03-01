package com.skypro.hw19.homework19.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeBadParamException extends RuntimeException{
    public EmployeeBadParamException(String message) {
        super(message);
    }
}
