package helpers;

import constants.FrameworkConstants;
import driver.DriverManager;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WaitHelper class to create wrappers for Web Driver wait methods
 */
public final class WaitHelper {

    private WaitHelper() {
    }

    /**
     * Method to perform Explicit Wait on the specified Web element
     *
     * @param by       - The locator for the specified element
     * @param waitType - The WaitType Enum required for the specified operation
     */
    public static WebElement performExplictiWait(WaitStrategy waitType, By by) {
        WebElement element = null;
        if (waitType == WaitStrategy.VISIBILITY) {
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWaitTimeout())).until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (waitType == WaitStrategy.PRESENCE) {
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWaitTimeout())).until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitType == WaitStrategy.CLICKABLE) {
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWaitTimeout())).until(ExpectedConditions.elementToBeClickable(by));
        } else if (waitType == WaitStrategy.NONE) {
            element = DriverManager.getDriver().findElement(by);
        }
        return element;
    }
}
