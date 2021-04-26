package com.railway.drivermanager;


public class DriverFactory {

    public static Driver createDriver(String type) {
        Driver driver;
        switch (type) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resource/driver/chromedriver_89.exe");
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.edge.driver", "src/main/resource/driver/msedgedriver_89.exe");
                driver = new EdgeDriver();
        }
        return driver;
    }
}