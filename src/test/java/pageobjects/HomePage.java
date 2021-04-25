package pageobjects;

import com.railway.constant.Constant;
import com.railway.drivermanager.Driver;
import org.testng.Assert;

public class HomePage extends GeneralPage {

    public HomePage open() {
        Driver.getWebDriver().navigate().to("http://railway.somee.com/Page/HomePage.cshtml");
        return this;
    }

    public void checkWelcomeMsg() {
        HomePage homePage = new HomePage();
        String actualMsg = homePage.getWelcomeMsg();
        String expectedMsg = "Welcome " + Constant.username;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected!");
    }
}
