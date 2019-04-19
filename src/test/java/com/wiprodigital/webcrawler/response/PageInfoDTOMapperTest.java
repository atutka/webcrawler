package com.wiprodigital.webcrawler.response;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PageInfoDTOMapperTest
{
    private PageInfoDTOMapper mapper = new PageInfoDTOMapper();

    @Test
    public void testMap()
    {
        var url = "url";
        var link = "link";
        var externalLink = "externalLink";
        var staticContentLink = "staticContentLink";
        var subdomain = "subdomain";
        var pageInfo = mock(PageInfo.class);

        when(pageInfo.getUrl()).thenReturn(url);
        when(pageInfo.getLinks()).thenReturn(List.of(link));
        when(pageInfo.getExternalLinks()).thenReturn(List.of(externalLink));
        when(pageInfo.getStaticContentLinks()).thenReturn(List.of(staticContentLink));
        when(pageInfo.getSubdomains()).thenReturn(List.of(subdomain));

        PageInfoDTO result = mapper.map(pageInfo);

        verify(pageInfo).getUrl();
        verify(pageInfo).getLinks();
        verify(pageInfo).getExternalLinks();
        verify(pageInfo).getStaticContentLinks();
        verify(pageInfo).getSubdomains();

        assertThat(result).isNotNull();
        assertThat(result.getUrl()).isEqualTo(url);
        assertThat(result.getLinks()).containsExactly(link);
        assertThat(result.getExternalLinks()).containsExactly(externalLink);
        assertThat(result.getStaticContentLinks()).containsExactly(staticContentLink);
        assertThat(result.getSubdomains()).containsExactly(subdomain);
    }
}