package helpers;

import driver.DriverManager;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
     * @param by - The locator for the specified element
     */
    public void clickElement(By by) {
        getElement(by).click();
    }

    /**
     * Method to get the Element using the specified locator
     *
     * @param by - The locator for the specified element
     * @param waitType - The WaitType Enum required
     */
    public void clickElement(By by, WaitStrategy waitType) {
        WaitHelper.performExplictiWait(waitType, by).click();
    }

    /**
     * Method to click on the Element using the specified locator
     *
     * @param by - The locator for the specified element
     * @return String - The title of the specified element
     */
    public String getTitle(By by) {
        return getElement(by).getText();
    }

    /**
     * Method to send input to the Element using the specified locator
     *
     * @param by - The locator for the specified element
     * @param input - The user input as string for the specified element
     */
    public void sendKeys(By by, String input) {
        getElement(by).sendKeys(input);
    }

    /**
     * Method to check the Element is displayed using the specified locator
     *
     * @param by - The locator for the specified element
     * @return boolean - True/False based on the element
     */
    public boolean elementIsDisplayed(By by){
        return getElement(by).isDisplayed();
    }
}
