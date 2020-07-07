package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.traditional.BaseTest;


import java.util.concurrent.TimeUnit;


public class Driver {

    private static Driver instance = null;
    public RemoteWebDriver webDriver;

    private Driver() {
        setNewDriver();
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }


    public void setNewDriver() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webDriver = null;
        }
        try {
            String browserType = BaseTest.browser;
            String path = System.getProperty("user.dir");
            System.out.println(path);
            if (browserType == null) {
                return;
            }

            switch (browserType.toLowerCase()) {
                case "chrome":
                    /*
                     * Initializing Chrome remote driver
                     */
                    System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/chromedriver");
                    webDriver = new ChromeDriver();
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();
                    break;

                case "firefox":
                    /*
                     * Initializing Firefox remote driver
                     */
                    System.setProperty("webdriver.firefox.driver", path+"/src/test/resources/geckodriver");
                    webDriver = new FirefoxDriver();
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();

                    break;

                case "edge":
                    /*
                     * Initializing Edge chromium remote driver
                     * Could not find any other way to make edge start using a grid like firefox and chrome
                     */
                    System.setProperty("webdriver.edge.driver", path+"/src/test/resources/msedgedriver");
                    webDriver = new EdgeDriver();
                    webDriver.manage().window().setSize(new Dimension(BaseTest.width, BaseTest.height));
                    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    webDriver.manage().deleteAllCookies();

                    break;
                default:

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exit() {
        try {
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            webDriver = null;
            instance = null;
        }
    }

    public void navigateTo(String URL) {
        webDriver.navigate().to(URL);
    }

    public boolean isDisplayed(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return webDriver.findElement(by).isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return webDriver.findElement(by) != null;
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void click(By by) {
        scrollToElement(by);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        webDriver.findElement(by).click();
    }

    public String getElementText(By by) {
        return webDriver.findElement(by).getText();
    }

    public void hoverOverElement(By by) {
        scrollToElement(by);
        Actions hover = new Actions(webDriver);
        hover.moveToElement(webDriver.findElement(by)).build().perform();
    }

    public void clickElementUsingActions(By by) {
        scrollToElement(by);
        Actions click = new Actions(webDriver);
        click.moveToElement(webDriver.findElement(by)).click().build().perform();
    }

    public void scrollToElement(By by) {
        try {
            WebElement element = webDriver.findElement(by);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
