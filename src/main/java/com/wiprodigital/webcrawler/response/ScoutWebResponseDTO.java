package com.wiprodigital.webcrawler.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Response with information gathered by web crawler")
public class ScoutWebResponseDTO
{
    @ApiModelProperty(notes = "Main domain url that was scout")
    private String mainDomain;

    @ApiModelProperty(notes = "Web page gathered information")
    private List<PageInfoDTO> pageInfos = new ArrayList<>();

    @ApiModelProperty(notes = "Links that were broken and cannot be accessed")
    private List<String> brokenLinks = new ArrayList<>();
}
