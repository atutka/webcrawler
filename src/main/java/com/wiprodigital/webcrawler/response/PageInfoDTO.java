package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfoDTO
{
    private String url;
    private List<String> links = new ArrayList<>();
    private List<String> externalLinks = new ArrayList<>();
    private List<String> staticContentLinks = new ArrayList<>();
    private List<String> subdomains = new ArrayList<>();
}
