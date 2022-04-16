package base;

import driver.Driver;
import enums.ConfigProperties;
import listeners.RetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reports.ExtentReportImpl;
import utils.PropertiesFileImpl;

import java.lang.reflect.Method;

public class BaseTest {

    protected BaseTest() {
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {

        // Extent Report Initialization
        ExtentReportImpl.initializeReport();

        //Initializing Tests with Retry Analyzer Annotation
        for(ITestNGMethod method : context.getAllTestMethods()){
            method.setRetryAnalyzerClass(RetryAnalyzer.class);
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

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            ExtentReportImpl.passTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONPASS));

        } else if (ITestResult.SKIP == result.getStatus()) {
            ExtentReportImpl.skipTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONSKIP));
        }
        Driver.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportImpl.flushReports();
    }
}
