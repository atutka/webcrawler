package com.wiprodigital.webcrawler.logging;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class RequestLoggingFilter implements Filter
{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException
    {

        var requestWrapper = new RequestWrapper((HttpServletRequest) request);
        Map<String, String> requestMap = getRequestParameters(requestWrapper);

        var requestMessageLog = "REST Request - "
                + "[HTTP METHOD:" + requestWrapper.getMethod()
                + "] [URI:" + requestWrapper.getRequestURI()
                + "] [REQUEST PARAMETERS:" + requestMap
                + "] [REQUEST BODY:" + requestWrapper.getBody()
                + "]";
        log.info(requestMessageLog);
        filterChain.doFilter(requestWrapper, response);
    }

    private Map<String, String> getRequestParameters(HttpServletRequest request)
    {
        Map<String, String> requestsMap = new HashMap<>();
        Enumeration<String> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements())
        {
            String requestParamName = requestParamNames.nextElement();
            String requestParamValue = request.getParameter(requestParamName);
            requestsMap.put(requestParamName, requestParamValue);
        }
        return requestsMap;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // not used
    }

    @Override
    public void destroy()
    {
        // not used
    }
}
