import com.railway.extentreport.BaseTest;
import com.railway.extentreport.Reporter;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

public class BookTicket_01 extends BaseTest {
    @Test
    public void BookTicketPage_display_correct_info() {
        Reporter.log("Step 1: Navigate to Railway's HomePage");
        System.out.println("Step 1: Navigate to Railway's HomePage");
        HomePage homePage = new HomePage().open();

        Reporter.log("Step 2: Log in with a new account and click BookTicket tab");
        System.out.println("Step 2: Log in and click BookTicket tab");
        LoginPage loginPage = homePage.loginWithNewCreateAccount(false);
        BookTicketPage bookTicketPage = loginPage.gotoBookTicketPage();

        Reporter.log("Step 3: Book tickets");
        System.out.println("Step 3: Book tickets");
        bookTicketPage.enterBookingInfo(1);
        String expected = bookTicketPage.getBookingInfo();
        SuccessPage successPage = bookTicketPage.clickBookTicketBtn();

        Reporter.log("Step 4: Observe information in the 'Ticket booked successfully!' page");
        System.out.println("Step 4: Observe information in the 'Ticket booked successfully!' page");
        String observed = successPage.getConfirmationInfo();
        Assert.assertEquals(expected, observed, "Mismatch information");
    }
}
