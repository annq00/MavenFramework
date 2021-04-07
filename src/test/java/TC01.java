
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import java.lang.reflect.Method;

public class TC01 extends BaseTest {


    @Test
    public void TC01(Method method){

        extentTest = extentreports.ExtentTestManager.startTest(method.getName(), "TC01");

        extentTest.log(LogStatus.INFO,"Step 1","Open the Home Page");
        HomePage homePage = new HomePage(driver).open();

        extentTest.log(LogStatus.INFO,"Step 2","Go to the Login Page");
        LoginPage loginPage = homePage.gotoLoginPage(driver);

        extentTest.log(LogStatus.INFO,"Step 3","Log in with a valid account");
        HomePage loggedInHomePage = loginPage.loginWithValidAccount();

        loggedInHomePage.checkWelcomeMsg();
    }

}
