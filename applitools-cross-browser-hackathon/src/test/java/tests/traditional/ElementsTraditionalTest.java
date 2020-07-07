package tests.traditional;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.Constants.productsListPageConstants.*;

public class ElementsTraditionalTest extends BaseTest {

    @Test
    public void elementsTraditionalTest() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", pageLogo.toString(), productsListPage().isLogoDisplayed()), "The logo is not displayed");
        productsListPage().revealFiltersButton();

        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", filterButton.toString(), productsListPage.isFilterButtonDisplayed()), "The filter is not displayed");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", resetFiltersButton.toString(), productsListPage.isResetFilterButtonDisplayed()), "The reset filter is not displayed");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", filterOptionSelector, productsListPage.isFiltersInSection("Soccer ")), "The filter Soccer is not displayed");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", filterOptionSelector, productsListPage.isFiltersInSection("Black ")), "The filter Black is not displayed");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", filterOptionSelector, productsListPage.isFiltersInSection("Adibas ")), "The filter Adibas is not displayed");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", filterOptionSelector, productsListPage.isFiltersInSection("$0 - $50 ")), "The filter $0 - $50 is not displayed");

        productsListPage().closeFilters();
        productsListPage().revealSearchField();
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", mobileSearchField.toString(), productsListPage().checkSearchSection()), "The search field is not displayed");

        String product = productsListPage.getRandomProduct();
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", addToCartButton, productsListPage.checkAddToCartButton(product)), "The add to cart button is not present");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", addToCompareButton, productsListPage.checkAddToCompareButton(product)), "The add to compare button is not present");
        softAssert.assertTrue(hackathonReporter(1, "Cross-Device Elements Test", addToFavoriteButton, productsListPage.checkAddToFavoriteButton(product)), "The add to favorites button is not present");
        softAssert.assertAll();
    }
}
