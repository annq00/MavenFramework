package drivermanagers;


import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeDriverManager extends DriverManager {

    @Override
    public void createWebDriver(){
        DriverManager.driver.set(new ChromeDriver());
    }

}