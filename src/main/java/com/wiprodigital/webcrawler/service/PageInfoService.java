package com.wiprodigital.webcrawler.service;

import java.io.IOException;

import com.wiprodigital.webcrawler.response.PageInfo;

public interface PageInfoService
{
    /**
     * Gets information about page
     * @param link
     *      link to the page
     * @param mainDomainUrl
     *      main domain url
     * @param checkRedirection
     *      check redirection for link
     * @return
     *      {@link PageInfo}
     * @throws IOException
     *      when there is a problem with connection
     */
    PageInfo getPageInfo(String link, String mainDomainUrl, boolean checkRedirection) throws IOException;
}
