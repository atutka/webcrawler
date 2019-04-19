package com.wiprodigital.webcrawler.exception;

import java.net.SocketTimeoutException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GlobalControllerExceptionHandlerTest
{
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private GlobalControllerExceptionHandler handler;

    @Test
    public void testHandleRedirectionException() throws Exception
    {
        String url = "url";
        var message = "message";
        var exception = mock(RedirectionException.class);
        var httpServletRequest = mock(HttpServletRequest.class);
        var arrayCaptor = ArgumentCaptor.forClass(Object[].class);
        var localeCaptor = ArgumentCaptor.forClass(Locale.class);

        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn(message);
        when(exception.getUrl()).thenReturn(url);

        ErrorResponse result = handler.handleRedirectionException(httpServletRequest, exception);

        verify(messageSource).getMessage(eq("error.redirection.msg"), arrayCaptor.capture(), localeCaptor.capture());
        verify(exception).getUrl();

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getErrorMessage()).isEqualTo(message);

        Object[] objects = arrayCaptor.getValue();
        assertThat(objects.length).isEqualTo(1);
        assertThat(objects[0]).isEqualTo(url);

        Locale locale = localeCaptor.getValue();
        assertThat(locale).isEqualTo(Locale.ENGLISH);
    }

    @Test
    public void testHandleSocketTimeoutException() throws Exception
    {
        var message = "message";
        var exception = mock(SocketTimeoutException.class);
        var httpServletRequest = mock(HttpServletRequest.class);
        var arrayCaptor = ArgumentCaptor.forClass(Object[].class);
        var localeCaptor = ArgumentCaptor.forClass(Locale.class);

        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn(message);

        ErrorResponse result = handler.handleSocketTimeoutException(httpServletRequest, exception);

        verify(messageSource).getMessage(eq("error.timeout.msg"), arrayCaptor.capture(), localeCaptor.capture());

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(HttpStatus.GATEWAY_TIMEOUT);
        assertThat(result.getErrorMessage()).isEqualTo(message);

        Object[] objects = arrayCaptor.getValue();
        assertThat(objects.length).isEqualTo(0);
        Locale locale = localeCaptor.getValue();
        assertThat(locale).isEqualTo(Locale.ENGLISH);
    }

    @Test
    public void testHandleValidationException() throws Exception
    {
        var message = "message";
        var exception = mock(BindException.class);
        var httpServletRequest = mock(HttpServletRequest.class);
        var filedError = mock(FieldError.class);

        when(exception.getFieldError()).thenReturn(filedError);
        when(filedError.getDefaultMessage()).thenReturn(message);

        ErrorResponse result = handler.handleValidationException(httpServletRequest, exception);

        verify(exception).getFieldError();
        verify(filedError).getDefaultMessage();

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getErrorMessage()).isEqualTo(message);
    }

    @Test
    public void testHandleUnknownException() throws Exception
    {
        var message = "message";
        var exception = mock(IllegalArgumentException.class);
        var arrayCaptor = ArgumentCaptor.forClass(Object[].class);
        var localeCaptor = ArgumentCaptor.forClass(Locale.class);

        when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn(message);

        ErrorResponse result = handler.handleUnknownException(exception);

        verify(messageSource).getMessage(eq("error.unknown.msg"), arrayCaptor.capture(), localeCaptor.capture());

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getErrorMessage()).isEqualTo(message);

        Object[] objects = arrayCaptor.getValue();
        assertThat(objects.length).isEqualTo(0);
        Locale locale = localeCaptor.getValue();
        assertThat(locale).isEqualTo(Locale.ENGLISH);
    }
}