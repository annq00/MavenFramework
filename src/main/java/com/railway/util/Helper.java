package com.railway.util;

import org.openqa.selenium.*;

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
        return sb.toString();
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static By getByFromElement(WebElement element) {
        By by;
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)]", "$1" + "")).split(":");

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
