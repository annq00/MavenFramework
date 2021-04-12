package drivermanagers;


import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
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


}