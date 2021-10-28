package com.example.easi641.common;

import com.example.easi641.dto.CategoriaDto;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class CategoriaValidator {
	public static boolean validateCategoria(CategoriaDto categoriaDto) {
		if (categoriaDto.getNombre().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}

		return true;
	}
}
