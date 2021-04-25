package util;

import com.aventstack.extentreports.Status;
import drivermanagers.Driver;
import extentreport.ExtentManager;
import extentreport.ExtentTestManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class TestListener implements ITestListener {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void onTestStart(ITestResult result) {
        logger.debug(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass(result.getName());
        logger.debug("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String fileName = Helper.generateTimeStampString() + ".png";
        logger.info(Driver.captureScreenshot(fileName, ExtentManager.getReportPath()));
        try {
            ExtentTestManager.getTest().fail(result.getThrowable());
            ExtentTestManager.getTest().addScreenCaptureFromPath(fileName, "Failure Screenshot");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.debug("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        logger.debug("*** Test Suite " + context.getName() + " started ***");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.debug(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

}
