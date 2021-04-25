import extentreport.BaseTest;
import extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;

public class Login_01 extends BaseTest {
    @Test
    public void User_can_open_the_Login_page() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Click the Login tab");
        System.out.println("Step 2: Click the Login tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        Reporter.log("Step 3: Check if the LoginPage is opened");
        System.out.println("Step 3: Check if the LoginPage is opened");
        loginPage.isLoginPageOpen();
    }
}
