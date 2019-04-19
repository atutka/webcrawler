package com.wiprodigital.webcrawler.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.wiprodigital.webcrawler.validation.MaxPages;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "Request for scouting web")
public class ScoutWebRequestDTO
{
    @ApiModelProperty(notes = "Domain url to scout")
    @Pattern(regexp = "^https?:\\/\\/(w{3}\\.)?[a-z0-9]*[.][a-z]*\\/?$", message = "{validation.domain.pattern.error}")
    @NotBlank(message = "{validation.missing.maindomain}")
    private String mainDomain;

    @ApiModelProperty(notes = "Optional parameter. Maximum number of pages to scout main domain")
    @MaxPages
    private Integer maxPages;
}
