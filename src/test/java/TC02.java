import com.relevantcodes.extentreports.LogStatus;
import extentreport.BaseTest;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

import java.lang.reflect.Method;

public class TC02 extends BaseTest {

    @Test
    public void TC02 (Method method){

        extentTest = extentreport.ExtentTestManager.startTest(method.getName(), "TC02");

        System.out.println("TC02: User can't login with invalid password");

        extentTest.log(LogStatus.INFO,"Step 1","Open the Home Page");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homepage = new HomePage(driver).open();

        extentTest.log(LogStatus.INFO,"Step 2","Go to Login Page");
        System.out.println("Step 2: Go to Login Page");
        LoginPage loginpage = homepage.gotoLoginPage(driver);

        extentTest.log(LogStatus.INFO,"Step 3","Login with invalid password");
        System.out.println("Step 3: Login with invalid password");
        loginpage.loginWithInvalidPassword();

        extentTest.log(LogStatus.INFO,"Step 4","Verify that login error message is displayed as expected");
        System.out.println("Step 4: Verify that login error message is displayed as expected");
        loginpage.checkLoginErrorMessage();

    }
}
