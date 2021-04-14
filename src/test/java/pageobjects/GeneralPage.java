package pageobjects;

import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralPage {

    private final By tabLogin = By.xpath("//*[@id='menu']//span[contains(text(),'Login')]");
    private final By tabLogout = By.xpath("//*[@id='menu']//span[contains(text(),'Log out')]");
    private final By lblWelcomeMsg = By.xpath("//*[@id='banner']/div/strong");
    private final By tabRegister = By.xpath("//*[@id='menu']//span[contains(text(),'Register')]");
    private final By tabBookTicket = By.xpath("//*[@id='menu']//span[contains(text(),'Book ticket')]");
    private final By txtPageHeader = By.xpath("//*[@id='content']/h1");

    protected WebElement getTabLogin() {
        return DriverManager.driver.get().findElement(tabLogin);
    }
    protected WebElement getTabLogout(WebDriver driver) {
        return driver.findElement(tabLogout);
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


}