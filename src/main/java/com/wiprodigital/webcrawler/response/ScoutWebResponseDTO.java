package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutWebResponseDTO
{
    private String mainDomain;
    private List<PageInfoDTO> pageInfos;
    private List<String> brokenLinks = new ArrayList<>();
}
