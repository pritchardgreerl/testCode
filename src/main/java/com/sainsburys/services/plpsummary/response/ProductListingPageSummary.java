package com.sainsburys.services.plpsummary.response;

import java.math.BigDecimal;
import java.util.List;

public class ProductListingPageSummary {

    private String productListingPageTitle;

    private List<Product> productList;

    private BigDecimal total;

    public String getProductListingPageTitle() {
        return productListingPageTitle;
    }

    public void setProductListingPageTitle(final String productListingPageTitle) {
        this.productListingPageTitle = productListingPageTitle;
    }

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
}
