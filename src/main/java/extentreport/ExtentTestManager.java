package extentreport;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();


    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int)(Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int)(Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int)(Thread.currentThread().getId()), test);
        return test;
    }

    public static void addIntoExtentReport(ITestResult result, LogStatus status) {

        //Generate Stack Trace string & Exception message
        String stackTrace = "";
        String exceptionMessage = "";
        if (status != LogStatus.PASS && result.getThrowable() != null) {
            for (StackTraceElement e : result.getThrowable().getStackTrace()) {
                if (e.toString().contains("TC")) {
                    stackTrace = stackTrace + e.toString() + "</br>";
                }
            }
            stackTrace = "</br><b style='color: #FF0101;'>STACK TRACE: </b>" + stackTrace;
            exceptionMessage = result.getThrowable().getMessage();
        }

        //Generate color status
        String color = "#FF0101";
        if (status == LogStatus.PASS) {
            color = "#44aa44";
        }

        // Extend Report - Start Test
        String[] classPath = result.getInstanceName().split("\\.");
        startTest(classPath[classPath.length - 1],"The test is started");
        if (status != null) {
            getTest().log(status, "<b style='color: " + color + ";'>" + status.toString().toUpperCase() + "</b> " + exceptionMessage + stackTrace);
        }

    }
}