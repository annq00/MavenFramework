package pageobjects;

import constant.Constant;
import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Helper;



public class RegisterPage extends GeneralPage {
    private final By tbxEmail = By.id("email");
    private final By tbxPassword = By.id("password");
    private final By tbxConfirmPassword = By.id("confirmPassword");
    private final By tbxPid = By.id("pid");
    private final By btnRegister = By.xpath("//*[@id='RegisterForm']/fieldset/p/input");
    private final By txtGeneralErrorMsg = By.xpath("//p[@class='message error']");

    protected WebElement getTbxEmail(){
        return DriverManager.driver.get().findElement(tbxEmail);
    }
    protected WebElement getTxtPassword(){
        return DriverManager.driver.get().findElement(tbxPassword);
    }
    protected WebElement getTbxConfirmPassword(){
        return DriverManager.driver.get().findElement(tbxConfirmPassword);
    }
    protected WebElement getTbxPid(){
        return DriverManager.driver.get().findElement(tbxPid);
    }
    protected WebElement getBtnRegister(){
        return DriverManager.driver.get().findElement(btnRegister);
    }
    protected WebElement getTxtGeneralErrorMsg(){
        return DriverManager.driver.get().findElement(txtGeneralErrorMsg);
    }

    public void isRegisterPageOpen(){
        String pageHeader = this.getPageHeader();
        Assert.assertEquals(pageHeader, Constant.RegisterPageHeader,"RegisterPage does not display as expected");
    }

    public void CreateAccountWithUsedEmail(){
        this.getTbxEmail().sendKeys(Constant.username);
        String password = Helper.generateRandomString(8);
        this.getTxtPassword().sendKeys(password);
        this.getTbxConfirmPassword().sendKeys(password);
        this.getTbxPid().sendKeys(Helper.generateRandomString(8));
        Helper.scrollToElement(getBtnRegister());
        this.getBtnRegister().click();
    }

    public void checkRegisterErrorMessage(String actualErrorMsg){
        String observedErrorMsg = this.getTxtGeneralErrorMsg().getText();
        Assert.assertEquals(observedErrorMsg,actualErrorMsg,"Error message does not display as expected");
    }
}
