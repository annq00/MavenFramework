package pageobjects;
import constant.Constant;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends GeneralPage{
    WebDriver driver;

    public HomePage (WebDriver driver){
        this.driver = driver;
    }

    public HomePage open(){
        driver.navigate().to("http://railway.somee.com/Page/HomePage.cshtml");
        return this;
    }

    public void checkWelcomeMsg(){
        HomePage homePage = new HomePage(driver);
        String actualMsg = homePage.getWelcomeMsg(driver);
        String expectedMsg = "Welcome "+ Constant.username;
        Assert.assertEquals(actualMsg,expectedMsg,"Welcome message is not displayed as expected!");

    }
}
