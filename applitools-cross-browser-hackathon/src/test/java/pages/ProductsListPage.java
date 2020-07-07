package pages;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.traditional.BaseTest;

import java.util.List;

import static utils.Constants.productsListPageConstants.*;

public class ProductsListPage extends BaseTest {

    private static ProductsListPage instance = null;

    public static ProductsListPage getInstance() {
        if (instance == null) {
            instance = new ProductsListPage();
        }
        return instance;
    }

    public void revealSearchField() {
        if (driver.isDisplayed(revealSearchFieldButton))
            driver.click(revealSearchFieldButton);
    }

    public void revealFiltersButton() {
        if (driver.isDisplayed(revealFiltersButton))
            driver.click(revealFiltersButton);
    }

    public void clickFilterButton() {
        driver.click(filterButton);
    }

    public void closeFilters() {
        if (driver.isDisplayed(closeFiltersButton))
            driver.click(closeFiltersButton);
    }

    public void selectProduct(String productName) {
        driver.click(By.xpath(String.format(productSelector, productName)));
    }

    public boolean isLogoDisplayed() {
        return driver.isDisplayed(pageLogo);
    }

    public void selectFilterNamed(String filterName) {
        driver.click(By.xpath(String.format(filterOptionSelector, filterName)));
    }

    public String getExpectedFilteredItemsNumber(String filterName) {
        return driver.getElementText(By.xpath(String.format(expectedItemsNumber, filterName)));
    }

    public boolean isFilterButtonDisplayed() {
        return driver.isDisplayed(filterButton);
    }

    public boolean isResetFilterButtonDisplayed() {
        return driver.isDisplayed(resetFiltersButton);
    }

    public boolean isFiltersInSection(String filterName) {
        return driver.isDisplayed(By.xpath(String.format(filterOptionSelector, filterName)));
    }

    public boolean checkSearchSection() {
        return (driver.isDisplayed(searchField) || driver.isDisplayed(mobileSearchField));
    }

    public List<WebElement> getAllProducts() {
        return driver.webDriver.findElements(productListSelector);
    }

    public String getRandomProduct() {
        List<WebElement> list = getAllProducts();
        WebElement e = list.get(RandomUtils.nextInt(1, list.size()));
        return e.getText();
    }

    public boolean checkAddToFavoriteButton(String productName) {
        driver.scrollToElement(By.xpath(String.format(productImage, productName)));
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isDisplayed(By.xpath(String.format(addToFavoriteButton, productName)));
    }

    public boolean checkAddToCartButton(String productName) {
        driver.scrollToElement(By.xpath(String.format(productImage, productName)));
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isDisplayed(By.xpath(String.format(addToCartButton, productName)));
    }

    public boolean checkAddToCompareButton(String productName) {
        driver.scrollToElement(By.xpath(String.format(productImage, productName)));
        driver.hoverOverElement(By.xpath(String.format(productPrice, productName)));
        return driver.isDisplayed(By.xpath(String.format(addToCompareButton, productName)));
    }

    public String getProductPrice(String productName) {
        return driver.getElementText(By.xpath(String.format(productPrice, productName)));
    }
}
