package extentreport;

import constant.Constant;
import drivermanagers.Driver;
import drivermanagers.DriverFactory;
import org.testng.annotations.*;

public class BaseTest {
    public Driver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browser) {
        System.out.print("Before Class");
        driver = DriverFactory.createDriver(browser);
        driver.initWebDriver();
        Driver.maximizeWindow();
        Driver.navigateTo(Constant.baseURL);
    }

    @AfterClass
    public void afterClass() {
        System.out.print("After Class");
        Driver.getWebDriver().quit();
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }
}
