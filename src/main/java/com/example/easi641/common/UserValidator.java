package com.example.easi641.common;

import com.example.easi641.dto.DesarrolladorDto;
import com.example.easi641.dto.ReviewerDto;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;

public class UserValidator {
	public static boolean validateDesarrollador(DesarrolladorDto reviewerDto) {
		if (reviewerDto.getNombre().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}
		return true;
	}

	public static boolean validateReviewer(ReviewerDto reviewerDto) {
		if (reviewerDto.getNombre().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}
		return true;
	}
}
