package drivermanager;


public class ChromeDriver extends Driver {

    @Override
    public void createWebDriver() {
        Driver.setWebDriver(new org.openqa.selenium.chrome.ChromeDriver());
    }
}