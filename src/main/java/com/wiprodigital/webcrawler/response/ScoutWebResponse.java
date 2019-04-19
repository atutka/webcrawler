package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ScoutWebResponse
{
    private String mainDomain;
    private List<PageInfo> pageInfos = new ArrayList<>();
}
