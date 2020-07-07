package tests.modern;


import com.applitools.eyes.*;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.ProductDetailsPage;
import pages.ProductsListPage;
import tests.traditional.BaseTest;
import utils.Driver;

import static utils.Constants.productDetailsPageConstants.*;
import static utils.Constants.productDetailsPageConstants.addProductToCartButton;
import static utils.Constants.productsListPageConstants.*;
import static utils.Constants.productsListPageConstants.addToFavoriteButton;

public class UltraFastGridTests {

    private final int viewPortWidth = 1200;
    private final int viewPortHeight = 700;
    String myEyesServer = "https://eyes.applitools.com/"; //set to your server/cloud URL
    String appName = "AppliFashion";
    String batchName = "UFG Hackathon";
    private int concurrentSessions = 7;
    private String apiKey = "pJea03eU2mNT109LYC0yEnTPdK5E7v3fLAO0rlxQCciC0110";
    private EyesRunner runner = null;
    private Configuration suiteConfig;
    private Eyes eyes;
    private WebDriver webDriver;
//    String url = "https://demo.applitools.com/gridHackathonV1.html";
    String url = "https://demo.applitools.com/gridHackathonV2.html";

    @BeforeSuite
    public void beforeTestSuite() {
        runner = new VisualGridRunner(concurrentSessions);
        // Create a configuration object, we will use this when setting up each test
        suiteConfig = (Configuration) new Configuration()
                // Visual Grid configurations
                .addBrowser(1200, 700, BrowserType.CHROME)
                .addBrowser(1200, 700, BrowserType.FIREFOX)
                .addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM)
                .addBrowser(768, 700, BrowserType.CHROME)
                .addBrowser(768, 700, BrowserType.FIREFOX)
                .addBrowser(768, 700, BrowserType.EDGE_CHROMIUM)
                .addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT)
                // Checkpoint configurations
                // Test specific configurations ....
                .setViewportSize(new RectangleSize(viewPortWidth, viewPortHeight))
                // Test suite configurations
                .setApiKey(apiKey)
                .setServerUrl(myEyesServer)
                .setAppName(appName)
                .setBatch(new BatchInfo(batchName)
                        /* ...other configurations */);
    }

    @BeforeMethod
    public void beforeEachTest(ITestResult result) {
        // Create the Eyes instance for the test and associate it with the runner
        eyes = new Eyes(runner);
        eyes.setConfiguration(suiteConfig);
        webDriver = new ChromeDriver();
    }

    @Test
    public void task1CrossDeviceElementsTest() {
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 1");
        eyes.setConfiguration(testConfig);

        // Open Eyes, the application,test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);

        // Now run the test
        driver.get(url);   // navigate to website
        eyes.checkWindow("Cross-Device Elements Test");
    }

    @Test
    public void task2ShoppingExperienceTest(){
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 2");
        eyes.setConfiguration(testConfig);

        // Open Eyes, the application,test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);

        // Now run the test
        driver.get(url);   // navigate to website
        eyes.checkWindow("Filter Results");

        webDriver.findElement(By.xpath(String.format(filterOptionSelector, "Black "))).click();
        webDriver.findElement(filterButton).click();

        eyes.check("Product Grid", Target.region(By.id("product_grid")));
    }

    @Test
    public void task3ProductDetailsTest(){
        // Update the Eyes configuration with test specific values
        Configuration testConfig = eyes.getConfiguration();
        testConfig.setTestName("Task 3");
        eyes.setConfiguration(testConfig);

        // Open Eyes, the application,test name and viewport size are already configured
        WebDriver driver = eyes.open(webDriver);

        // Now run the test
        driver.get(url);   // navigate to website

        webDriver.findElement(By.xpath(String.format(filterOptionSelector, "Black "))).click();
        webDriver.findElement(filterButton).click();
        webDriver.findElement(By.xpath(String.format(productSelector, "Appli Air x Night"))).click();
        eyes.checkWindow("Product Details test");
    }

    @AfterMethod
    public void afterEachTest(ITestResult result) {
        // check if an exception was thrown
        boolean testFailed = result.getStatus() == ITestResult.FAILURE;
        if (!testFailed) {
            // Close the Eyes instance, no need to wait for results, we'll get those at the end in afterTestSuite
            eyes.closeAsync();
        } else {
            // There was an exception so the test may be incomplete - abort the test
            eyes.abortAsync();
        }
        webDriver.quit();
    }

    @AfterSuite
    public void afterTestSuite(ITestContext testContext) {
        //Wait until the test results are available and retrieve them
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        for (TestResultContainer result : allTestResults) {
            handleTestResults(result);
        }
    }

    void handleTestResults(TestResultContainer summary) {
        Throwable ex = summary.getException();
        if (ex != null) {
            System.out.printf("System error occured while checking target.\n");
        }
        TestResults result = summary.getTestResults();
        if (result == null) {
            System.out.printf("No test results information available\n");
        } else {
            System.out.printf("URL = %s, AppName = %s, testname = %s, Browser = %s,OS = %s, viewport = %dx%d, matched = %d,mismatched = %d, missing = %d,aborted = %s\n",
                    result.getUrl(),
                    result.getAppName(),
                    result.getName(),
                    result.getHostApp(),
                    result.getHostOS(),
                    result.getHostDisplaySize().getWidth(),
                    result.getHostDisplaySize().getHeight(),
                    result.getMatches(),
                    result.getMismatches(),
                    result.getMissing(),
                    (result.isAborted() ? "aborted" : "no"));
        }
    }
}
