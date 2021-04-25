package drivermanager;


public class EdgeDriver extends Driver {

    @Override
    public void createWebDriver() {
        Driver.setWebDriver(new org.openqa.selenium.edge.EdgeDriver());
    }
}
