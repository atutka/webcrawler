package com.wiprodigital.webcrawler.service;

import java.io.IOException;

import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;

public interface WebCrawlerService
{
    /**
     * Scout web for informationa bout links
     * @param request
     *      information about web to scout
     * @return
     *      {@link ScoutWebResponse}
     * @throws IOException
     *      thrown when there is a problem with getting web information
     */
    ScoutWebResponse scoutWeb(ScoutWebRequest request) throws IOException;
}
