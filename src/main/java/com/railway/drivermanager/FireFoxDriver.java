package com.railway.drivermanager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriver extends Driver{
    @Override
    protected void createWebDriver() {
        Driver.setWebDriver(new FirefoxDriver());
    }
}
