package base;

import constants.FrameworkConstants;
import driver.Driver;
import enums.ConfigProperties;
import listeners.RetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reports.ExtentReportImpl;
import utils.EKLImpl;
import utils.PropertiesFileImpl;
import utils.TakeVideoImpl;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {

    protected BaseTest() {
    }

    private TakeVideoImpl captureVideo;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {

        // Extent Report Initialization
        ExtentReportImpl.initializeReport();

        //Initializing Tests with Retry Analyzer Annotation
        for(ITestNGMethod method : context.getAllTestMethods()){
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
        }

        //Clearing the EKS schema for older results, if ESKSetup is set to yes
        EKLImpl.clearPreviousELKResults();

        //Initializing object for taking Screen Recording
        try {
            captureVideo = new TakeVideoImpl(new File(FrameworkConstants.getScreenRecordingPath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    @BeforeMethod(alwaysRun = true)
    protected void setUp(Method method) {

        // Extent Report Initialization
        String testDescription = method.getAnnotation(Test.class).testName();
        String testName = method.getName();
        ExtentReportImpl.startTestExecution(testDescription, testName);
        ExtentReportImpl.logSteps(testName + " -> Execution starts");

        Driver.initDriver();

        // Starting the Screen Recording
        captureVideo.startRecording(method.getName(), true);
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult result, Method method) {
        String testName = result.getName();
        ExtentReportImpl.logSteps(result.getName() + " -> Execution ended");
        ExtentReportImpl.addDetails(method);

        if (ITestResult.FAILURE == result.getStatus()) {
            RetryAnalyzer rerun = new RetryAnalyzer();
            rerun.retry(result);
            ExtentReportImpl.failTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONFAIL), result.getThrowable().getMessage(), result.getThrowable());
            EKLImpl.sendResultsToELK(result.getName(), "FAIL");
            captureVideo.stopRecording(true);

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            ExtentReportImpl.passTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONPASS));
            EKLImpl.sendResultsToELK(result.getName(), "PASS");

        } else if (ITestResult.SKIP == result.getStatus()) {
            ExtentReportImpl.skipTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONSKIP));
            EKLImpl.sendResultsToELK(result.getName(), "SKIP");
        }
        Driver.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportImpl.flushReports();
    }
}
