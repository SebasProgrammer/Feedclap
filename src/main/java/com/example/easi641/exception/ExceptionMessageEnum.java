package com.example.easi641.exception;

public enum ExceptionMessageEnum {
    NOT_FOUND("Recycle no encontrado"),
    BAD_REQUEST_EMPTY("Recycle debe enviarse con los datos completos de name, address y city");
    //#BAD_REQUEST_AMOUNT("El campo amount de payment no debe ser un valor negativo");
    private final String message;

    ExceptionMessageEnum(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
