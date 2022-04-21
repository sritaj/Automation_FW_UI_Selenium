package constants;

import enums.ConfigProperties;
import utils.PropertiesFileImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FrameworkConstants class to store paths and common properties
 */
public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String RESOURCEPATH = System.getProperty("user.dir");
    private static final String PROPERTIESFILEPATH = RESOURCEPATH + "/src/test/resources/config/config.properties";
    private static final int EXPLICITWAITTIMEOUT = 10;
    private static final String JSONCONFIGFILEPATH = RESOURCEPATH + "/src/test/resources/config/config.json";
    private static final String EXTENTREPORTPATH = RESOURCEPATH + "/test-reports/";
    private static final String SCREENRECORDINGPATH = RESOURCEPATH + "/target/screen-records";
    private static final String LOGSFOLDERPATH = RESOURCEPATH + "target/logs/";
    private static final int RETRYCOUNTS = 2;

    /**
     * Method to get Properties file path
     *
     * @return String - Properties file path
     */
    public static String getPropertiesFilePath() {
        return PROPERTIESFILEPATH;
    }

    /**
     * Method to get Timeout to be set for Explicit Wait
     *
     * @return int - Time
     */
    public static int getExplicitWaitTimeout() {
        return EXPLICITWAITTIMEOUT;
    }

    /**
     * Method to get JSON config file path
     *
     * @return String - JSON config file path
     */
    public static String getJsonConfigFilePath() {
        return JSONCONFIGFILEPATH;
    }

    /**
     * Method to get Extent Report path
     *
     * @return String - Extent report path
     */
    public static String getExtentReportPath() {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("yes")) {
            return EXTENTREPORTPATH;
        }
        return EXTENTREPORTPATH + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "/";
    }

    /**
     * Method to get retry counts
     *
     * @return int - retry counts
     */
    public static int getRetryCounts() {
        return RETRYCOUNTS;
    }

    /**
     * Method to get Screen Recording directory path
     *
     * @return String - Screen Recording directory path
     */
    public static String getScreenRecordingPath() {
        return SCREENRECORDINGPATH;
    }

    /**
     * Method to get Logs directory path
     *
     * @return String - Logs directory path
     */
    public static String getLogsFolderPath() {
        return LOGSFOLDERPATH;
    }

}
