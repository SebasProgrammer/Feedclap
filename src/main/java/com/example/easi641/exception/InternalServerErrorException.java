package com.example.easi641.exception;

import java.util.Arrays;

import com.example.easi641.dto.ErrorDto;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends FeedclapException {
	public InternalServerErrorException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	public InternalServerErrorException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
