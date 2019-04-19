package com.wiprodigital.webcrawler.exception;

import java.net.SocketTimeoutException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler
{
    @Value("${webcrawler.maxpages}")
    private int maxPages;

    private final MessageSource messageSource;

    @Autowired
    public GlobalControllerExceptionHandler(MessageSource messageSource) {this.messageSource = messageSource;}

    @ExceptionHandler(value = RedirectionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRedirectionException(HttpServletRequest req, RedirectionException ex)
    {
        return ErrorResponse.create(HttpStatus.BAD_REQUEST, getMessage("error.redirection.msg", ex.getUrl()));
    }

    @ExceptionHandler(value = SocketTimeoutException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ErrorResponse handleSocketTimeoutException(HttpServletRequest req, SocketTimeoutException ex)
    {
        return ErrorResponse.create(HttpStatus.GATEWAY_TIMEOUT, getMessage("error.timeout.msg"));
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(HttpServletRequest req, BindException ex)
    {
        String defaultMessage = ex.getFieldError().getDefaultMessage();
        return ErrorResponse.create(HttpStatus.BAD_REQUEST, String.format(defaultMessage, maxPages));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknownException(Exception ex)
    {
        log.error("Internal application error", ex);
        return ErrorResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, getMessage("error.unknown.msg"));
    }

    private String getMessage(String key, Object... objects)
    {
        return messageSource.getMessage(key, objects, Locale.ENGLISH);
    }
}
