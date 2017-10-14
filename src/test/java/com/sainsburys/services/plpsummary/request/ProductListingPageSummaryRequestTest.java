package com.sainsburys.services.plpsummary.request;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductListingPageSummaryRequestTest {

    @Test
    public void shouldSetSourcesListGivenCorrectValuesPassedIntoConstructor() {

        //Given
        List<String> sourcesList = new ArrayList<>();
        sourcesList.add("mockSource");

        //When
        ProductListingPageSummaryRequest productListingPageSummaryRequest = new ProductListingPageSummaryRequest(sourcesList);

        //then
        Assert.assertEquals("List should be equal to list passed in.", sourcesList, productListingPageSummaryRequest.getSourcesList());
    }
}