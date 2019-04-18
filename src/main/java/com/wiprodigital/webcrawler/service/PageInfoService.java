package com.wiprodigital.webcrawler.service;

import java.io.IOException;

import com.wiprodigital.webcrawler.response.PageInfo;

public interface PageInfoService
{
    PageInfo getPageInfo(String link, String mainDomainUrl, boolean checkRedirection) throws IOException;
}
