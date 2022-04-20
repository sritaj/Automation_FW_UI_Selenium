package driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

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

    static void setDriver(WebDriver driverref) {
        if (Objects.nonNull(driverref))
            driver.set(driverref);
    }

    static void unload() {
        driver.remove();
    }

}
