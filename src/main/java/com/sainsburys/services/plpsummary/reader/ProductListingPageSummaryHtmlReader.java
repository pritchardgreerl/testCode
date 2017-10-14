package com.sainsburys.services.plpsummary.reader;

import com.google.inject.Inject;
import com.sainsburys.services.plpsummary.response.Product;
import com.sainsburys.services.plpsummary.response.ProductListingPageSummary;
import com.sainsburys.services.plpsummary.response.ProductListingPageSummaryResponse;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ProductListingPageSummaryHtmlReader implements ProductListingPageSummaryReader {

    private static final Logger logger = LoggerFactory.getLogger(ProductListingPageSummaryHtmlReader.class);
    private ProductListingPageSummaryResponse productListingPageSummaryResponse;
    private ProductListingPageSummary productListingPageSummary;
    private ProductDetailsHtmlReader productDetailsHtmlReader;

    private BigDecimal total = BigDecimal.valueOf(0.00);

    @Inject
    public ProductListingPageSummaryHtmlReader(final ProductListingPageSummaryResponse productListingPageSummaryResponse,
                                               final ProductListingPageSummary productListingPageSummary,
                                               final ProductDetailsHtmlReader productDetailsHtmlReader) {
        this.productListingPageSummaryResponse = productListingPageSummaryResponse;
        this.productListingPageSummary = productListingPageSummary;
        this.productDetailsHtmlReader = productDetailsHtmlReader;
    }

    @Override
    public ProductListingPageSummaryResponse readMultipleProductListingPages(List<String> urlList) throws IOException {

        List<ProductListingPageSummary> productListingPageSummaryList = new ArrayList<>();
        for (String url : urlList) {
            if (isUrlValid(url)) {
                productListingPageSummary.setProductList(createListOfProducts(createListOfProductDetailPageUrlsForPage(url)));
                productListingPageSummary.setTotal(total);
                total = BigDecimal.valueOf(0.00);
                productListingPageSummaryList.add(productListingPageSummary);
            } else {
                throw new IllegalArgumentException("Passed in url is not valid: " + url);
            }
        }
        productListingPageSummaryResponse.setProductListingPageSummaries(productListingPageSummaryList);
        return productListingPageSummaryResponse;
    }

    @Override
    public ProductListingPageSummaryResponse readSingleProductListingPage(String url) throws IOException {

        if (isUrlValid(url)) {
            List<String> pdpUrlList = createListOfProductDetailPageUrlsForPage(url);
            productListingPageSummaryResponse.setProductList(createListOfProducts(pdpUrlList));
            productListingPageSummaryResponse.setTotal(total);
            return productListingPageSummaryResponse;
        } else {
            throw new IllegalArgumentException("Passed in url is not valid: " + url);
        }
    }

    /**
     * Checks to see if the url to be called is valid.
     * @param url: url to scrape.
     * @return boolean: true if valid, false is not.
     */
    private boolean isUrlValid(String url) {

        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    /**
     *
     * @param plpUrl: url of the product listing page to be read.
     * @return
     * @throws IOException
     */
    private List<String> createListOfProductDetailPageUrlsForPage(String plpUrl) throws IOException {

        final Document document = parseHtmlFromUrl(plpUrl);
        final Elements products = document.getElementsByClass("productInfo");

        return products.stream()
                .map(element -> element.getElementsByTag("a").attr("abs:href"))
                .collect(Collectors.toList());
    }

    /**
     * Creates list of Products with details read from their individual PDPs.
     * @param pdpUrlList: List of PDP urls to scrape.
     * @return productList: List of populated Product objects.
     * @throws IOException
     */
    private List<Product> createListOfProducts(List<String> pdpUrlList) throws IOException {

        List<Product> productList = new ArrayList<>();

        for (String str : pdpUrlList) {
            Product product = productDetailsHtmlReader.readProductDetails(parseHtmlFromUrl(str));
            productList.add(product);
            total = total.add(product.getPricePerUnit());
        }
        return productList;
    }

    /**
     * Reads in the html from a given url.
     * @param url: url of the webpage to be read.
     * @return Document: Html document containing all the html from the given url.
     * @throws IOException
     */
    private Document parseHtmlFromUrl(String url) throws IOException {

        return Jsoup.connect(url).get();
    }
}
