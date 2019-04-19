package com.wiprodigital.webcrawler.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ScoutWebRequestMapperTest
{
    private ScoutWebRequestMapper mapper = new ScoutWebRequestMapper();

    @Test
    public void testMap()
    {
        var mainDomain = "maindomain";
        var maxPages = 10;
        var request = mock(ScoutWebRequestDTO.class);

        when(request.getMainDomain()).thenReturn(mainDomain);
        when(request.getMaxPages()).thenReturn(maxPages);

        ScoutWebRequest result = mapper.map(request);

        verify(request).getMainDomain();
        verify(request).getMaxPages();

        assertThat(result).isNotNull();
        assertThat(result.getMainDomain()).isEqualTo(mainDomain);
        assertThat(result.getMaxPages()).isEqualTo(maxPages);
    }

}