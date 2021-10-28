package com.example.easi641.common;

import com.example.easi641.dto.CategoryDto;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class CategoryValidator {
	public static boolean validateCategory(CategoryDto categoryDto) {
		if (categoryDto.getName().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}

		return true;
	}
}
