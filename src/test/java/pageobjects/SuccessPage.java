package pageobjects;

import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Helper;

public class SuccessPage extends GeneralPage {
    private final By registeredPID = By.xpath("//*[@id='content']//strong[2]");
    private final By bookedDepartStation = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]");
    private final By bookedArriveStation = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]");
    private final By bookedSeatType = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]");
    private final By bookedDepartDate = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]");
    private final By bookedTicketAmount = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]");

    protected WebElement getRegisteredPID(){
        return DriverManager.driver.get().findElement(registeredPID);
    }
    protected WebElement getBookedDepartStation(){
        return DriverManager.driver.get().findElement(bookedDepartStation);
    }
    protected WebElement getBookedArriveStation(){
        return DriverManager.driver.get().findElement(bookedArriveStation);
    }
    protected WebElement getBookedSeatType(){
        return DriverManager.driver.get().findElement(bookedSeatType);
    }
    protected WebElement getBookedDepartDate(){
        return DriverManager.driver.get().findElement(bookedDepartDate);
    }
    protected WebElement getBookedTicketAmount(){
        return DriverManager.driver.get().findElement(bookedTicketAmount);
    }

    public String getConfirmationInfo(){
        Helper.waitFor(getRegisteredPID());

        String confirmInfo = getRegisteredPID().getText()
                +getBookedDepartStation().getText()
                +getBookedArriveStation().getText()
                +getBookedSeatType().getText()
                +getBookedDepartDate().getText()
                +getBookedTicketAmount().getText();
        return confirmInfo;
    }

}
