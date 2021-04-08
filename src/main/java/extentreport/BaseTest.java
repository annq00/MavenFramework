package extentreport;

import com.relevantcodes.extentreports.ExtentTest;
import drivermanagers.DriverManager;
import drivermanagers.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    public DriverManager driverManager;
    public WebDriver driver;
    public ExtentTest extentTest;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browser){
        System.out.print("Before Class");
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().window().maximize();
    }
    @AfterClass
    public void afterClass(){

        System.out.print("After Class");
        driverManager.quitWebDriver();

        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
}
