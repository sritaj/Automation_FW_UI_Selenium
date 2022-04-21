package utils;

import constants.FrameworkConstants;
import enums.ConfigProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import reports.ExtentReportImpl;

import java.io.File;

/**
 * LoggerImpl class to implement Logging methods for Tests/Framework based on ExtentReports Logs and Log4j2
 */
public final class LoggerImpl {

    private LoggerImpl() {
    }

    private static Logger logger = LogManager.getLogger();

    /**
     * Method to start writing Logs in TestNG BeforeMethod in BaseTest and creating Log files
     *
     * @param testName - Test Name
     */
    public static synchronized void startLog(String testName) {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.LOG4JLOGGINGREQUIRED).trim().equalsIgnoreCase("yes")) {
            saveLogsToDirectory(testName);
            logSteps(testName + " -> Execution started");
        } else {
            ExtentReportImpl.logSteps(testName + " -> Execution started");
        }
    }

    /**
     * Method to write Log for TestNG AfterMethod in BaseTest
     *
     * @param testName - Test Name
     */
    public static void endLog(String testName) {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.LOG4JLOGGINGREQUIRED).trim().equalsIgnoreCase("yes")) {
            logSteps(testName + " -> Execution ended");
        } else {
            ExtentReportImpl.logSteps(testName + " -> Execution ended");
        }
    }

    /**
     * Method to write Logs to the specified Directory
     *
     * @param testName - Test Name
     */
    private static void saveLogsToDirectory(String testName) {

        int noOfFiles = 0;

        File dir = new File(FrameworkConstants.getLogsFolderPath());
        if (dir.exists()) {
            int count = 0;
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".log") && file.getName().contains(testName)) {
                    count++;
                }
            }
            noOfFiles = count;
        }

        noOfFiles++;
        String logFileName = testName + "_" + noOfFiles;

        ThreadContext.put("logFilename", logFileName);
    }

    /**
     * Method to getCurrentLog
     *
     * @return Logger
     */
    public static Logger getCurrentLog() {
        return logger;
    }

    /**
     * Method to set Additional info while writing the Logs
     *
     * @return String - additional info clubbed together
     */
    public static String getCallInfo() {
        String callInfo;
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        callInfo = className + ":" + methodName + " " + "==>  ";
        return callInfo;

    }

    /**
     * Method to write Logs for WebElement Actions, Test steps
     *
     * @param message - Message to be displayed in the Logs/Extent Reports
     */
    public static void logSteps(String message) {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.LOG4JLOGGINGREQUIRED).trim().equalsIgnoreCase("yes")) {
            getCurrentLog().info(getCallInfo() + message);
        }
        ExtentReportImpl.logSteps(message);
    }

}
