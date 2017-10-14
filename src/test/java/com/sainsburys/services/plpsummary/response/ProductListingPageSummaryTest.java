package com.sainsburys.services.plpsummary.response;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductListingPageSummaryTest {

    @Mock
    private Product mockProduct;

    @Mock
    private BigDecimal mockTotal;

    @Test
    public void shouldGetAndSetProductListingPageTitle() {

        //Given
        String productListingPageTitle = "title";
        ProductListingPageSummary productListingPageSummary = new ProductListingPageSummary();

        //When
        productListingPageSummary.setProductListingPageTitle(productListingPageTitle);

        //Then
        Assert.assertEquals(productListingPageTitle, productListingPageSummary.getProductListingPageTitle());
    }

    @Test
    public void shouldGetAndSetProductList() {

        //Given
        List<Product> productList = new ArrayList<>();
        productList.add(mockProduct);
        ProductListingPageSummary productListingPageSummary = new ProductListingPageSummary();

        //When
        productListingPageSummary.setProductList(productList);

        //Then
        Assert.assertEquals(productList, productListingPageSummary.getProductList());
    }

    @Test
    public void shouldGetAndSetTotal() {

        //Given
        ProductListingPageSummary productListingPageSummary = new ProductListingPageSummary();

        //When
        productListingPageSummary.setTotal(mockTotal);

        //Then
        Assert.assertEquals(mockTotal, productListingPageSummary.getTotal());
    }
}
