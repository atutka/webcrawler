package com.wiprodigital.webcrawler.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.wiprodigital.webcrawler.validation.MaxPages;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutWebRequestDTO
{
    @Pattern(regexp = "^https?:\\/\\/(w{3}\\.)?[a-z0-9]*[.][a-z]*\\/?$", message = "{validation.domain.pattern.error}")
    @NotBlank(message = "{validation.missing.maindomain}")
    private String mainDomain;

    @MaxPages
    private Integer maxPages;
}
