package com.sainsburys.services.plpsummary.config;

import com.google.inject.AbstractModule;
import com.sainsburys.services.plpsummary.reader.ProductDetailsHtmlReader;
import com.sainsburys.services.plpsummary.response.ProductListingPageSummary;
import com.sainsburys.services.plpsummary.reader.ProductListingPageSummaryHtmlReader;
import com.sainsburys.services.plpsummary.reader.ProductListingPageSummaryReader;
import com.sainsburys.services.plpsummary.response.ProductListingPageSummaryResponse;

/**
 * Google Guice config for the Product Listing Page Summary Service.
 */
public class ProductListingPageSummaryServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ProductListingPageSummaryReader.class).to(ProductListingPageSummaryHtmlReader.class);
        bind(ProductListingPageSummaryResponse.class);
        bind(ProductListingPageSummary.class);
        bind(ProductDetailsHtmlReader.class);
    }
}
