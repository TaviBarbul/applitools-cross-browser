package pages;

import org.openqa.selenium.By;
import tests.traditional.BaseTest;

import static utils.Constants.productDetailsPageConstants.*;

public class ProductDetailsPage extends BaseTest {

    private static ProductDetailsPage instance = null;

    public static ProductDetailsPage getInstance() {
        if (instance == null) {
            instance = new ProductDetailsPage();
        }
        return instance;
    }

    public boolean isSizeSelectDisplayed() {
        return driver.isDisplayed(sizeSelect);
    }

    public void clickSizeSelect() {
        driver.clickElementUsingActions(sizeSelect);
    }

    public boolean checkSizeIsInList(String sizeName) {
        return driver.isPresent(By.xpath(String.format(sizeSelector, sizeName)));
    }

    public boolean isAddToCartDisplayed() {
        return driver.isDisplayed(addProductToCartButton);
    }

    public boolean isShoeDescriptionPresent() {
        return driver.isDisplayed(shoeDescription);
    }


    public String getShoeName() {
        return driver.getElementText(shoeName);
    }

    public String getShoePrice() {
        return driver.getElementText(price);
    }
}
