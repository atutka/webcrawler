package com.wiprodigital.webcrawler.response;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoutWebResponseDTOMapperTest
{
    @Mock
    private PageInfoDTOMapper pageInfoDTOMapper;

    @InjectMocks
    private ScoutWebResponseDTOMapper mapper;

    @Test
    public void testMap()
    {
        var mainDomain = "mainDomain";
        var pageInfo1Url = "pageInfo1Url";
        var pageInfo1 = mock(PageInfo.class);
        var pageInfo2 = mock(PageInfo.class);
        var pageInfoDTO = mock(PageInfoDTO.class);
        var response = mock(ScoutWebResponse.class);

        when(response.getMainDomain()).thenReturn(mainDomain);
        when(response.getPageInfos()).thenReturn(List.of(pageInfo1, pageInfo2));
        when(pageInfo1.isBroken()).thenReturn(true);
        when(pageInfo1.getUrl()).thenReturn(pageInfo1Url);
        when(pageInfoDTOMapper.map(anyList())).thenReturn(List.of(pageInfoDTO));

        ScoutWebResponseDTO result = mapper.map(response);

        verify(response).getMainDomain();
        verify(response).getPageInfos();
        verify(pageInfo1, times(2)).isBroken();
        verify(pageInfo1).getUrl();
        verify(pageInfoDTOMapper).map(eq(List.of(pageInfo2)));

        assertThat(result).isNotNull();
        assertThat(result.getPageInfos()).containsExactly(pageInfoDTO);
        assertThat(result.getBrokenLinks()).containsExactly(pageInfo1Url);
    }
}