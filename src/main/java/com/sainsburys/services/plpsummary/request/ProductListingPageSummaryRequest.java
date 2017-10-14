package com.sainsburys.services.plpsummary.request;

import java.util.List;

public class ProductListingPageSummaryRequest {

    private List<String> sourcesList;

    public ProductListingPageSummaryRequest(List<String> sourcesList) {
        this.sourcesList = sourcesList;
    }

    public List<String> getSourcesList() {
        return sourcesList;
    }
}
