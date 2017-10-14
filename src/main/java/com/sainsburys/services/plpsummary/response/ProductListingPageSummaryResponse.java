package com.sainsburys.services.plpsummary.response;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

public class ProductListingPageSummaryResponse {

    @SerializedName("results")
    private List<Product> productList;

    private BigDecimal total;

    private List<ProductListingPageSummary> productListingPageSummaries;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(final List<Product> productList) {
        this.productList = productList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(final BigDecimal total) {
        this.total = total;
    }

    public List<ProductListingPageSummary> getProductListingPageSummaries() {
        return productListingPageSummaries;
    }

    public void setProductListingPageSummaries(final List<ProductListingPageSummary> productListingPageSummaries) {
        this.productListingPageSummaries = productListingPageSummaries;
    }
}
