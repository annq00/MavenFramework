package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralPage {

    private final By tabLogin = By.xpath("//*[@id='menu']//span[contains(text(),'Login')]");
    private final By tabLogout = By.xpath("//*[@id='menu']//span[contains(text(),'Log out')]");
    private final By lblWelcomeMsg = By.xpath("//*[@id='banner']/div/strong");

    protected WebElement getTabLogin(WebDriver driver){
        return driver.findElement(tabLogin);
    }

    protected WebElement getTabLogout(WebDriver driver){
        return driver.findElement(tabLogout);
    }

    protected WebElement getLblWelcomeMsg(WebDriver driver){
        return driver.findElement(lblWelcomeMsg);
    }

    public String getWelcomeMsg(WebDriver driver){
        return this.getLblWelcomeMsg(driver).getText();
    }

    public LoginPage gotoLoginPage(WebDriver driver){
        this.getTabLogin(driver).click();
        return new LoginPage(driver);
    }

}