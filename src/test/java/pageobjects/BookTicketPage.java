package pageobjects;

import constant.Constant;
import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Helper;

public class BookTicketPage extends GeneralPage {

    private final By cbbDepartDate = By.xpath("//*[@id='content']//select[@name='Date']") ;
    private final By cbbDepartFrom = By.xpath("//*[@id='content']//select[@name='DepartStation']");
    private final By cbbArriveAt = By.xpath("//*[@id='content']//select[@name='ArriveStation']");
    private final By cbbSeatType = By.xpath("//*[@id='content']//select[@name='SeatType']");
    private final By cbbTicketAmount = By.xpath("//*[@id='content']//select[@name='TicketAmount']");
    private final By btnBookTicket = By.xpath("//input[@type='submit'][@value='Book ticket']");

    protected WebElement getCbbDepartDate(){
        return DriverManager.driver.get().findElement(cbbDepartDate);
    }
    protected WebElement getCbbDepartFrom(){
        return DriverManager.driver.get().findElement(cbbDepartFrom);
    }
    protected WebElement getCbbArriveAt(){
        return DriverManager.driver.get().findElement(cbbArriveAt);
    }
    protected WebElement getCbbSeatType(){
        return DriverManager.driver.get().findElement(cbbSeatType);
    }
    protected WebElement getCbbTicketAmount(){
        return DriverManager.driver.get().findElement(cbbTicketAmount);
    }
    protected WebElement getBtnBookTicket(){
        return DriverManager.driver.get().findElement(btnBookTicket);
    }

    //example of using wrapper getWebElement()
//    protected WebElement cbbDepartDate1 = Helper.getWebElement("a");


    public SuccessPage clickBookTicketBtn(){
        Helper.scrollToElement(getBtnBookTicket());
        this.getBtnBookTicket().click();
        return new SuccessPage();
    }

    public void enterBookingInfo(){
        Helper.select(getCbbDepartDate(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbDepartDate())));
        Helper.select(getCbbDepartFrom(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbDepartFrom())));
        Helper.select(getCbbArriveAt(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbArriveAt())));
        Helper.select(getCbbSeatType(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbSeatType())));
        Helper.select(getCbbTicketAmount(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbTicketAmount())));
    }

    public void enterBookingInfoWith1Ticket(){
        Helper.select(getCbbDepartDate(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbDepartDate())));
        Helper.select(getCbbDepartFrom(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbDepartFrom())));
        Helper.select(getCbbArriveAt(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbArriveAt())));
        Helper.select(getCbbSeatType(),Helper.getRandomNumber(0,Helper.getCbbSize(getCbbSeatType())));
        Helper.select(getCbbTicketAmount(),0);
    }

    public String getBookingInfo(){

        String info = Constant.PID
                +Helper.selectCurrentOption(getCbbDepartFrom())
                +Helper.selectCurrentOption(getCbbArriveAt())
                +Helper.selectCurrentOption(getCbbSeatType())
                +Helper.selectCurrentOption(getCbbDepartDate())
                +Helper.selectCurrentOption(getCbbTicketAmount());
        return info;
    }


}
