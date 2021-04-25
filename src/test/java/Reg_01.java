import com.railway.extentreport.BaseTest;
import com.railway.extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.RegisterPage;

public class Reg_01 extends BaseTest {

    @Test
    public void User_can_open_the_Register_page() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Click the Register tab");
        System.out.println("Step 2: Click the Register tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();

        Reporter.log("Step 3: Check if the RegisterPage is opened");
        System.out.println("Step 3: Check if the RegisterPage is opened");
        registerPage.doesRegisterPageOpen();
    }
}
