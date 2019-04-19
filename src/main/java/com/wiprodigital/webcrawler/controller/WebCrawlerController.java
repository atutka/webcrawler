package com.wiprodigital.webcrawler.controller;

import java.io.IOException;
import javax.validation.Valid;

import com.wiprodigital.webcrawler.map.Mapper;
import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.request.ScoutWebRequestDTO;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;
import com.wiprodigital.webcrawler.response.ScoutWebResponseDTO;
import com.wiprodigital.webcrawler.service.WebCrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Web crawler", description = "Operations for scouting web pages by web crawler")
@RequestMapping("/api/v1")
@RestController
public class WebCrawlerController
{
    private final WebCrawlerService webCrawlerService;
    private final Mapper<ScoutWebRequestDTO, ScoutWebRequest> scoutWebRequestMapper;
    private final Mapper<ScoutWebResponse, ScoutWebResponseDTO> scoutWebResponseDTOMapper;

    @ApiOperation(value = "Scout web page by web crawler", response = ScoutWebResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved information about webpage"),
            @ApiResponse(code = 400, message = "Request was incorrect"),
            @ApiResponse(code = 500, message = "Error in application"),
            @ApiResponse(code = 504, message = "Problem with connecting to requested domain")
    })
    @GetMapping("/scoutWeb")
    public ScoutWebResponseDTO scoutWeb(@Valid ScoutWebRequestDTO requestDTO) throws IOException
    {
        var request = scoutWebRequestMapper.map(requestDTO);
        var response = webCrawlerService.scoutWeb(request);
        return scoutWebResponseDTOMapper.map(response);
    }

    @Autowired
    public WebCrawlerController(WebCrawlerService webCrawlerService, Mapper<ScoutWebRequestDTO, ScoutWebRequest> scoutWebRequestMapper, Mapper<ScoutWebResponse, ScoutWebResponseDTO> scoutWebResponseDTOMapper)
    {
        this.webCrawlerService = webCrawlerService;
        this.scoutWebRequestMapper = scoutWebRequestMapper;
        this.scoutWebResponseDTOMapper = scoutWebResponseDTOMapper;
    }
}
