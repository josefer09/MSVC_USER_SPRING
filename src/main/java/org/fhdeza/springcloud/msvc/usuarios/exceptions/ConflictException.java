package org.fhdeza.springcloud.msvc.usuarios.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {
    public ConflictException(String message, HttpStatus status) {
        super(message, HttpStatus.CONFLICT);
    }
}
