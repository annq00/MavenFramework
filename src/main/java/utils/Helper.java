package utils;

import drivermanagers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Helper {

    public static String generateTimeStampString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String generateRandomString(int length) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEddMMMMyyyyHHmmss");
        Date date = new Date();
        String dateTime = formatter.format(date);

        char[] keys = dateTime.toCharArray();

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            char temp = keys[random.nextInt(keys.length)];
            sb.append(temp);
        }
        String result = sb.toString();
        return result;
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor je = (JavascriptExecutor) DriverManager.driver.get();
        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void waitForLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver.get(), 60);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete';"));
    }

    public static void waitForCbbText(WebElement element){
        Select tempCbb = new Select(element);
        WebElement tempElement = tempCbb.getFirstSelectedOption();
        WebDriverWait wait = new WebDriverWait(DriverManager.driver.get(), 60);
        wait.until(ExpectedConditions.visibilityOf(tempElement));
    }

    public static void select(WebElement element, int index) {
        try{
            By xpatch = Helper.getByFromElement(element);
            WebElement temp = DriverManager.driver.get().findElement(xpatch);
            Select select = new Select(temp);
            select.selectByIndex(index);
        }catch (StaleElementReferenceException e){
            select(element, index);
        }

    }

    public static String getCbbText(WebElement dropdown) {
        String currentOpt=null;
        try {
            By xpatch = Helper.getByFromElement(dropdown);
            WebElement temp = DriverManager.driver.get().findElement(xpatch);

            Select sl = new Select(temp);
            currentOpt = sl.getFirstSelectedOption().getText();

        } catch (StaleElementReferenceException e) {
            getCbbText(dropdown);
        }
        return currentOpt;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getCbbSize(WebElement dropdown) {
        List<WebElement> list =null;
        try {
            By xpatch = Helper.getByFromElement(dropdown);
            WebElement temp = DriverManager.driver.get().findElement(xpatch);

            Select sl = new Select(temp);
            list = sl.getOptions();


        } catch (StaleElementReferenceException e) {
            getCbbSize(dropdown);
        }
        return list.size();
    }


    public static void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver.get(), 3);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.driver.get(), 3);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement getWebElement(String locator) {
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

        return DriverManager.driver.get().findElement(locatorBy);
    }

    public static boolean retryingFindClick(By by) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 60) {
            try {
                DriverManager.driver.get().findElement(by).click();
                result = true;
                System.out.println(attempts);
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static By getByFromElement(WebElement element) {
        By by = null;
        //[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }


}
