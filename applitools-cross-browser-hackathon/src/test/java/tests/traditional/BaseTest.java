package tests.traditional;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.ProductDetailsPage;
import pages.ProductsListPage;
import utils.Driver;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class BaseTest {

    public static Driver driver = null;
    public ProductsListPage productsListPage = null;
    public ProductDetailsPage productDetailsPage = null;
    public static String browser;
    public static String device;
    public static String fileName;
    public static int width;
    public static int height;
    public static String viewport;

    public ProductDetailsPage productDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = ProductDetailsPage.getInstance();
        }
        return productDetailsPage;
    }

    public ProductsListPage productsListPage() {
        if (productsListPage == null) {
            productsListPage = ProductsListPage.getInstance();
        }
        return productsListPage;
    }

    public Driver driver() {
        if (driver == null) {
            driver = Driver.getInstance();
        }
        return driver;
    }

    @Parameters({"url", "width", "height", "browser", "device", "fileName"})
    @BeforeMethod
    public void setup(String url, int width, int height, String browser, String device, String fileName) {
        BaseTest.width = width;
        BaseTest.height = height;
        BaseTest.browser = browser;
        BaseTest.device = device;
        BaseTest.fileName = fileName;
        viewport = width + "X" + height;
        driver().navigateTo(url);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.exit();
        driver = null;
    }

    /**
     * A Helper to print the test result in the following format:
     * Task: <Task Number>, Test Name: <Test Name>, DOM Id:: <id>, Browser: <Browser>, Viewport: <Width x Height>, Device<Device type>, Status: <Pass | Fail>
     * <p>
     * Example: Task: 1, Test Name: Search field is displayed, DOM Id: DIV__customsear__41, Browser: Chrome, Viewport: 1200 x 700, Device: Laptop, Status: Pass
     *
     * @param task             int - 1, 2 or 3
     * @param testName         string - Something meaningful. E.g. 1.1 Search field is displayed
     * @param domId            string - DOM ID of the element
     * @param comparisonResult boolean - The result of comparing the "Expected" value and the "Actual" value.
     * @return boolean - returns the same comparison result back so that it can be used for further Assertions in the test code.
     */

    public boolean hackathonReporter(int task, String testName, String domId, boolean comparisonResult) {
        try (var writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Task: " + task + ", Test Name: " + testName + ", DOM Id: " + domId + ", Browser: " + browser
                    + ", Viewport: " + viewport + ", Device: " + device + ", Status: " + (comparisonResult ? "Pass" : "Fail"));
            writer.newLine();
        } catch (Exception e) {
            System.out.println("Error writing to report file");
            e.printStackTrace();
        }
        //returns the result so that it can be used for further Assertions in the test code.
        return comparisonResult;
    }
}
