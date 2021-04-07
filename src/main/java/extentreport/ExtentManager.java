package extentreport;

import com.relevantcodes.extentreports.ExtentReports;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent;
    private static String reportFileName = "Test-Report-" + generateTimeStampString()+".html";

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                extent = new ExtentReports( workingDir + "/reports/" + reportFileName, false);
            }
            else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                extent = new ExtentReports(workingDir + "/reports/" +reportFileName, false);
            }
        }
        return extent;
    }

    public static String generateTimeStampString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
