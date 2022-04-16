package driver;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager class to implement Thread Safety for WebDriver for parallel executions
 */
public final class DriverManager {

    private DriverManager() {
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    public static void unload() {
        driver.remove();
    }

}
