package pageobjects;

import Control.Label;
import Control.Message;
import constant.Constant;
import extentreport.Reporter;
import util.Helper;

public class GeneralPage {

    protected final Label tabLogin = new Label("//*[@id='menu']//span[contains(text(),'Login')]");
    protected final Label tabLogout = new Label("//*[@id='menu']//span[contains(text(),'Log out')]");
    protected final Message welcomeMsg = new Message("//*[@id='banner']/div/strong");
    protected final Label tabRegister = new Label("//*[@id='menu']//span[contains(text(),'Register')]");
    protected final Label tabBookTicket = new Label("//*[@id='menu']//span[contains(text(),'Book ticket')]");
    protected final Label tabMyTicket = new Label("//*[@id='menu']//span[contains(text(),'My ticket')]");
    protected final Message pageHeader = new Message("//*[@id='content']/h1");

    public String getWelcomeMsg() {
        return welcomeMsg.getText();
    }

    public String getPageHeader() {
        return pageHeader.getText();
    }

    public LoginPage gotoLoginPage() {
        tabLogin.click();
        return new LoginPage();
    }

    public RegisterPage gotoRegisterPage() {
        tabRegister.click();
        return new RegisterPage();
    }

    public BookTicketPage gotoBookTicketPage() {
        tabBookTicket.click();
        return new BookTicketPage();
    }

    public MyTicketPage gotoMyTicketPage() {
        tabMyTicket.click();
        return new MyTicketPage();
    }

    public LoginPage loginWithNewCreateAccount(Boolean randomPID) {
        String newEmail = Helper.generateRandomString(6) + "@gmail.com";
        String newPassword = Helper.generateRandomString(8);
        String newPid = Constant.PID;
        if (randomPID) {
            newPid = Helper.generateRandomString(10);
        }
        Reporter.log(String.format("Account's username is %s", newEmail));
        Reporter.log(String.format("Account's password is %s", newPassword));
        Reporter.log(String.format("Account's PID is %s", newPid));

        HomePage homePage = new HomePage();
        //register an account with random info
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.createAccount(newEmail, newPassword, newPid);
        //log in with the new account
        LoginPage loginPage = registerPage.gotoLoginPage();
        loginPage.loginWithValidAccount(newEmail, newPassword);
        return new LoginPage();
    }
}