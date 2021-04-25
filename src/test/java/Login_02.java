import extentreport.BaseTest;
import extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;

public class Login_02 extends BaseTest {
    @Test
    public void Open_RegisterPage_by_clicking_the_hyperlink_registration_page() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Click the Login tab");
        System.out.println("Step 2: Click the Login tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        Reporter.log("Step 3: Click the 'registration page' hyperlink");
        System.out.println("Step 3: Click the 'registration page' hyperlink");
        RegisterPage registerPage = loginPage.clickRegistrationPageLink();

        Reporter.log("Step 4: Check if the Login page is displayed");
        System.out.println("Step 3: Check if the Login page is displayed");
        registerPage.doesRegisterPageOpen();
    }
}
