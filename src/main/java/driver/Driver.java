package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

public class Driver {

    private Driver(){}

    private static WebDriver webDriver;

    public static void initDriver(){
        if(Objects.isNull(webDriver)){
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(chromeOptions);
        }
    }

    public static void quitDriver(){
        if(Objects.nonNull(webDriver)){
            webDriver.quit();
            webDriver = null;
        }

    }
}
