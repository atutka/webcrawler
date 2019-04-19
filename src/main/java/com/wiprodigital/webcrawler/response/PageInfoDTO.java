package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Web page details information")
public class PageInfoDTO
{
    @ApiModelProperty(notes = "Url of web page")
    private String url;

    @ApiModelProperty(notes = "Links to be accessed from url")
    private List<String> links = new ArrayList<>();

    @ApiModelProperty(notes = "Links to external web pages to be accessed from url")
    private List<String> externalLinks = new ArrayList<>();

    @ApiModelProperty(notes = "Links of static content on web page")
    private List<String> staticContentLinks = new ArrayList<>();

    @ApiModelProperty(notes = "Links of subdomains accessed from url")
    private List<String> subdomains = new ArrayList<>();
}
