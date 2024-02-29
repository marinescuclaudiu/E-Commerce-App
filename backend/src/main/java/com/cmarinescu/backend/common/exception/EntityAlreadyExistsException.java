package com.cmarinescu.backend.common.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException{
    public EntityAlreadyExistsException(String message) {super (message, HttpStatus.CONFLICT);}
}
