package com.wiprodigital.webcrawler.controller;

import java.io.IOException;

import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.request.ScoutWebRequestDTO;
import com.wiprodigital.webcrawler.request.ScoutWebRequestMapper;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;
import com.wiprodigital.webcrawler.response.ScoutWebResponseDTO;
import com.wiprodigital.webcrawler.response.ScoutWebResponseDTOMapper;
import com.wiprodigital.webcrawler.service.WebCrawlerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WebCrawlerControllerTest
{
    @Mock
    private ScoutWebRequestMapper scoutWebRequestMapper;

    @Mock
    private ScoutWebResponseDTOMapper scoutWebResponseDTOMapper;

    @Mock
    private WebCrawlerService webCrawlerService;

    private WebCrawlerController controller;

    @Before
    public void setUp()
    {
        controller = new WebCrawlerController(webCrawlerService, scoutWebRequestMapper, scoutWebResponseDTOMapper);
    }

    @Test
    public void testScoutWeb() throws IOException
    {
        var requestDTO = mock(ScoutWebRequestDTO.class);
        var request = mock(ScoutWebRequest.class);
        var response = mock(ScoutWebResponse.class);
        var responseDTO = mock(ScoutWebResponseDTO.class);

        when(scoutWebRequestMapper.map(any(ScoutWebRequestDTO.class))).thenReturn(request);
        when(webCrawlerService.scoutWeb(any(ScoutWebRequest.class))).thenReturn(response);
        when(scoutWebResponseDTOMapper.map(any(ScoutWebResponse.class))).thenReturn(responseDTO);

        ScoutWebResponseDTO result = controller.scoutWeb(requestDTO);

        verify(scoutWebRequestMapper).map(same(requestDTO));
        verify(webCrawlerService).scoutWeb(same(request));
        verify(scoutWebResponseDTOMapper).map(same(response));

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(responseDTO);
    }
}