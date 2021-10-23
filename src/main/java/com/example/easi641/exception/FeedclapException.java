package com.example.easi641.exception;


import com.example.easi641.dto.ErrorDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FeedclapException extends Throwable {
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
