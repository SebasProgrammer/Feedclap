package xyz.feedclap.theclaps.Exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import xyz.feedclap.theclaps.Dtos.ErrorDto;

@Getter
public class FeedclapException extends Exception {
	private final String code;
	private final int responseCode;
	private final List<ErrorDto> errorList = new ArrayList<>();

	public FeedclapException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}

	public FeedclapException(String code, int responseCode, String message, List<ErrorDto> errorList) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
		this.errorList.addAll(errorList);
	}
}
