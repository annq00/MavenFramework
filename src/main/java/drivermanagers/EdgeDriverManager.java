package drivermanagers;


import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {

    @Override
    public void createWebDriver(){

        DriverManager.driver.set(new EdgeDriver());
    }
}
