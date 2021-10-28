package com.example.easi641.exception;

public enum ExceptionMessageEnum {
    NOT_FOUND("Not found element"), BAD_REQUEST_EMPTY("Check parameters again");

    // #BAD_REQUEST_AMOUNT("El campo amount de payment no debe ser un valor
    // negativo");
    private final String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
