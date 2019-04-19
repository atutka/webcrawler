package com.wiprodigital.webcrawler.exception;

import lombok.Getter;

@Getter
public class RedirectionException extends RuntimeException
{
    private String url;

    public RedirectionException(String url)
    {
        super();
        this.url = url;
    }
}