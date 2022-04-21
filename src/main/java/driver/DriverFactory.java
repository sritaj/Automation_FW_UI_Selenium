package driver;

import enums.ConfigProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertiesFileImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * DriverFactory class to setup WebDriver based on Local or Remote run
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    /**
     * Method to setup WebDriver for local or remote run and initialising the Browser specified based on Properties file or Maven command
     *
     * @return WebDriver - WebDriver object
     */
    public static WebDriver getDriver() {
        WebDriver driver = null;
        String runmode = PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.RUNMODE).trim();
        String url = PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.HUBURL).trim();
        String browser = null;

        //Checking if Browser is specified from Maven command, if not Browser to be picked from properties
        if (System.getProperty("BROWSER") == null) {
            if (Objects.isNull(PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.BROWSER))
                    || PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.BROWSER).isEmpty()) {
                browser = "chrome";
            } else {
                browser = PropertiesFileImpl.getDataFromPropertyFile(ConfigProperties.BROWSER).trim();
            }
        }else{
            browser = System.getProperty("BROWSER");
        }


        DesiredCapabilities cap;
        if (browser.equalsIgnoreCase("chrome")) {
            if (runmode.equalsIgnoreCase("remote")) {
                cap = new DesiredCapabilities();
                cap.setBrowserName(Browser.CHROME.browserName());

                try {
                    driver = new RemoteWebDriver(new URL(url), cap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            if (runmode.equalsIgnoreCase("remote")) {
                cap = new DesiredCapabilities();
                cap.setBrowserName(Browser.FIREFOX.browserName());

                try {
                    driver = new RemoteWebDriver(new URL(url), cap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

            } else {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
            }
        }
        return driver;
    }
}
