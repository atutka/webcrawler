package com.wiprodigital.webcrawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.response.PageInfo;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebCrawlerServiceImpl implements WebCrawlerService
{
    @Value("${webcrawler.maxpages}")
    private Integer defaultMaxPages;

    private final PageInfoService pageInfoService;

    @Autowired
    public WebCrawlerServiceImpl(PageInfoService pageInfoService)
    {
        this.pageInfoService = pageInfoService;
    }

    @Override
    public ScoutWebResponse scoutWeb(ScoutWebRequest request) throws IOException
    {
        String mainDomain = request.getMainDomain();
        log.debug("Scouting web for url {}", mainDomain);
        Integer maxPages = request.getMaxPages() == null ? this.defaultMaxPages : request.getMaxPages();
        Set<String> links = new LinkedHashSet<>();
        links.add(mainDomain);
        List<PageInfo> pageInfos = new ArrayList<>();
        var sizeOfLinksToVisit = links.size();
        List<String> linkList = List.copyOf(links);
        for(int i = 0; i < links.size() && i < maxPages; i++)
        {
            if(sizeOfLinksToVisit != links.size())
            {
                linkList = List.copyOf(links);
            }
            var link = linkList.get(i);
            var pageInfo = pageInfoService.getPageInfo(link, mainDomain, link.equals(mainDomain));
            links.addAll(pageInfo.getLinks());
            pageInfos.add(pageInfo);
        }
        return new ScoutWebResponse(mainDomain, pageInfos);
    }

}
