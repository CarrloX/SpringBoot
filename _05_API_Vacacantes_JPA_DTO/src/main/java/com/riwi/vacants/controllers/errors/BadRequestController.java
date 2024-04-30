package com.riwi.vacants.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.riwi.vacants.utils.dto.errors.BaseErrorResponse;
import com.riwi.vacants.utils.dto.errors.ErrorResponse;
import com.riwi.vacants.utils.exceptions.IdNotFoundException;

//restcontrolleradvice = controlador de errores

@RestControllerAdvice
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestController {

    //para especificar cuando se va utilizar o cuando se va disparar este metodo
    //utilizamos la anotacion ExceptionHandler

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception){

        return ErrorResponse.builder()
        .message(exception.getMessage())
        .status(HttpStatus.BAD_REQUEST.name())
        .code(HttpStatus.BAD_REQUEST.value())
        .build();

        // ErrorResponse error = new ErrorResponse()
        // error.setMessage(exception.getMessage());
        // error.setStatus(HttpStatus.BAD_REQUEST.name());
        // error.code(HttpStatus.BAD_REQUEST.value());
    }
}
