package helpers;

import driver.DriverManager;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.LoggerImpl;

import java.util.List;

/**
 * ElementHelper class to create wrappers for WebElement methods with Logging and relevant operations
 */
public final class ElementHelper {

    private WebDriver driver;

    public ElementHelper() {
        this.driver = DriverManager.getDriver();
    }

    /**
     * Method to get the Element using the specified locator
     *
     * @param by - The locator for the specified element
     */
    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    /**
     * Method to click on the Element using the specified locator
     *
     * @param by          - The locator for the specified element
     * @param elementInfo - Element which is clicked
     */
    public void clickElement(By by, String elementInfo) {
        getElement(by).click();
        LoggerImpl.logSteps("Element clicked : " + elementInfo);
    }

    /**
     * Method to get the Element using the specified locator
     *
     * @param by          - The locator for the specified element
     * @param waitType    - The WaitType Enum required
     * @param elementInfo - Element which is clicked
     */
    public void clickElement(By by, WaitStrategy waitType, String elementInfo) {
        WaitHelper.performExplictiWait(waitType, by).click();
        LoggerImpl.logSteps("Element clicked : " + elementInfo);
    }

    /**
     * Method to get Title for specified page
     *
     * @param by          - The locator for the specified element
     * @param elementInfo - Element which is clicked
     * @return String - The title of the specified element
     */
    public String getTitle(By by, String elementInfo) {
        LoggerImpl.logSteps("Get title for : " + elementInfo);
        return driver.getTitle();
    }

    /**
     * Method to get Text for the specified element
     *
     * @param by          - The locator for the specified element
     * @param elementInfo - Element which is clicked
     * @return String - The title of the specified element
     */
    public String getText(By by, String elementInfo) {
        LoggerImpl.logSteps("Get text for : " + elementInfo);
        return getElement(by).getText();
    }

    /**
     * Method to send input to the Element using the specified locator
     *
     * @param by          - The locator for the specified element
     * @param elementInfo - Element which is clicked
     * @param input       - The user input as string for the specified element
     */
    public void sendKeys(By by, String input, String elementInfo) {
        LoggerImpl.logSteps("Send input '" + input + "' to : " + elementInfo);
        getElement(by).sendKeys(input);
    }

    /**
     * Method to check the Element is displayed using the specified locator
     *
     * @param by - The locator for the specified element
     * @return boolean - True/False based on the element
     */
    public boolean elementIsDisplayed(By by) {
        return getElement(by).isDisplayed();
    }

    /**
     * Method to check the Element is enabled using the specified locator
     *
     * @param by - The locator for the specified element
     * @return boolean - True/False based on the element
     */
    public boolean elementIsEnabled(By by) {
        return getElement(by).isEnabled();
    }

    /**
     * Method to check the Element is selected using the specified locator
     *
     * @param by - The locator for the specified element
     * @return boolean - True/False based on the element
     */
    public boolean elementIsSelected(By by) {
        return getElement(by).isSelected();
    }

    /**
     * Method to get the list of WebElements using the specified locator
     *
     * @param by - The locator for the specified element
     * @return List - WebElements as list
     */
    public List<WebElement> getElements(By by) {
        return driver.findElements(by);
    }

}
