package com.example.easi641.common;

import com.example.easi641.dto.GeneroDto;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class GeneroValidator {
    public static boolean validateGenero(GeneroDto generoDto){
        if (generoDto.getNombre().isEmpty()) {
            throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
        }

        return true;
    }
}
