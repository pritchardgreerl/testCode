package com.sainsburys.services.plpsummary.reader;

import com.sainsburys.services.plpsummary.response.Product;
import com.sainsburys.services.plpsummary.response.ProductListingPageSummary;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *
 */
public class ProductDetailsHtmlReader {

    private static final Logger logger = LoggerFactory.getLogger(ProductListingPageSummaryHtmlReader.class);

    /**
     * Reads and sets up a new Product with the following product details read from a passed in html document of a product details page:
     * - title
     * - description
     * - pricePerUnit
     * - kcalPer100g
     * @param document: Html document from a product details page.
     * @return product: Populated Product object.
     */
    public Product readProductDetails(Document document) {

        String title = readProductTitle(document);
        String description = readProductDescription(document);
        BigDecimal pricePerUnit = readProductPricePerUnit(document);
        String kcalPer100g = readKcalPer100g(document);
        return new Product(title, description, pricePerUnit, kcalPer100g);
    }

    /**
     * Returns the product title from the product details page.
     * @param document: Html document from a product details page.
     * @return String: product title.
     */
    private String readProductTitle(Document document) {
        logger.debug("Reading title of product");
        return document.getElementsByClass("productTitleDescriptionContainer").first().text();
    }

    /**
     * Returns the first line of the product description from the product details page.
     * @param document: Html document from a product details page.
     * @return String: 1st line of the product description.
     */
    private String readProductDescription(Document document) {

        logger.debug("Reading description of product");
        Element element = document.getElementById("information").getElementsByClass("productText").first();
        if (isDescriptionMultipleLines(element)) {
            logger.info("Product description covers multiple lines, just reading first");
            return element.getElementsByClass("memo").first().text().trim();
        }
        return element.getElementsByTag("p").text().trim();
    }

    /**
     * Determines if the product description covers multiple lines in the product details page.
     * @param element: Html element containing the product description.
     * @return boolean: true if multiple lines, false if not.
     */
    private boolean isDescriptionMultipleLines(Element element) {

        return !element.getElementsByClass("memo").isEmpty();
    }

    /**
     * Returns the price per unit of product from the product details page.
     * @param document: Html document from a product details page.
     * @return BigDecimal: pricePerUnit.
     */
    private BigDecimal readProductPricePerUnit(Document document) {

        logger.debug("reading pricePerUnit of product");
        return new BigDecimal(document.getElementsByClass("pricePerUnit")
                .first()
                .text()
                .replaceAll("[^0-9.]+", "")).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Returns the kcal per 100g value from the nutrition information table on product details page.
     * @param document: Html document from a product details page.
     * @return String or null: kcalPer100g value if found otherwise will return null.
     */
    private String readKcalPer100g(Document document) {

        logger.debug("reading kcalPer100g for product");
        Elements table = document.getElementsByClass("nutritionTable");

        if (table != null && !table.isEmpty()) {
            logger.info("Element has table. Getting rows");
            Elements tableHeader = table.select("thead");
            Elements columnHeadings = tableHeader.get(0).select("th");
            OptionalInt column = IntStream.range(1, columnHeadings.size())
                    .filter(columnIndex -> columnHeadings.get(columnIndex).text().toLowerCase().contains("per 100g"))
                    .findFirst();
            if (column.isPresent() && column.getAsInt()-1 >= 0) {
                logger.info("column int is: " + column.getAsInt());
                Elements tableRows = table.get(0).select("tr");
                return tableRows.get(2).select("td").get(column.getAsInt()-1).text().replace("kcal", "");
            }
        }
        logger.info("No kcalPer100g found");
        return null;
    }
}
