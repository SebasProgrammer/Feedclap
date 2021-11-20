package com.example.easi641.common;

import com.example.easi641.dto.DeveloperDto;
import com.example.easi641.dto.ReviewerDto;
import com.example.easi641.entities.User;
import com.example.easi641.exception.BadRequestException;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.IncorrectResourceRequestException;

public class UserValidator {
	public static boolean validateDeveloper(DeveloperDto developerDto) {
		if (developerDto.getName().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}
		return true;
	}

	public static boolean validateReviewer(ReviewerDto reviewerDto) {
		if (reviewerDto.getName().isEmpty()) {
			throw new BadRequestException(ExceptionMessageEnum.BAD_REQUEST_EMPTY.getMessage());
		}
		return true;
	}

	public static void validate(User user){
		if(user.getUsername()==null || user.getUsername().trim().isEmpty()){
			throw new IncorrectResourceRequestException("El nombre de usuario es requerido");
		}

		if(user.getUsername().length()>30){
			throw new IncorrectResourceRequestException("El nombre de usuario es muy largo (max 30)");
		}

		if(user.getPassword()==null || user.getPassword().isEmpty()){
			throw new IncorrectResourceRequestException("El password es requerido");
		}
		if(user.getPassword().length()>30){
			throw new IncorrectResourceRequestException("El password de usuario es muy largo (max 30)");
		}
	}
}
