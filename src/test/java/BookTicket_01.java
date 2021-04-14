import extentreport.BaseTest;
import extentreport.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.BookTicketPage;
import pageobjects.HomePage;
import pageobjects.MyTicketPage;
import pageobjects.SuccessPage;

public class BookTicket_01 extends BaseTest {
    @Test
    public void BookTicketPage_display_correct_info(){
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Log in and click BookTicket tab");
        System.out.println("Step 2: Log in and click BookTicket tab");
        HomePage loggedInHomePage = homePage.gotoLoginPage().loginWithValidAccount();
        BookTicketPage bookTicketPage = loggedInHomePage.gotoBookTicketPage();

        Reporter.log("Step 3: Book tickets");
        System.out.println("Step 3: Book tickets");
        bookTicketPage.enterBookingInfo();
        String expected = bookTicketPage.getBookingInfo();
        SuccessPage successPage = bookTicketPage.bookTicket();

        Reporter.log("Step 4: Observe information in the 'Ticket booked successfully!' page");
        System.out.println("Step 4: Observe information in the 'Ticket booked successfully!' page");
        String observed = successPage.getConfirmationInfo();
        MyTicketPage myTicketPage = successPage.gotoMyTicketPage();
        myTicketPage.cancelAllTickets();
        Assert.assertEquals(expected,observed,"Mismatch information");

    }
}
