package tests.traditional;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.Constants.productsListPageConstants.*;

public class ShoppingExperienceTest extends BaseTest {

    @Test
    public void shoppingExperienceTest() {
        SoftAssert softAssert = new SoftAssert();
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(hackathonReporter(2, "Filter Results", filterButton.toString(), productsListPage.isFilterButtonDisplayed()), "The filter is not displayed");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", resetFiltersButton.toString(), productsListPage.isResetFilterButtonDisplayed()), "The reset filter is not displayed");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", filterOptionSelector, productsListPage.isFiltersInSection("Soccer ")), "The filter Soccer is not displayed");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", filterOptionSelector, productsListPage.isFiltersInSection("Black ")), "The filter Black is not displayed");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", filterOptionSelector, productsListPage.isFiltersInSection("Adibas ")), "The filter Adibas is not displayed");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", filterOptionSelector, productsListPage.isFiltersInSection("$0 - $50 ")), "The filter $0 - $50 is not displayed");


        productsListPage().selectFilterNamed("Black ");
        String expected = productsListPage().getExpectedFilteredItemsNumber("Black ");
        productsListPage().clickFilterButton();
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", productListSelector.toString(), Integer.valueOf(productsListPage().getAllProducts().size()).equals(Integer.valueOf(expected))), "Found " + productsListPage().getAllProducts().size() + " items but the expected is " + expected);

        String product = productsListPage.getRandomProduct();
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", addToCartButton, productsListPage.checkAddToCartButton(product)), "The add to cart button is not present");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", addToCompareButton, productsListPage.checkAddToCompareButton(product)), "The add to compare button is not present");
        softAssert.assertTrue(hackathonReporter(2, "Filter Results", addToFavoriteButton, productsListPage.checkAddToFavoriteButton(product)), "The add to favorites button is not present");
        softAssert.assertAll();
    }
}
