package com.sainsburys.services.plpsummary.service;

import com.sainsburys.services.plpsummary.reader.ProductListingPageSummaryReader;
import com.sainsburys.services.plpsummary.request.ProductListingPageSummaryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductListingPageSummaryServiceTest {

    @Mock
    private ProductListingPageSummaryReader mockProductListingPageSummaryReader;

    @Mock
    private ProductListingPageSummaryRequest mockProductListingPageSummaryRequest;

    @InjectMocks
    private ProductListingPageSummaryService productListingPageSummaryService;

    @Test
    public void shouldCallReadSingleProductListingPageGivenSingleSourceInRequest() throws IOException {

        //Given
        List<String> urlList = new ArrayList<>();
        String url = "url";
        urlList.add(url);
        Mockito.when(mockProductListingPageSummaryRequest.getSourcesList()).thenReturn(urlList);

        //When
        productListingPageSummaryService.createProductListingPageSummary(mockProductListingPageSummaryRequest);

        //Then
        Mockito.verify(mockProductListingPageSummaryReader, Mockito.times(1)).readSingleProductListingPage(url);
    }

    @Test
    public void shouldCallReadMultipleProductListingPagesGivenMultipleSourcesInRequest() throws IOException {

        //Given
        List<String> urlList = new ArrayList<>();
        String url = "url";
        String url1 = "url1";
        urlList.add(url);
        urlList.add(url1);
        Mockito.when(mockProductListingPageSummaryRequest.getSourcesList()).thenReturn(urlList);

        //When
        productListingPageSummaryService.createProductListingPageSummary(mockProductListingPageSummaryRequest);

        //Then
        Mockito.verify(mockProductListingPageSummaryReader, Mockito.times(1)).readMultipleProductListingPages(urlList);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionGivenNullSourcesList() throws IOException {

        //Given
        Mockito.when(mockProductListingPageSummaryRequest.getSourcesList()).thenReturn(null);

        //When
        productListingPageSummaryService.createProductListingPageSummary(mockProductListingPageSummaryRequest);

        //Then
        //throws IllegalArgumentException.
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionGivenEmptySourcesList() throws IOException {

        //Given
        Mockito.when(mockProductListingPageSummaryRequest.getSourcesList()).thenReturn(Collections.EMPTY_LIST);

        //When
        productListingPageSummaryService.createProductListingPageSummary(mockProductListingPageSummaryRequest);

        //Then
        //throws IllegalArgumentException.
    }
}
