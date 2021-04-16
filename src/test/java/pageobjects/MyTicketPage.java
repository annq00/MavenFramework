package pageobjects;

import drivermanagers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Helper;

public class MyTicketPage extends GeneralPage{
    private final By txtNo = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'No.')]//preceding-sibling::th)+1]");
    private final By txtDepartStation = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]");
    private final By txtArriveStation = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]");
    private final By txtSeatType = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]");
    private final By txtDepartDate = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]");
    private final By txtTicketAmount = By.xpath("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]");
    private final By btnCancelTicket = By.xpath("//table/tbody/tr[2]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Operation')]//preceding-sibling::th)+1]/input");
    private final By txtNoteMsg = By.xpath("//*[@id='content']//li[contains(text(),'book')]");

    protected WebElement getTxtNo(){
        return DriverManager.driver.get().findElement(txtNo);
    }
    protected WebElement getTxtDepartStation(){
        return DriverManager.driver.get().findElement(txtDepartStation);
    }
    protected WebElement getTxtArriveStation(){
        return DriverManager.driver.get().findElement(txtArriveStation);
    }
    protected WebElement getTxtSeatType(){
        return DriverManager.driver.get().findElement(txtSeatType);
    }
    protected WebElement getTxtDepartDate(){
        return DriverManager.driver.get().findElement(txtDepartDate);
    }
    protected WebElement getTxtTicketAmount(){
        return DriverManager.driver.get().findElement(txtTicketAmount);
    }
    protected WebElement getBtnCancelTicket(){
        return DriverManager.driver.get().findElement(btnCancelTicket);
    }
    protected WebElement getTxtNoteMsg(){
        return DriverManager.driver.get().findElement(txtNoteMsg);
    }

    public void cancelAllTickets(){
        MyTicketPage page = new MyTicketPage();
        SuccessPage successPage = new SuccessPage();

        int numberOfRemainTickets = DriverManager.driver.get().findElements(txtNo).size();

        if (numberOfRemainTickets==0){
            return;
        }
        while (6 - numberOfRemainTickets > 0)
        {
            BookTicketPage bookTicketPage = page.gotoBookTicketPage();
            bookTicketPage.enterBookingInfoWith1Ticket();
            bookTicketPage.clickBookTicketBtn();
            numberOfRemainTickets++;
        }
        successPage.gotoMyTicketPage();
        int numberOfCurrentTickets = DriverManager.driver.get().findElements(txtNo).size()-1;
        while (numberOfCurrentTickets > 0){
            page.getBtnCancelTicket().click();
            DriverManager.driver.get().switchTo().alert().accept();
            numberOfCurrentTickets--;
        }
    }

    public void compareTicketInMsgAndNote(){
        String observed = this.getTxtNoteMsg().getText();
        String bookedAmount = this.getTxtTicketAmount().getText();
        int remain = 10 - Integer.parseInt(bookedAmount);
        String remainAmount = "";
        if(remain==0){
            remainAmount = "no";
        }
        else
        remainAmount = String.valueOf(remain);
        String expected = String.format("You currently book %s tickets, you can book %s more.",bookedAmount,remainAmount) ;
        Assert.assertEquals(observed,expected,"Message mismatch");
    }

    public void checkOldTickets(){
        MyTicketPage myTicketPage = new MyTicketPage();
        String expected = myTicketPage.getTxtDepartStation().getText()
                +myTicketPage.getTxtArriveStation().getText()
                +myTicketPage.getTxtSeatType().getText()
                +myTicketPage.getTxtDepartDate().getText()
                +myTicketPage.getTxtTicketAmount().getText();

        BookTicketPage bookTicketPage = myTicketPage.gotoBookTicketPage();
        bookTicketPage.enterBookingInfoWith1Ticket();
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();
        successPage.gotoMyTicketPage();
        String observed = Helper.getWebElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]").getText()
                + Helper.getWebElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]").getText()
                + Helper.getWebElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]").getText()
                + Helper.getWebElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]").getText()
                + Helper.getWebElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]").getText();

        Assert.assertEquals(expected,observed,"Old ticket does not display as expected");
    }
}
