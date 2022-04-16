package base;

import driver.Driver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reports.ExtentReportImpl;

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

        Driver.initDriver();
    }

    @AfterMethod
    protected void tearDown(ITestResult result, Method method) {

        if (ITestResult.FAILURE == result.getStatus()) {
            String testName = result.getName();
            ExtentReportImpl.failTest(testName);

        } else if (ITestResult.SUCCESS == result.getStatus()) {
            String testName = result.getName();
            ExtentReportImpl.passTest(testName);

        } else if (ITestResult.SKIP == result.getStatus()) {
            String testName = result.getName();
            ExtentReportImpl.skipTest(testName);
        }
        Driver.quitDriver();
    }

    @AfterSuite()
    public void afterSuite() {
        ExtentReportImpl.flushReports();
    }
}
