package drivermanagers;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public abstract class DriverManager {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Logger logger = Logger.getLogger(DriverManager.class);
    protected abstract void createWebDriver();

    public void quitWebDriver(){
        if(driver!=null){
            driver.get().quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver(){
        createWebDriver();
        return driver.get();
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


}