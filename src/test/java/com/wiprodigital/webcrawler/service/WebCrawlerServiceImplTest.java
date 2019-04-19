package com.wiprodigital.webcrawler.service;

import java.io.IOException;
import java.util.List;

import com.wiprodigital.webcrawler.request.ScoutWebRequest;
import com.wiprodigital.webcrawler.response.PageInfo;
import com.wiprodigital.webcrawler.response.ScoutWebResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WebCrawlerServiceImplTest
{
    private final static Integer MAX_PAGES = 100;

    @Mock
    private PageInfoService pageInfoService;

    @InjectMocks
    private WebCrawlerServiceImpl service;

    @Before
    public void setUp()
    {
        ReflectionTestUtils.setField(service, "defaultMaxPages", MAX_PAGES);
    }

    @Test
    public void testScoutWeb() throws IOException
    {
        var mainDomain = "https://wiprodigital.com";
        var request = mock(ScoutWebRequest.class);
        var pageInfo1 = mock(PageInfo.class);
        var pageInfo2 = mock(PageInfo.class);
        var link1 = "link1";

        when(request.getMainDomain()).thenReturn(mainDomain);
        when(request.getMaxPages()).thenReturn(null);
        when(pageInfoService.getPageInfo(anyString(), anyString(), anyBoolean())).thenReturn(pageInfo1, pageInfo2);
        when(pageInfo1.getLinks()).thenReturn(List.of(link1));
        when(pageInfo2.getLinks()).thenReturn(List.of());

        ScoutWebResponse result = service.scoutWeb(request);

        verify(request).getMainDomain();
        verify(request).getMaxPages();
        verify(pageInfoService).getPageInfo(eq(mainDomain), eq(mainDomain), eq(true));
        verify(pageInfoService).getPageInfo(eq(link1), eq(mainDomain), eq(false));
        verify(pageInfo1).getLinks();
        verify(pageInfo2).getLinks();

        assertThat(result).isNotNull();
        assertThat(result.getMainDomain()).isEqualTo(mainDomain);
        assertThat(result.getPageInfos()).containsExactly(pageInfo1, pageInfo2);
    }

    @Test
    public void testScoutWebWithMaxPagesNotDefault() throws IOException
    {
        var mainDomain = "https://wiprodigital.com";
        var request = mock(ScoutWebRequest.class);
        var pageInfo1 = mock(PageInfo.class);
        var link1 = "link1";

        when(request.getMainDomain()).thenReturn(mainDomain);
        when(request.getMaxPages()).thenReturn(1);
        when(pageInfoService.getPageInfo(anyString(), anyString(), anyBoolean())).thenReturn(pageInfo1);
        when(pageInfo1.getLinks()).thenReturn(List.of(link1));

        ScoutWebResponse result = service.scoutWeb(request);

        verify(request).getMainDomain();
        verify(request, times(2)).getMaxPages();
        verify(pageInfoService).getPageInfo(eq(mainDomain), eq(mainDomain), eq(true));
        verify(pageInfo1).getLinks();

        assertThat(result).isNotNull();
        assertThat(result.getMainDomain()).isEqualTo(mainDomain);
        assertThat(result.getPageInfos()).containsExactly(pageInfo1);
    }
}