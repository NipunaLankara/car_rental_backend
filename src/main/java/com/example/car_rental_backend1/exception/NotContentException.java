package com.example.car_rental_backend1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NotContentException extends RuntimeException {

    public NotContentException(String message){
        super(message);
    }
}
