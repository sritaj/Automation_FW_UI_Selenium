package pages;

import driver.DriverManager;
import helpers.ElementHelper;
import org.openqa.selenium.WebDriver;

/**
 * BasePage class to setup constructor for relevant classes that can be extended and utilized by revelant POM pages
 */
public class BasePage {

    protected WebDriver driver;
    protected ElementHelper elementHelper;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        elementHelper = new ElementHelper();
    }
}
