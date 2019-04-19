package com.wiprodigital.webcrawler.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse
{
    private HttpStatus status;
    private String errorMessage;

    private ErrorResponse(HttpStatus status, String errorMessage)
    {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public static ErrorResponse create(HttpStatus status, String errorMessage)
    {
        return new ErrorResponse(status, errorMessage);
    }
}
