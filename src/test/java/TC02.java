
import extentreport.BaseTest;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;


public class TC02 extends BaseTest {

    @Test
    public void TC02 (){
        System.out.println("TC02: User can't login with invalid password");

        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homepage = new HomePage().open();

        System.out.println("Step 2: Go to Login Page");
        LoginPage loginpage = homepage.gotoLoginPage();

        System.out.println("Step 3: Login with invalid password");
        loginpage.loginWithInvalidPassword();

        System.out.println("Step 4: Verify that login error message is displayed as expected");
        loginpage.checkLoginErrorMessage();
    }
}
