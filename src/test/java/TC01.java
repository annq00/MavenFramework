import extentreport.BaseTest;
import extentreport.ExtentTestManager;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;


public class TC01 extends BaseTest {

    @Test
    public void TC01(){
        System.out.println("TC01: User can login with valid credential");

        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage(driver).open();

        System.out.println("Step 2: Go to the Login Page");
        LoginPage loginPage = homePage.gotoLoginPage(driver);

        System.out.println("Step 3: Log in with a valid account");
        HomePage loggedInHomePage = loginPage.loginWithValidAccount();

        loggedInHomePage.checkLogoutTabExist();
    }

}
