package com.example.easi641.common;

import com.example.easi641.dto.GameDto;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.BadRequestException;

public class GameValidator {
	public static boolean validateGame(GameDto juegoDto) {
		if (juegoDto.getName().isEmpty() || juegoDto.getDescription().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}

		return true;
	}
}
