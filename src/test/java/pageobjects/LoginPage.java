package pageobjects;

import control.Button;
import control.Link;
import control.Message;
import control.TextBox;
import constant.Constant;
import org.testng.Assert;

public class LoginPage extends GeneralPage {

    private final TextBox userNameTbx = new TextBox("id=username");
    private final TextBox passWordTbx = new TextBox("id=password");
    private final Button loginBtn = new Button("//*[@id='content']/form/fieldset/p/input");
    private final Message loginErrorMsg = new Message("//*[@id='content']//p[@class='message error LoginForm']");
    private final Link registerPageLink = new Link("//a[contains(text(),'registration page')]");

    public HomePage loginWithValidAccount(String username, String password) {
        userNameTbx.enter(username);
        passWordTbx.enter(password);
        loginBtn.click();
        return new HomePage();
    }

    public void isLoginPageOpen() {
        String pageHeader = this.getPageHeader();
        Assert.assertEquals(pageHeader, Constant.LoginPageHeader, "LoginPage does not display as expected");
    }

    public RegisterPage clickRegistrationPageLink() {
        registerPageLink.click();
        return new RegisterPage();
    }
}
