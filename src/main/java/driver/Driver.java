package driver;

import enums.ConfigProperties;
import org.openqa.selenium.WebDriver;
import utils.PropertiesFileImpl;

import java.util.Objects;

/**
 * Driver class to implement WebDriver initialisation and teardown
 */
public final class Driver {

    public Driver() {
    }

    /**
     * Method to initialise WebDriver
     */
    public static void initDriver() {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriver webDriver = DriverFactory.getDriver();
            DriverManager.setDriver(webDriver);
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().get(PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.URL));
        }
    }

    /**
     * Method to quit the WebDriver
     */
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
