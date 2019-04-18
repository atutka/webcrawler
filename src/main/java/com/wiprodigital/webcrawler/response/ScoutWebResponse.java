package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutWebResponse
{
    private String mainDomain;
    private List<PageInfo> pageInfos = new ArrayList<>();
}
