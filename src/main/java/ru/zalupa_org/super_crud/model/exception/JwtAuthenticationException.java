package ru.zalupa_org.super_crud.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {
    private HttpStatus httpStatus;

    public JwtAuthenticationException(String msg, HttpStatus unauthorized) {
        super(msg);
        this.httpStatus = unauthorized;
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
