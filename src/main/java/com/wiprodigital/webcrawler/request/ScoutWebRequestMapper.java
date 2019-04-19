package com.wiprodigital.webcrawler.request;

import com.wiprodigital.webcrawler.map.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ScoutWebRequestMapper implements Mapper<ScoutWebRequestDTO, ScoutWebRequest>
{
    @Override
    public ScoutWebRequest map(ScoutWebRequestDTO source)
    {
        var target = new ScoutWebRequest();
        target.setMainDomain(source.getMainDomain());
        target.setMaxPages(source.getMaxPages());
        return target;
    }
}
