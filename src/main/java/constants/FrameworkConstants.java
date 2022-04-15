package constants;

public final class FrameworkConstants {

    private FrameworkConstants(){}

    private static final String RESOURCEPATH = System.getProperty("user.dir");
    private static final String PROPERTIESFILEPATH = RESOURCEPATH + "/src/test/resources/config/config.properties";
    private static final int EXPLICITWAITTIMEOUT = 10;

    public static String getPropertiesFilePath(){
        return PROPERTIESFILEPATH;
    }

    public static int getExplicitWaitTimeout() { return EXPLICITWAITTIMEOUT; }

}
