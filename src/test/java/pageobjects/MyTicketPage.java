package pageobjects;

import com.railway.control.Button;
import com.railway.control.Message;
import com.railway.control.TableColumn;
import com.railway.drivermanager.Driver;
import org.testng.Assert;

public class MyTicketPage extends GeneralPage {

    private final TableColumn noTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'No.')]//preceding-sibling::th)+1]");
    private final TableColumn departStationTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]");
    private final TableColumn arriveStationTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]");
    private final TableColumn seatTypeTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]");
    private final TableColumn departDateTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]");
    private final TableColumn ticketAmountTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]");
    private final Button cancelTicketBtn = new Button("//table/tbody/tr[2]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Operation')]//preceding-sibling::th)+1]/input");
    private final Message noteMsg = new Message("//*[@id='content']//li[contains(text(),'book')]");

    public void cancelAllTickets() {
        MyTicketPage page = new MyTicketPage();
        SuccessPage successPage = new SuccessPage();

        int numberOfRemainTickets = noTC.getTableColumnSize();

        if (numberOfRemainTickets == 0) {
            return;
        }
        while (6 - numberOfRemainTickets > 0) {
            BookTicketPage bookTicketPage = page.gotoBookTicketPage();
            bookTicketPage.enterBookingInfo(1);
            bookTicketPage.clickBookTicketBtn();
            numberOfRemainTickets++;
        }
        successPage.gotoMyTicketPage();
        int numberOfCurrentTickets = noTC.getTableColumnSize() - 1;
        while (numberOfCurrentTickets > 0) {
            cancelTicketBtn.click();
            Driver.getWebDriver().switchTo().alert().accept();
            numberOfCurrentTickets--;
        }
    }

    public void compareTicketInMsgAndNote() {
        String observed = noteMsg.getText();
        String bookedAmount = ticketAmountTC.getText();
        int remain = 10 - Integer.parseInt(bookedAmount);
        String expected;
        if (remain == 0) {
            expected = "You currently book 10 tickets, you can book no more.";
        } else if (remain == 9) {
            expected = "You currently book 1 ticket, you can book 9 more.";
        } else {
            String remainAmount = String.valueOf(remain);
            expected = String.format("You currently book %s tickets, you can book %s more.", bookedAmount, remainAmount);
        }
        Assert.assertEquals(observed, expected, "Message mismatch");

    }

    public void checkOldTickets() {
        MyTicketPage myTicketPage = new MyTicketPage();
        //collect booked ticket's info
        String expected = myTicketPage.departStationTC.getText()
                + myTicketPage.arriveStationTC.getText()
                + myTicketPage.seatTypeTC.getText()
                + myTicketPage.departDateTC.getText()
                + myTicketPage.ticketAmountTC.getText();

        //book a new ticket
        BookTicketPage bookTicketPage = myTicketPage.gotoBookTicketPage();
        bookTicketPage.enterBookingInfo(1);
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();

        //check if the old ticket is still displaying after booking a new ticket
        successPage.gotoMyTicketPage();
        String observed = Driver.findElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]").getText()
                + Driver.findElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]").getText()
                + Driver.findElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]").getText()
                + Driver.findElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]").getText()
                + Driver.findElement("//table/tbody//tr[3]/td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]").getText();
        Assert.assertEquals(expected, observed, "Old ticket does not display as expected");
    }
}
