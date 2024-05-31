package pages;

import enums.WaitStrategy;
import org.openqa.selenium.By;

/**
 * OrangeHRMHomePage class to initialize locators and relevant methods for operations on Home Page
 */
public final class OrangeHRMHomePage extends BasePage {

    private final By dashboardMenuOption = By.xpath("//*[contains(@class, 'userdropdown-tab')]");
    private final By userAreaSection = By.xpath("//*[contains(@class, 'userdropdown-icon')]");
    private final By logout = By.xpath("//a[text()='Logout']");

    /**
     * Method to get the Dashboard title text
     *
     * @return String - The title as String
     */
    public String getDashboardTitle() {
        return elementHelper.getText(dashboardMenuOption, "Dashboard Title");
    }

    /**
     * Method to get the Welcome message
     *
     * @return String - The msg as String
     */
    public Boolean getIsUserAreaVisible() {
        return elementHelper.elementIsDisplayed(userAreaSection, WaitStrategy.VISIBILITY);
    }

    /**
     * Method to logout from the Application
     */
    public OrangeHRMLoginPage logout() {
        elementHelper.clickElement(userAreaSection, WaitStrategy.CLICKABLE, "User area section");
        elementHelper.clickElement(logout, WaitStrategy.CLICKABLE, "Logout Button");
        return new OrangeHRMLoginPage();
    }
}
