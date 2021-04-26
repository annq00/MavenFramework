package pageobjects;

import com.railway.control.Button;
import com.railway.control.Link;
import com.railway.control.Message;
import com.railway.control.TextBox;
import com.railway.constant.Constant;
import com.railway.drivermanager.Driver;
import org.testng.Assert;

public class LoginPage extends GeneralPage {

    private final TextBox userNameTbx = new TextBox("id=username");
    private final TextBox passWordTbx = new TextBox("id=password");
    private final Button loginBtn = new Button("//*[@id='content']/form/fieldset/p/input");
    private final Message loginErrorMsg = new Message("//*[@id='content']//p[@class='message error LoginForm']");
    private final Link registerPageLink = new Link("//a[contains(text(),'registration page')]");

    public HomePage loginWithValidAccount(String username, String password) {
        Driver.waitFor(registerPageLink);
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
