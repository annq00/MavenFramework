package pageobjects;
import constant.Constant;
import drivermanagers.DriverManager;
import org.testng.Assert;
import utils.Helper;

public class HomePage extends GeneralPage{



    public HomePage open(){
        DriverManager.driver.get().navigate().to("http://railway.somee.com/Page/HomePage.cshtml");
        return this;
    }

    public void checkWelcomeMsg(){
        HomePage homePage = new HomePage();
        String actualMsg = homePage.getWelcomeMsg();
        String expectedMsg = "Welcome "+ Constant.username;
        Assert.assertEquals(actualMsg,expectedMsg,"Welcome message is not displayed as expected!");

    }

    public void checkLogoutTabExist(){
        HomePage homePage = new HomePage();
        Boolean observed  = homePage.isTabLogoutExist();
        Assert.assertEquals(observed, java.util.Optional.of(true),"User is not logged in");
    }

    public void loginWithNewCreatedAccount(){
        String newEmail = Helper.generateRandomString(6)+"@gmail.com";
        String newPassword = Helper.generateRandomString(8);
        String newPid = Helper.generateRandomString(10);
        HomePage homePage = new HomePage();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.getTbxEmail().sendKeys(newEmail);
        registerPage.getTbxPassword().sendKeys(newPassword);
        registerPage.getTbxConfirmPassword().sendKeys(newPassword);
        registerPage.getTbxPid().sendKeys(newPid);
        Helper.scrollToElement(registerPage.getBtnRegister());
        registerPage.getBtnRegister().click();
        LoginPage loginPage = registerPage.gotoLoginPage();
        loginPage.getTbxUserName().sendKeys(newEmail);
        loginPage.getTbxPassword().sendKeys(newPassword);
        loginPage.getBtnLogin().click();
    }
}
