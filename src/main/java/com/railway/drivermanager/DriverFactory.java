package com.railway.drivermanager;


public class DriverFactory {

    public static Driver createDriver(String type) {
        Driver driver;
        switch (type) {
            case "edge":
                System.setProperty("webdriver.edge.driver", "src/main/resource/driver/msedgedriver_89.exe");
                driver = new EdgeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resource/driver/geckodriver_29.exe");
                driver = new FireFoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resource/driver/chromedriver_89.exe");
                driver = new ChromeDriver();
        }
        return driver;
    }
}