package com.wiprodigital.webcrawler.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper
{
    private final String body;

    public RequestWrapper(HttpServletRequest request) throws IOException
    {
        super(request);
        var stringBuilder = new StringBuilder();
        var inputStream = request.getInputStream();
        if (inputStream != null)
        {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)))
            {
                var charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0)
                {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        }

        body = stringBuilder.toString();
    }

    String getBody()
    {
        return body;
    }
}
