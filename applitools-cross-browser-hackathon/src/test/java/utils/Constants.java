package utils;

import org.openqa.selenium.By;

public class Constants {

    public interface productDetailsPageConstants {
        By sizeSelect = By.className("nice-select");
        String sizeSelector = "//li[contains(@class,'option') and text()='%s']";
        By addProductToCartButton = By.className("btn_add_to_cart");
        By shoeName = By.id("shoe_name");
        By shoeDescription = By.className("prod_info");
        By price = By.className("new_price");
    }

    public interface productsListPageConstants {
        By searchField = By.xpath("//div[@class='custom-search-input']/input[contains(@id,'INPUTtext')]");
        By mobileSearchField = By.xpath("//div[contains(@class,'search_mob')]/input[contains(@id,'INPUTtext')]");
        By revealSearchFieldButton = By.className("btn_search_mob");
        String filterOptionSelector = "//label[@class='container_check' and text()='%s']";
        String expectedItemsNumber = filterOptionSelector + "/small";
        By revealFiltersButton = By.id("ti-filter");
        By filterButton = By.id("filterBtn");
        By resetFiltersButton = By.id("resetBtn");
        By closeFiltersButton = By.cssSelector(".open_filters .ti-close");
        String productSelector = "//a[contains(@href,'ProductDetails')]/../../a/h3[text()='%s']";
        String addToFavoriteButton = productSelector + "/../../ul/li/a[(@title='Add to favorites' or @data-original-title='Add to favorites')]";
        String addToCartButton = productSelector + "/../../ul/li/a[(@title='Add to cart' or @data-original-title='Add to cart')]";
        String addToCompareButton = productSelector + "/../../ul/li/a[(@title='Add to compare' or @data-original-title='Add to compare')]";
        String productPrice = productSelector + "/../..//span[@class='new_price']";
        By pageLogo = By.id("logo");
        By productListSelector = By.xpath("//a[contains(@href,'ProductDetails')]/../../a/h3");
        String productImage = "//div[@id='product_grid']//img[@alt='%s']";
    }
}
