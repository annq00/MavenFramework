package com.railway.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.railway.constant.Constant;
import org.apache.commons.codec.CharEncoding;
import org.apache.log4j.Logger;
import com.railway.util.Helper;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static final String reportFileName = "Test-Automaton-Report-" + Helper.generateTimeStampString() + ".html";
    private static final String reportDir = "TestReport";
    private static final String reportFilepath = Constant.USER_DIR + Constant.FileSeparator + reportDir;
    private static final String reportFileLocation = reportFilepath + Constant.FileSeparator + reportFileName;
    private static final String HTML_REPORT_TIME_FORMAT = "EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'";
    private static final Logger LOGGER = Logger.getLogger(ExtentManager.class.getName());

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    // Create an extent report instance
    public static ExtentReports createInstance() {
        LOGGER.info("Init report..");
        String fileName = getReportPath(reportFilepath);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding(CharEncoding.UTF_8);
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat(HTML_REPORT_TIME_FORMAT);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    // Create the report path
    private static String getReportPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                return reportFileLocation;
            } else {
                return Constant.USER_DIR;
            }
        }
        return reportFileLocation;
    }

    public static String getReportPath() {
        return reportFilepath;
    }

}
