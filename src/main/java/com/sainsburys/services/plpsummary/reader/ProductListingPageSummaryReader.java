package com.sainsburys.services.plpsummary.reader;

import com.sainsburys.services.plpsummary.response.ProductListingPageSummaryResponse;

import java.io.IOException;
import java.util.List;

public interface ProductListingPageSummaryReader {

    ProductListingPageSummaryResponse readSingleProductListingPage(String source) throws IOException;

    ProductListingPageSummaryResponse readMultipleProductListingPages(List<String> sourcesList) throws IOException;

}
