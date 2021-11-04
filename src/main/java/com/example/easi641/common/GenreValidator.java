package com.example.easi641.common;

import com.example.easi641.dto.GenreDto;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class GenreValidator {
	public static boolean validateGenre(GenreDto genreDto) {
		if (genreDto.getName().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}
		return true;
	}
}
