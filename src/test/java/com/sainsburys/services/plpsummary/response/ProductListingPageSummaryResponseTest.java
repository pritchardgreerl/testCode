package com.sainsburys.services.plpsummary.response;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProductListingPageSummaryResponseTest {

    @Mock
    private Product mockProduct;

    @Mock
    private ProductListingPageSummary mockProductListingPageSummary;

    @Test
    public void shouldGetAndSetProductList() {

        //Given
        List<Product> productList = new ArrayList<>();
        productList.add(mockProduct);
        ProductListingPageSummaryResponse productListingPageSummaryResponse = new ProductListingPageSummaryResponse();

        //When
        productListingPageSummaryResponse.setProductList(productList);

        //Then
        Assert.assertEquals(productList, productListingPageSummaryResponse.getProductList());
    }

    @Test
    public void shouldGetAndSetTotal() {

        //Given
        BigDecimal total = new BigDecimal(2.00);
        ProductListingPageSummaryResponse productListingPageSummaryResponse = new ProductListingPageSummaryResponse();

        //When
        productListingPageSummaryResponse.setTotal(total);

        //Then
        Assert.assertEquals(total, productListingPageSummaryResponse.getTotal());
    }

    @Test
    public void shouldGetAndSetProductListingPageSummaries() {

        //Given
        List<ProductListingPageSummary> productListingPageSummaries = new ArrayList<>();
        productListingPageSummaries.add(mockProductListingPageSummary);
        ProductListingPageSummaryResponse productListingPageSummaryResponse = new ProductListingPageSummaryResponse();

        //When
        productListingPageSummaryResponse.setProductListingPageSummaries(productListingPageSummaries);

        //Then
        Assert.assertEquals(productListingPageSummaries, productListingPageSummaryResponse.getProductListingPageSummaries());
    }
}