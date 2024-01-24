package com.prueba.PruebaTecnicaBE.exception;

public class SuperheroeNotFoundException extends RuntimeException {
    public SuperheroeNotFoundException(String message){
        super(message);
    }
}
