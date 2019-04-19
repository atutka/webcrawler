package com.wiprodigital.webcrawler.service;

import java.io.IOException;
import java.util.List;

import com.wiprodigital.webcrawler.exception.RedirectionException;
import com.wiprodigital.webcrawler.response.PageInfo;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*"})
public class PageInfoServiceImplTest
{
    private final static String REGEX = ".*[.](tif|tiff|gif|jpeg|jpg|jif|jfif|jp2|jpx|j2k|j2c|fpx|pcd|png|pdf|docx|svg)";

    private PageInfoServiceImpl service = new PageInfoServiceImpl();

    @Before
    public void setUp()
    {
        ReflectionTestUtils.setField(service, "contentLinkRegex", REGEX);
    }

    @Test
    public void testGetPageInfoWithSuccess() throws IOException
    {
        var link = "https://wiprodigital.com/case";
        var mainDomain = "https://wiprodigital.com/";
        var connection = mock(Connection.class);
        var document = mock(Document.class);
        var response = mock(Connection.Response.class);
        var statusCode = 200;

        var link1 = "https://wiprodigital.com/case/something";
        var link2 = "https://facebook.com/";
        var link3 = "https://abc.wiprodigital.com/";
        var link4 = "https://wiprodigital.com/case#aaa";
        var link5 = "aaaa.pl";
        var link6 = "https://wiprodigital.com/case.png";

        var element1 = mock(Element.class);
        when(element1.absUrl(anyString())).thenReturn(link1);
        var element2 = mock(Element.class);
        when(element2.absUrl(anyString())).thenReturn(link2);
        var element3 = mock(Element.class);
        when(element3.absUrl(anyString())).thenReturn(link3);
        var element4 = mock(Element.class);
        when(element4.absUrl(anyString())).thenReturn(link4);
        var element5 = mock(Element.class);
        when(element5.absUrl(anyString())).thenReturn(link5);
        var element6 = mock(Element.class);
        when(element6.absUrl(anyString())).thenReturn(link6);

        var elements1 = new Elements(List.of(element1, element2, element3, element4, element5));
        var elements2 = new Elements(List.of(element6));

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        when(connection.get()).thenReturn(document);
        when(connection.response()).thenReturn(response);
        when(response.statusCode()).thenReturn(statusCode);
        when(document.select(anyString())).thenReturn(elements2, elements1);

        PageInfo result = service.getPageInfo(link, mainDomain, false);

        verify(connection).get();
        verify(connection).response();
        verify(connection).followRedirects(eq(false));
        verify(response).statusCode();
        verify(document).select(eq("a[href]"));
        verify(document).select(eq("img[src]"));
        verify(element1).absUrl(eq("href"));
        verify(element2).absUrl(eq("href"));
        verify(element3).absUrl(eq("href"));
        verify(element4).absUrl(eq("href"));
        verify(element5).absUrl(eq("href"));
        verify(element6).absUrl(eq("src"));

        assertThat(result).isNotNull();
        assertThat(result.getUrl()).isEqualTo(link);
        assertThat(result.isBroken()).isFalse();
        assertThat(result.getLinks()).containsExactly(link1+"/");
        assertThat(result.getExternalLinks()).containsExactly(link2);
        assertThat(result.getStaticContentLinks()).containsExactly(link6);
        assertThat(result.getSubdomains()).containsExactly(link3);
    }

    @Test
    public void testGetPageInfoWithRedirectionException() throws IOException
    {
        var link = "https://wiprodigital.com/case";
        var mainDomain = "https://wiprodigital.com/";
        var connection = mock(Connection.class);
        var document = mock(Document.class);
        var response = mock(Connection.Response.class);
        var statusCode = 301;

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        when(connection.get()).thenReturn(document);
        when(connection.response()).thenReturn(response);
        when(response.statusCode()).thenReturn(statusCode);

        assertThatThrownBy(() -> service.getPageInfo(link, mainDomain, true))
                .isInstanceOf(RedirectionException.class);

        verify(connection).get();
        verify(connection).response();
        verify(connection).followRedirects(eq(false));
        verify(response).statusCode();
    }

    @Test
    public void testGetPageInfoWhenPageNotFound() throws IOException
    {
        var link = "https://wiprodigital.com/case";
        var mainDomain = "https://wiprodigital.com/";
        var connection = mock(Connection.class);
        var exception = mock(HttpStatusException.class);

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connection);
        when(connection.get()).thenThrow(exception);

        PageInfo result = service.getPageInfo(link, mainDomain, true);

        verify(connection).get();
        verify(connection).followRedirects(eq(false));

        assertThat(result).isNotNull();
        assertThat(result.getUrl()).isEqualTo(link);
        assertThat(result.isBroken()).isTrue();
        assertThat(result.getLinks()).isEmpty();
        assertThat(result.getSubdomains()).isEmpty();
        assertThat(result.getStaticContentLinks()).isEmpty();
        assertThat(result.getExternalLinks()).isEmpty();
    }

}