package pageobjects;

import control.Button;
import control.Message;
import control.TextBox;
import constant.Constant;
import drivermanager.Driver;
import org.testng.Assert;

public class RegisterPage extends GeneralPage {
    private final TextBox emailTbx = new TextBox("id=email");
    private final TextBox passwordTbx = new TextBox("id=password");
    private final TextBox confirmPasswordTbx = new TextBox("id=confirmPassword");
    private final TextBox pidTbx = new TextBox("id=pid");
    private final Button registerBtn = new Button("//*[@id='RegisterForm']/fieldset/p/input");
    private final Message generalErrorMsg = new Message("//p[@class='message error']");

    public void doesRegisterPageOpen() {
        String pageHeader = this.getPageHeader();
        Assert.assertEquals(pageHeader, Constant.RegisterPageHeader, "RegisterPage does not display as expected");
    }

    public void createAccount(String email, String password, String pid) {
        emailTbx.enter(email);
        passwordTbx.enter(password);
        confirmPasswordTbx.enter(password);
        pidTbx.enter(pid);
        Driver.scrollToElement(registerBtn);
        registerBtn.click();
    }

    public void checkRegisterErrorMessage(String actualErrorMsg) {
        String observedErrorMsg = generalErrorMsg.getText();
        Assert.assertEquals(observedErrorMsg, actualErrorMsg, "Error message does not display as expected");
    }
}
