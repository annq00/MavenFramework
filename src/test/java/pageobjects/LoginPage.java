package pageobjects;
import constant.Constant;
import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends GeneralPage {


    private final By tbxUserName = By.id("username");
    private final By tbxPassword = By.id("password");
    private final By btnLogin = By.xpath("//*[@id='content']/form/fieldset/p/input");
    private final By txtLoginErrorMessage =  By.xpath("//*[@id='content']//p[@class='message error LoginForm']");
    private final By linkToRegisterPage = By.xpath("//a[contains(text(),'registration page')]");

    protected WebElement getTbxUserName(){
        return DriverManager.driver.get().findElement(tbxUserName);

    }
    protected WebElement getTbxPassword(){
        return DriverManager.driver.get().findElement(tbxPassword);
    }
    protected WebElement getBtnLogin (){
        return DriverManager.driver.get().findElement(btnLogin);
    }
    protected WebElement getTxtLoginErrorMessage (){
        return DriverManager.driver.get().findElement(txtLoginErrorMessage);
    }
    protected WebElement getLinkToRegisterPage(){
        return DriverManager.driver.get().findElement(linkToRegisterPage);
    }


    public HomePage loginWithValidAccount(){

        this.getTbxUserName().sendKeys(Constant.username);
        this.getTbxPassword().sendKeys(Constant.password);
        this.getBtnLogin().click();

        return new HomePage();
    }

    public HomePage loginWithInvalidPassword(){

        this.getTbxUserName().sendKeys(Constant.username);
        this.getTbxPassword().sendKeys("12345678");
        this.getBtnLogin().click();
        return new HomePage();
    }

    public void checkLoginErrorMessage(){
        LoginPage loginPage = new LoginPage();
        String actualMessage = loginPage.getTxtLoginErrorMessage().getText();
        String expectedMessage = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualMessage,expectedMessage,"Error message is not displayed as expected!");
    }

    public void isLoginPageOpen(){
        String pageHeader = this.getPageHeader();
        Assert.assertEquals(pageHeader, Constant.LoginPageHeader,"LoginPage does not display as expected");
    }

    public RegisterPage clickRegistrationPageLink(){
        this.getLinkToRegisterPage().click();
        return new RegisterPage();
    }
}
