package pageobjects;
import constant.Constant;
import drivermanagers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
}
