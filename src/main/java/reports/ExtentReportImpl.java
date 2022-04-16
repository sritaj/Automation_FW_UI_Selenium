package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;

import java.util.Objects;

public final class ExtentReportImpl {

    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static Markup m;
    private final static String testInfo = "TEST CASE: - ";

    private ExtentReportImpl() {
    }

    public static void initializeReport() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportPath() + "AutomationReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("AutomationReport");
            spark.config().setReportName("Selenium UI Automation`");
            extent.attachReporter(spark);
        }
    }

    public static void startTestExecution(String testDescription, String testName) {
        ExtentReportManager.setTest(extent.createTest(testDescription, testName));
    }

    public static void passTest(String testName) {
        String passText = "<b>" + testInfo + testName + " PASSED" + "</b>";
        m = MarkupHelper.createLabel(passText, ExtentColor.GREEN);
        ExtentReportManager.getTest().pass(m);
    }

    public static void skipTest(String testName) {
        String skipTest = "<b>" + testInfo + testName + " SKIPPED" + "</b>";
        m = MarkupHelper.createLabel(skipTest, ExtentColor.GREY);
        ExtentReportManager.getTest().skip(m);
    }

    public static void failTest(String testName) {
        String failText = "<b>" + testInfo + testName + " FAILED" + "</b>";
        m = MarkupHelper.createLabel(failText, ExtentColor.RED);
        ExtentReportManager.getTest().fail(m);
    }

    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
    }
}
