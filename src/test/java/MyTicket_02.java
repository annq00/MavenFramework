import com.railway.extentreport.BaseTest;
import com.railway.extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.*;

public class MyTicket_02 extends BaseTest {
    @Test
    public void Booking_new_tickets_does_not_make_old_ticket_disappear() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Create an account and login");
        System.out.println("Step 2: Create an account and login");
        LoginPage loginPage = homePage.loginWithNewCreateAccount(true);
        MyTicketPage myTicketPage = loginPage.gotoMyTicketPage();

        Reporter.log("Step 3: Book 1 ticket");
        System.out.println("Step 3: Book 1 ticket");
        BookTicketPage bookTicketPage = myTicketPage.gotoBookTicketPage();
        bookTicketPage.enterBookingInfo(1);
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();

        Reporter.log("Step 4: Book another ticket and check if the old one still displays");
        System.out.println("Step 4: Book another ticket and check if the old one still displays");
        successPage.gotoMyTicketPage();
        myTicketPage.checkOldTickets();
    }
}
