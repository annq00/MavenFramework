package pageobjects;

import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Helper;

public class GeneralPage {

    private final By tabLogin = By.xpath("//*[@id='menu']//span[contains(text(),'Login')]");
    private final By tabLogout = By.xpath("//*[@id='menu']//span[contains(text(),'Log out')]");
    private final By lblWelcomeMsg = By.xpath("//*[@id='banner']/div/strong");
    private final By tabRegister = By.xpath("//*[@id='menu']//span[contains(text(),'Register')]");
    private final By tabBookTicket = By.xpath("//*[@id='menu']//span[contains(text(),'Book ticket')]");
    private final By tabMyTicket = By.xpath("//*[@id='menu']//span[contains(text(),'My ticket')]");
    private final By txtPageHeader = By.xpath("//*[@id='content']/h1");

    protected WebElement getTabLogin() {
        return DriverManager.driver.get().findElement(tabLogin);
    }
    protected WebElement getTabLogout() {
        return DriverManager.driver.get().findElement(tabLogout);
    }
    protected WebElement getLblWelcomeMsg() {
        return DriverManager.driver.get().findElement(lblWelcomeMsg);
    }
    protected WebElement getTabRegister() {
        return DriverManager.driver.get().findElement(tabRegister);
    }
    protected WebElement getTabBookTicket() {
        return DriverManager.driver.get().findElement(tabBookTicket);
    }
    protected WebElement getTabMyTicket() {
        return DriverManager.driver.get().findElement(tabMyTicket);
    }
    protected WebElement getTxtPageHeader(){
        return DriverManager.driver.get().findElement(txtPageHeader);
    }



    public String getWelcomeMsg() {
        return this.getLblWelcomeMsg().getText();
    }

    public String getPageHeader() {
        return this.getTxtPageHeader().getText();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }

    public boolean isTabLogoutExist() {
        return DriverManager.driver.get().findElements(tabLogout).size() > 0;
    }

    public RegisterPage gotoRegisterPage() {
        this.getTabRegister().click();
        return new RegisterPage();
    }

    public BookTicketPage gotoBookTicketPage(){
        this.getTabBookTicket().click();
        return new BookTicketPage();
    }

    public MyTicketPage gotoMyTicketPage(){
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }

    public LoginPage loginWithNewCreateAccount(){
        String newEmail = Helper.generateRandomString(6)+"@gmail.com";
        String newPassword = Helper.generateRandomString(8);
        String newPid = Helper.generateRandomString(10);
        HomePage homePage = new HomePage();
        RegisterPage registerPage = homePage.gotoRegisterPage();
        registerPage.getTbxEmail().sendKeys(newEmail);
        registerPage.getTbxPassword().sendKeys(newPassword);
        registerPage.getTbxConfirmPassword().sendKeys(newPassword);
        registerPage.getTbxPid().sendKeys(newPid);
        Helper.scrollToElement(registerPage.getBtnRegister());
        registerPage.getBtnRegister().click();
        LoginPage loginPage = registerPage.gotoLoginPage();
        loginPage.getTbxUserName().sendKeys(newEmail);
        loginPage.getTbxPassword().sendKeys(newPassword);
        loginPage.getBtnLogin().click();
        return new LoginPage();
    }
}