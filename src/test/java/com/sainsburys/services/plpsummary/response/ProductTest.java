package com.sainsburys.services.plpsummary.response;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    @Mock
    private BigDecimal mockPricePerUnit;

    private String title = "title";

    private String description = "description";

    private String kcalPer100g = "kcalPer100g";

    @Test
    public void shouldSetAllFieldsGivenValidParametersInConstructor() {

        //Given
        //Valid product variables.

        //When
        Product product = new Product(title, kcalPer100g, mockPricePerUnit, description);

        //Then
        Assert.assertNotNull(product);
        Assert.assertEquals(title, product.getTitle());
        Assert.assertEquals(kcalPer100g, product.getKcalPer100g());
        Assert.assertEquals(mockPricePerUnit, product.getPricePerUnit());
        Assert.assertEquals(description, product.getDescription());
    }

    @Test
    public void shouldReturnProductInformationGivenValidDateWhenToStringCalled() {

        //Given
        String expectedProductToString = "Product{title=" + title + ", kcalPer100g=" + kcalPer100g + ", pricePerUnit=" + mockPricePerUnit + ", description=" + description + "}";
        Product product = new Product(title, kcalPer100g, mockPricePerUnit, description);

        //When
        String productString = product.toString();

        //Then
        Assert.assertEquals(expectedProductToString, productString);
    }
}
