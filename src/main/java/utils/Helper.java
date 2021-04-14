package utils;

import drivermanagers.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
}
