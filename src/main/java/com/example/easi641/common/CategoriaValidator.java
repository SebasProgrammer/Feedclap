package com.example.easi641.common;

import com.example.easi641.dto.CategoriaRequest;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class CategoriaValidator {
	public static boolean validateCategoria(CategoriaRequest categoriaRequest) {
		if (categoriaRequest.getNombre().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}

		return true;
	}
}
