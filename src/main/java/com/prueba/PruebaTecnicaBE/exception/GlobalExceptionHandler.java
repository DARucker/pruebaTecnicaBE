package com.prueba.PruebaTecnicaBE.exception;

import com.prueba.PruebaTecnicaBE.entity.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SuperheroeNotFoundException.class)
    public ResponseEntity<ErrorDto> handleSuperHeroeNotFoundException(SuperheroeNotFoundException ex){

        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListaVaciaException.class)
    public ResponseEntity<ErrorDto> handleListaVaciaException(ListaVaciaException ex){

        ErrorDto errorDto = ErrorDto.builder()
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
