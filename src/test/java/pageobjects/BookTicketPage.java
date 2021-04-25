package pageobjects;

import control.Button;
import control.DropDown;
import constant.Constant;
import drivermanager.Driver;
import util.Helper;

public class BookTicketPage extends GeneralPage {

    private final DropDown departDateDD = new DropDown("//*[@id='content']//select[@name='Date']");
    private final DropDown departFromDD = new DropDown("//*[@id='content']//select[@name='DepartStation']");
    private final DropDown arriveAtDD = new DropDown("//*[@id='content']//select[@name='ArriveStation']");
    private final DropDown seatTypeDD = new DropDown("//*[@id='content']//select[@name='SeatType']");
    private final DropDown ticketAmountDD = new DropDown("//*[@id='content']//select[@name='TicketAmount']");
    private final Button bookTicketBtn = new Button("//input[@type='submit'][@value='Book ticket']");

    public SuccessPage clickBookTicketBtn() {
        Driver.scrollToElement(bookTicketBtn);
        bookTicketBtn.click();
        return new SuccessPage();
    }

    public void enterBookingInfo(int tickets) {
        departDateDD.select(Helper.getRandomNumber(0, departDateDD.getSize()));
        departFromDD.select(Helper.getRandomNumber(0, departFromDD.getSize()));
        arriveAtDD.select(Helper.getRandomNumber(0, arriveAtDD.getSize()));
        seatTypeDD.select(Helper.getRandomNumber(0, seatTypeDD.getSize()));
        if (0 < tickets && tickets < 11) {
            ticketAmountDD.select(Helper.getRandomNumber(0, tickets - 1));
        } else {
            ticketAmountDD.select(Helper.getRandomNumber(0, ticketAmountDD.getSize()));
        }
    }

    public String getBookingInfo() {
        String info = Constant.PID
                + departFromDD.getSelectedOption()
                + arriveAtDD.getSelectedOption()
                + seatTypeDD.getSelectedOption()
                + departDateDD.getSelectedOption()
                + ticketAmountDD.getSelectedOption();
        return info;
    }

}
