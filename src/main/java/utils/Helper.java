package utils;

import drivermanagers.DriverManager;
import org.openqa.selenium.*;
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

    public static String generateRandomString(int length){
        SimpleDateFormat formatter = new SimpleDateFormat("EEddMMMMyyyyHHmmss");
        Date date = new Date();
        String dateTime = formatter.format(date);

        char[] keys = dateTime.toCharArray();

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for(int i = 0; i<length; i++){
            char temp = keys[random.nextInt(keys.length)];
            sb.append(temp);
        }
        String result = sb.toString();
        return result;
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor je = (JavascriptExecutor) DriverManager.driver.get();
        je.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static void select(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static String selectCurrentOption(WebElement dropdown){
        Select sl = new Select(dropdown);
        String currentOpt = sl.getFirstSelectedOption().getText();
        return currentOpt;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getCbbSize(WebElement dropdown){
        Select sl = new Select(dropdown);
        List<WebElement> list = sl.getOptions();
        return list.size();
    }

    public static void waitFor(WebElement element){
        WebDriverWait wait = new WebDriverWait(DriverManager.driver.get(), 3);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement getWebElement(String locator){
        char temp = locator.charAt(0);
        By locatorBy = null;
        switch (temp){
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

}
