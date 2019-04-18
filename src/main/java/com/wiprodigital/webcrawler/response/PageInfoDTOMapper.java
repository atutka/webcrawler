package com.wiprodigital.webcrawler.response;

import com.wiprodigital.webcrawler.map.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PageInfoDTOMapper implements Mapper<PageInfo, PageInfoDTO>
{

    @Override
    public PageInfoDTO map(PageInfo source)
    {
        var target = new PageInfoDTO();
        target.setUrl(source.getUrl());
        target.setLinks(source.getLinks());
        target.setExternalLinks(source.getExternalLinks());
        target.setStaticContentLinks(source.getStaticContentLinks());
        target.setSubdomains(source.getSubdomains());
        return target;
    }
}
