package com.app.patron.exception;

public class CiudadanoYaExisteException extends RuntimeException {

    public CiudadanoYaExisteException(String mensaje) {
        super(mensaje);
    }
}

