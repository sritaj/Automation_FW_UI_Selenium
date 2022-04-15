package pages;

import driver.DriverManager;
import helpers.ElementHelper;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected ElementHelper elementHelper;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
        elementHelper = new ElementHelper();
    }
}
