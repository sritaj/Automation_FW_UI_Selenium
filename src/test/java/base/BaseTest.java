package base;

import driver.Driver;
import enums.ConfigProperties;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reports.ExtentReportImpl;
import utils.PropertiesFileImpl;

import java.lang.reflect.Method;

public class BaseTest {

    protected BaseTest() {
    }

    @BeforeSuite
    public void beforeSuite(ITestContext context) {

        // Extent Report Initialization
        ExtentReportImpl.initializeReport();
    }

    @BeforeMethod
    protected void setUp(Method method) {

        // Extent Report Initialization
        String testDescription = method.getAnnotation(Test.class).testName();
        String testName = method.getName();
        ExtentReportImpl.startTestExecution(testDescription, testName);
        ExtentReportImpl.logSteps(testName + " -> Execution starts");

        Driver.initDriver();
    }

    @AfterMethod
    protected void tearDown(ITestResult result, Method method) {
        String testName = result.getName();
        ExtentReportImpl.logSteps(result.getName() + " -> Execution ended");
        if (ITestResult.FAILURE == result.getStatus()) {
            ExtentReportImpl.failTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONFAIL), result.getThrowable().getMessage());

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            ExtentReportImpl.passTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONPASS));

        } else if (ITestResult.SKIP == result.getStatus()) {
            ExtentReportImpl.skipTest(testName, PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.SCREENSHOTONSKIP));
        }
        Driver.quitDriver();
    }

    @AfterSuite()
    public void afterSuite() {
        ExtentReportImpl.flushReports();
    }
}
