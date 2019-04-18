package com.wiprodigital.webcrawler.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.wiprodigital.webcrawler.map.Mapper;
import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.request.ScoutWebRequestDTO;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;
import com.wiprodigital.webcrawler.response.ScoutWebResponseDTO;
import com.wiprodigital.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController
{
    private final WebCrawlerService webCrawlerService;
    private final Mapper<ScoutWebRequestDTO, ScoutWebRequest> scoutWebRequestMapper;
    private final Mapper<ScoutWebResponse, ScoutWebResponseDTO> scoutWebResponseDTOMapper;

    @Autowired
    public WebCrawlerController(WebCrawlerService webCrawlerService, Mapper<ScoutWebRequestDTO, ScoutWebRequest> scoutWebRequestMapper, Mapper<ScoutWebResponse, ScoutWebResponseDTO> scoutWebResponseDTOMapper)
    {
        this.webCrawlerService = webCrawlerService;
        this.scoutWebRequestMapper = scoutWebRequestMapper;
        this.scoutWebResponseDTOMapper = scoutWebResponseDTOMapper;
    }

    @GetMapping
    public ScoutWebResponseDTO scoutWeb(@Valid ScoutWebRequestDTO requestDTO) throws IOException
    {
        var request = scoutWebRequestMapper.map(requestDTO);
        var response = webCrawlerService.scoutWeb(request);
        return scoutWebResponseDTOMapper.map(response);
    }
}
