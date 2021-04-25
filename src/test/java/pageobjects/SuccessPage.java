package pageobjects;

import control.Message;
import control.TableColumn;
import drivermanager.Driver;

public class SuccessPage extends GeneralPage {

    private final Message registeredPID = new Message("//*[@id='content']//strong[2]");
    private final TableColumn departStationTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Station')]//preceding-sibling::th)+1]");
    private final TableColumn arriveStationTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Arrive Station')]//preceding-sibling::th)+1]");
    private final TableColumn seatTypeTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Seat Type')]//preceding-sibling::th)+1]");
    private final TableColumn departDateTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Depart Date')]//preceding-sibling::th)+1]");
    private final TableColumn ticketAmountTC = new TableColumn("//table/tbody//td[count(//tr[@class='TableSmallHeader']//th[contains(text(),'Amount')]//preceding-sibling::th)+1]");

    public String getConfirmationInfo() {
        Driver.waitFor(registeredPID);

        String confirmInfo = registeredPID.getText()
                + departStationTC.getText()
                + arriveStationTC.getText()
                + seatTypeTC.getText()
                + departDateTC.getText()
                + ticketAmountTC.getText();
        return confirmInfo;
    }
}
