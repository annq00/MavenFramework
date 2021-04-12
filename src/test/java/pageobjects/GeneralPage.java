package pageobjects;
import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralPage {

    private final By tabLogin = By.xpath("//*[@id='menu']//span[contains(text(),'Login')]");
    private final By tabLogout = By.xpath("//*[@id='menu']//span[contains(text(),'Log out')]");
    private final By lblWelcomeMsg = By.xpath("//*[@id='banner']/div/strong");

    protected WebElement getTabLogin(){
        return DriverManager.driver.get().findElement(tabLogin);
    }

    protected WebElement getTabLogout(WebDriver driver){
        return driver.findElement(tabLogout);
    }

    protected WebElement getLblWelcomeMsg(){
        return DriverManager.driver.get().findElement(lblWelcomeMsg);
    }

    public String getWelcomeMsg(){
        return this.getLblWelcomeMsg().getText();
    }

    public LoginPage gotoLoginPage(){
        this.getTabLogin().click();
        return new LoginPage();
    }

    public boolean isTabLogoutExist (){
        return DriverManager.driver.get().findElements(tabLogout).size() > 0;
    }

}