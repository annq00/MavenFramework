package pageobjects;
import constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends GeneralPage {
    WebDriver driver;

    public LoginPage (WebDriver driver){
        this.driver = driver;
    }

    private final By txtUserName = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.xpath("//*[@id='content']/form/fieldset/p/input");
    private final By txtLoginErrorMessage =  By.xpath("//*[@id='content']//p[@class='message error LoginForm']");

    protected WebElement getTxtUserName(WebDriver driver){
        return driver.findElement(txtUserName);

    }
    protected WebElement getTxtPassword (WebDriver driver){
        return driver.findElement(txtPassword);
    }
    protected WebElement getBtnLogin (WebDriver driver){
        return driver.findElement(btnLogin);
    }
    protected WebElement getTxtLoginErrorMessage (WebDriver driver){
        return driver.findElement(txtLoginErrorMessage);
    }


    public HomePage loginWithValidAccount(){

        this.getTxtUserName(driver).sendKeys(Constant.username);
        this.getTxtPassword(driver).sendKeys(Constant.password);
        this.getBtnLogin(driver).click();

        return new HomePage(driver);
    }

    public HomePage loginWithInvalidPassword(){

        this.getTxtUserName(driver).sendKeys(Constant.username);
        this.getTxtPassword(driver).sendKeys("12345678");
        this.getBtnLogin(driver).click();

        return new HomePage(driver);
    }

    public void checkLoginErrorMessage(){
        LoginPage loginPage = new LoginPage(driver);
        String actualMessage = loginPage.getTxtLoginErrorMessage(driver).getText();
        String expectedMessage = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMessage,expectedMessage,"Error message is not displayed as expected!");
    }

}
