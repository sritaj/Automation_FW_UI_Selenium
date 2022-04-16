package pages;

import enums.WaitStrategy;
import org.openqa.selenium.By;

/**
 * OrangeHRMHomePage class to initialize locators and relevant methods for operations on Home Page
 */
public final class OrangeHRMHomePage extends BasePage {

    private final By dashboardMenuOption = By.id("menu_dashboard_index");
    private final By welcomeSection = By.id("welcome");
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
    public String getWelcomeMessage() {
        return elementHelper.getText(welcomeSection, "Welcome Msg");
    }

    /**
     * Method to logout from the Application
     */
    public OrangeHRMLoginPage logout() {
        elementHelper.clickElement(welcomeSection, "Welcome Section");
        elementHelper.clickElement(logout, WaitStrategy.CLICKABLE, "Logout Button");
        return new OrangeHRMLoginPage();
    }
}
