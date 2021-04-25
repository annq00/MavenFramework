package drivermanagers;


import Control.Element;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public abstract class Driver {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Logger logger = Logger.getLogger(Driver.class);

    protected abstract void createWebDriver();

    public WebDriver initWebDriver() {
        createWebDriver();
        return getWebDriver();
    }

    public static WebDriver getWebDriver() {
        return driver.get();
    }

    protected static void setWebDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static String captureScreenshot(String filename, String filepath) {
        logger.info("Capture screenshot");
        String path = "";
        try {
            // Taking the screen using TakesScreenshot Class
            File objScreenCaptureFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
            // Storing the image in the local system.
            File dest = new File(filepath + File.separator + filename);
            FileUtils.copyFile(objScreenCaptureFile, dest);
            path = dest.getAbsolutePath();
        } catch (Exception e) {
            logger.error("An error occurred when capturing screen shot: " + e.getMessage());
        }
        return path;
    }

    public static void navigateTo(String url) {
        getWebDriver().navigate().to(url);
    }

    public static void maximizeWindow() {
        getWebDriver().manage().window().maximize();
    }

    public static void scrollToElement(Element element) {
        WebElement webElement = element.getCurrentElement();
        JavascriptExecutor je = (JavascriptExecutor) Driver.getWebDriver();
        je.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public static void waitForLoad() {
        WebDriverWait wait = new WebDriverWait(Driver.getWebDriver(), 60);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
    }

    public static void waitFor(Element element) {
        WebElement webElement = element.getCurrentElement();
        WebDriverWait wait = new WebDriverWait(Driver.getWebDriver(), 3);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static WebElement findElement(String locator) {
        char temp = locator.charAt(0);
        By locatorBy = null;
        switch (temp) {
            case '/':
                locatorBy = By.xpath(locator);
                break;
            case '.':
                locatorBy = By.cssSelector(locator);
                break;
            case 'i':
                locatorBy = By.id(locator.substring(3));
                break;
            case 'c':
                locatorBy = By.className(locator.substring(3));
                break;
            case 'n':
                locatorBy = By.name(locator.substring(3));
                break;
            case 't':
                locatorBy = By.tagName(locator.substring(3));
                break;
        }

        return Driver.getWebDriver().findElement(locatorBy);
    }

    public static List<WebElement> findElements(String locator) {
        char temp = locator.charAt(0);
        By locatorBy = null;
        switch (temp) {
            case '/':
                locatorBy = By.xpath(locator);
                break;
            case '.':
                locatorBy = By.cssSelector(locator);
                break;
            case 'i':
                locatorBy = By.id(locator.substring(2));
                break;
            case 'c':
                locatorBy = By.className(locator.substring(2));
                break;
            case 'n':
                locatorBy = By.name(locator.substring(2));
                break;
            case 't':
                locatorBy = By.tagName(locator.substring(2));
                break;
        }
        return Driver.getWebDriver().findElements(locatorBy);
    }
}