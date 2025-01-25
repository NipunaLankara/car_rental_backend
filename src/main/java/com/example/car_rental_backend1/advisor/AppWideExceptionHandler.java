package com.example.car_rental_backend1.advisor;

import com.example.car_rental_backend1.exception.AlreadyExistsException;
import com.example.car_rental_backend1.exception.NotContentException;
import com.example.car_rental_backend1.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardResponse> handleAlreadyExistsException(AlreadyExistsException exception){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409,"Error",exception.getMessage()),HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardResponse> handleBadCredentialsException (BadCredentialsException exception){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401,"Error",exception.getMessage()),HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardResponse> handleUsernameNotFoundException(UsernameNotFoundException exception){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404 ,"Error",exception.getMessage()),HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardResponse> handleIllegalArgumentException(IllegalArgumentException exception){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400 ,"Invalid argument",exception.getMessage()),HttpStatus.BAD_REQUEST
        );
    }

//    @ExceptionHandler(NotContentException.class)
//    public ResponseEntity<StandardResponse> handleNotContentException(NotContentException exception){
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(204 ,"No Content",exception.getMessage()),HttpStatus.NO_CONTENT
//        );
//    }

    @ExceptionHandler(NotContentException.class)
    public ResponseEntity<StandardResponse> handleNotContentException(NotContentException exception) {
        return new ResponseEntity<>(
                new StandardResponse(20401, "No Content Available", exception.getMessage()),
                HttpStatus.OK
        );
    }


}
