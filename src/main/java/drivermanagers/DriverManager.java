package drivermanagers;


import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected static WebDriver driver;
    protected abstract void createWebDriver();

    public void quitWebDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }

    public WebDriver getWebDriver(){
        if(driver==null){
            createWebDriver();
        }
        return driver;
    }

    public static WebDriver initWebDriver(){
        return driver;
    }
}