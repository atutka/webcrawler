package com.wiprodigital.webcrawler.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoutWebRequestDTO
{
    @Pattern(regexp = "^http[s*]://.*$", message = "{validation.date.pattern.error}")
    @NotBlank
    private String mainDomain;
}
