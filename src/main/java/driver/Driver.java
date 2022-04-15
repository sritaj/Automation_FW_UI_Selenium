package driver;

import enums.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertiesFileImpl;

import java.util.Objects;

public final class Driver {

    public Driver(){}

    /**
     * Method to initialise WebDriver
     */
    public static void initDriver(){
        if(Objects.isNull(DriverManager.getDriver())){
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver(chromeOptions);
            DriverManager.setDriver(webDriver);
            DriverManager.getDriver().manage().window().maximize();
            DriverManager.getDriver().get(PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.URL));
        }
    }

    /**
     * Method to quit the WebDriver
     */
    public static void quitDriver(){
        if(Objects.nonNull(DriverManager.getDriver())){
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }

}
