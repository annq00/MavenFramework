import extentreport.BaseTest;
import extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.*;

public class MyTicket_02 extends BaseTest {
    @Test
    public void Booking_new_tickets_does_not_make_old_ticket_disappear(){
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Log in and cancel existed tickets");
        System.out.println("Step 2: Log in and cancel existed tickets");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.loginWithValidAccount();
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        myTicketPage.cancelAllTickets();

        Reporter.log("Step 3: Book 1 ticket");
        System.out.println("Step 3: Book 1 ticket");
        BookTicketPage bookTicketPage = myTicketPage.gotoBookTicketPage();
        bookTicketPage.enterBookingInfoWith1Ticket();
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();

        Reporter.log("Step 4: Book another ticket and check if the old one still displays");
        System.out.println("Step 4: Book another ticket and check if the old one still displays");
        successPage.gotoMyTicketPage();
        myTicketPage.checkOldTickets();
    }
}
