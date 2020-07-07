package tests.traditional;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.Constants.productDetailsPageConstants.*;
import static utils.Constants.productsListPageConstants.*;

public class ProductDetailsTest extends BaseTest {

    @Test
    public void productDetailsTest() {
        SoftAssert softAssert = new SoftAssert();
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(hackathonReporter(3, "Product Details test", filterButton.toString(), productsListPage.isFilterButtonDisplayed()), "The filter is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", resetFiltersButton.toString(), productsListPage.isResetFilterButtonDisplayed()), "The reset filter is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", filterOptionSelector, productsListPage.isFiltersInSection("Soccer ")), "The filter Soccer is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", filterOptionSelector, productsListPage.isFiltersInSection("Black ")), "The filter Black is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", filterOptionSelector, productsListPage.isFiltersInSection("Adibas ")), "The filter Adibas is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", filterOptionSelector, productsListPage.isFiltersInSection("$0 - $50 ")), "The filter $0 - $50 is not displayed");

        productsListPage().selectFilterNamed("Black ");
        productsListPage().clickFilterButton();
        String expectedPrice = productsListPage().getProductPrice("Appli Air x Night");
        productsListPage().selectProduct("Appli Air x Night");


        softAssert.assertTrue(hackathonReporter(3, "Product Details test", shoeName.toString(), productDetailsPage().getShoeName().equals("Appli Air x Night")), "Shoe name is different from the selected one");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", price.toString(), productDetailsPage().getShoePrice().equals(expectedPrice)), "Shoe price is different from the selected one");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", sizeSelect.toString(), productDetailsPage.isSizeSelectDisplayed()), "Shoe size select is not displayed");
        productDetailsPage.clickSizeSelect();
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", sizeSelector, productDetailsPage.checkSizeIsInList("Small (S)")), "Shoe size Small is not present");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", sizeSelector, productDetailsPage.checkSizeIsInList("M")), "Shoe size M is not present");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", sizeSelector, productDetailsPage.checkSizeIsInList("L")), "Shoe size L is not present");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", sizeSelector, productDetailsPage.checkSizeIsInList("XL")), "Shoe size XL is not present");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", shoeDescription.toString(), productDetailsPage.isShoeDescriptionPresent()), "Shoe description is not displayed");
        softAssert.assertTrue(hackathonReporter(3, "Product Details test", addProductToCartButton.toString(), productDetailsPage.isAddToCartDisplayed()), "Add to cart button is not displayed");
        softAssert.assertAll();
    }
}
