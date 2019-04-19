package com.wiprodigital.webcrawler.service;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import com.wiprodigital.webcrawler.exception.RedirectionException;
import com.wiprodigital.webcrawler.response.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class PageInfoServiceImpl implements PageInfoService
{
    private final static String SRC_ATTRIBUTE_KEY = "src";
    private final static String HREF_ATTRIBUTE_KEY = "href";
    private final static String IMAGE_QUERY = "img[src]";
    private final static String LINK_QUERY = "a[href]";
    private final static String HTTP_PREFIX = "http";
    private final static String HASH_SYMBOL = "#";
    private final static String SLASH_SYMBOL = "/";

    @Value("${contentLink.regex}")
    private String contentLinkRegex;

    @Override
    public PageInfo getPageInfo(String link, String mainDomainUrl, boolean checkRedirection) throws IOException
    {
        log.debug("Getting page info for url: {}", link);
        var pageInfo = new PageInfo();
        pageInfo.setUrl(link);
        var connection = Jsoup.connect(link);
        connection.followRedirects(false);
        Document htmlDocument;
        try
        {
            htmlDocument = connection.get();
        } catch (HttpStatusException ex)
        {
            log.error(String.format("Url is broken: %s", link), ex);
            pageInfo.setBroken(true);
            return pageInfo;
        }
        var statusCode = connection.response().statusCode();
        if (checkRedirection && statusCode >= 300 && statusCode <= 400)
        {
            throw new RedirectionException(link);
        }
        Set<String> linksOnPage =  getLinks(htmlDocument);
        processLinks(pageInfo, linksOnPage, mainDomainUrl);
        return pageInfo;
    }

    private Set<String> getLinks(Document htmlDocument)
    {
        Elements imagesOnPageElements = htmlDocument.select(IMAGE_QUERY);
        Set<String> imagesOnPage = imagesOnPageElements.stream()
                .map(e -> e.absUrl(SRC_ATTRIBUTE_KEY))
                .collect(Collectors.toSet());
        Elements linksOnPageElements = htmlDocument.select(LINK_QUERY);
        Set<String> linksOnPage = linksOnPageElements.stream()
                .map(e -> e.absUrl(HREF_ATTRIBUTE_KEY))
                .collect(Collectors.toSet());
        linksOnPage.addAll(imagesOnPage);
        return linksOnPage;
    }

    private void processLinks(PageInfo pageInfo, Set<String> links, String mainDomainUrl)
    {
        var mainDomainPattern = StringUtils.replace(mainDomainUrl, "://", ":\\/\\/.+[.]") + ".*";
        for (var link : links)
        {
            var contentLink = link.matches(contentLinkRegex);
            if (!link.startsWith(mainDomainUrl) && !contentLink)
            {
                if (link.matches(mainDomainPattern))
                {
                    pageInfo.getSubdomains().add(link);
                    continue;
                }
                if (link.startsWith(HTTP_PREFIX))
                {
                    pageInfo.getExternalLinks().add(link);
                }
                continue;
            }
            if (link.contains(HASH_SYMBOL))
            {
                continue;
            }
            if (contentLink)
            {
                pageInfo.getStaticContentLinks().add(link);
                continue;
            }
            if (!link.endsWith(SLASH_SYMBOL))
            {
                link = link + SLASH_SYMBOL;
            }
            pageInfo.getLinks().add(link);
        }
    }

}
