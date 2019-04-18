package com.wiprodigital.webcrawler.response;

import java.util.List;
import java.util.stream.Collectors;

import com.wiprodigital.webcrawler.map.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoutWebResponseDTOMapper implements Mapper<ScoutWebResponse, ScoutWebResponseDTO>
{
    private final Mapper<PageInfo, PageInfoDTO> pageInfoDTOMapper;

    @Autowired
    public ScoutWebResponseDTOMapper(Mapper<PageInfo, PageInfoDTO> pageInfoDTOMapper)
    {
        this.pageInfoDTOMapper = pageInfoDTOMapper;
    }

    @Override
    public ScoutWebResponseDTO map(ScoutWebResponse source)
    {
        var target = new ScoutWebResponseDTO();
        target.setMainDomain(source.getMainDomain());
        List<PageInfo> pageInfos = source.getPageInfos();
        List<PageInfo> pageInfosWithoutBrokenLinks = pageInfos.stream()
                .filter(pi -> !pi.isBroken())
                .collect(Collectors.toList());
        List<PageInfoDTO> pageInfoDTOs = pageInfoDTOMapper.map(pageInfosWithoutBrokenLinks);
        target.setPageInfos(pageInfoDTOs);
        List<String> brokenLinks = pageInfos.stream()
                .filter(PageInfo::isBroken)
                .map(PageInfo::getUrl)
                .collect(Collectors.toList());
        target.setBrokenLinks(brokenLinks);
        return target;
    }
}
