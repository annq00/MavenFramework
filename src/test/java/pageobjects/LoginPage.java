package pageobjects;
import constant.Constant;
import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends GeneralPage {


    private final By txtUserName = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.xpath("//*[@id='content']/form/fieldset/p/input");
    private final By txtLoginErrorMessage =  By.xpath("//*[@id='content']//p[@class='message error LoginForm']");

    protected WebElement getTxtUserName(){
        return DriverManager.driver.get().findElement(txtUserName);

    }
    protected WebElement getTxtPassword (){
        return DriverManager.driver.get().findElement(txtPassword);
    }
    protected WebElement getBtnLogin (){
        return DriverManager.driver.get().findElement(btnLogin);
    }
    protected WebElement getTxtLoginErrorMessage (){
        return DriverManager.driver.get().findElement(txtLoginErrorMessage);
    }


    public HomePage loginWithValidAccount(){

        this.getTxtUserName().sendKeys(Constant.username);
        this.getTxtPassword().sendKeys(Constant.password);
        this.getBtnLogin().click();

        return new HomePage();
    }

    public HomePage loginWithInvalidPassword(){

        this.getTxtUserName().sendKeys(Constant.username);
        this.getTxtPassword().sendKeys("12345678");
        this.getBtnLogin().click();
        return new HomePage();
    }

    public void checkLoginErrorMessage(){
        LoginPage loginPage = new LoginPage();
        String actualMessage = loginPage.getTxtLoginErrorMessage().getText();
        String expectedMessage = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMessage,expectedMessage,"Error message is not displayed as expected!");
    }

}
