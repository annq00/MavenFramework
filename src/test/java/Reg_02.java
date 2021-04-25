import com.railway.constant.Constant;
import com.railway.extentreport.BaseTest;
import com.railway.extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.RegisterPage;
import com.railway.util.Helper;

public class Reg_02 extends BaseTest {
    @Test
    public void User_cannot_create_account_using_registered_email() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Open the Register tab");
        System.out.println("Step 2: Open the Register tab");
        RegisterPage registerPage = homePage.gotoRegisterPage();

        Reporter.log("Step 3: Create an account with registered email");
        System.out.println("Step 3: Create an account with registered email");
        registerPage.createAccount(Constant.username, Helper.generateRandomString(8), Helper.generateRandomString(10));

        Reporter.log("Step 4: Check the error message");
        System.out.println("Step 4: Check the error message");
        registerPage.checkRegisterErrorMessage(Constant.RegisterUsedEmailMsg);
    }
}
