import com.railway.extentreport.BaseTest;
import com.railway.extentreport.Reporter;
import org.testng.annotations.Test;
import pageobjects.*;

public class MyTicket_01 extends BaseTest {
    @Test
    public void Total_tickets_in_MyTicket_table_matches_with_Note_message() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Create an account and login");
        System.out.println("Step 2: Create an account and login");
        LoginPage loginPage = homePage.loginWithNewCreateAccount(true);

        Reporter.log("Step 3: Book tickets");
        System.out.println("Step 3: Book tickets");
        BookTicketPage bookTicketPage = loginPage.gotoBookTicketPage();
        bookTicketPage.enterBookingInfo(0);
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();

        Reporter.log("Step 4: Open My Ticket page");
        System.out.println("Step 4: Open My Ticket page");
        MyTicketPage myTicketPage = successPage.gotoMyTicketPage();
        myTicketPage.compareTicketInMsgAndNote();
    }
}
