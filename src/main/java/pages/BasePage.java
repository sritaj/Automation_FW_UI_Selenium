package pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class BasePage {

    WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }
}
