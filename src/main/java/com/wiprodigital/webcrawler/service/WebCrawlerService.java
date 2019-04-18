package com.wiprodigital.webcrawler.service;

import java.io.IOException;

import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;

public interface WebCrawlerService
{
    ScoutWebResponse scoutWeb(ScoutWebRequest request) throws IOException;
}
