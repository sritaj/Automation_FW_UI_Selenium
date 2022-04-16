package constants;

import enums.ConfigProperties;
import utils.PropertiesFileImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String RESOURCEPATH = System.getProperty("user.dir");
    private static final String PROPERTIESFILEPATH = RESOURCEPATH + "/src/test/resources/config/config.properties";
    private static final int EXPLICITWAITTIMEOUT = 10;
    private static final String JSONTESTDATAFILEPATH = RESOURCEPATH + "/src/test/resources/config/config.json";
    private static final String EXTENTREPORTPATH = RESOURCEPATH + "/test-reports/";
    private static final int RETRYCOUNTS = 2;

    public static String getPropertiesFilePath() {
        return PROPERTIESFILEPATH;
    }

    public static int getExplicitWaitTimeout() {
        return EXPLICITWAITTIMEOUT;
    }

    public static String getJsonDataFilePath() {
        return JSONTESTDATAFILEPATH;
    }

    public static String getExtentReportPath() {
        if (PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("yes")) {
            return EXTENTREPORTPATH;
        }
        return EXTENTREPORTPATH + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "/";
    }

    public static int getRetryCounts() {
        return RETRYCOUNTS;
    }

}
