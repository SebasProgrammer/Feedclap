package com.example.easi641.common;

import com.example.easi641.dto.JuegoRequest;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.BadRequestException;

public class JuegoValidator {
    public static boolean validateGame(JuegoRequest juegoRequest) {
        if (juegoRequest.getNombre().isEmpty() || juegoRequest.getDescripcion().isEmpty()) {
            throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
        }

        return true;
    }
}
