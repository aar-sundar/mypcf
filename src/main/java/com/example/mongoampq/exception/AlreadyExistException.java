package com.example.mongoampq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String msg){
        super(msg);
    }
}
