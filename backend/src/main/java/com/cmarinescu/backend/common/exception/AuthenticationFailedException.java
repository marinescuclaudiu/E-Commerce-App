package com.cmarinescu.backend.common.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends BaseException {
    public AuthenticationFailedException(String message) {super (message, HttpStatus.UNAUTHORIZED);}
}
