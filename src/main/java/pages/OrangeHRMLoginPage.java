package pages;

import enums.WaitStrategy;
import org.openqa.selenium.By;

/**
 * OrangeHRMLoginPage class to initialize locators and relevant methods for operations on Login Page
 */
public final class OrangeHRMLoginPage extends BasePage {

    public OrangeHRMLoginPage() {
        super();
    }

    private final By usernameTxtBox = By.name("username");
    private final By passwordTxtBox = By.name("password");
    private final By loginButton = By.xpath("//*[@type='submit']");
    private final By loginPageTitle = By.id("logInPanelHeading");
    private final By invalidCredentials = By.cssSelector("[role='alert']");

    /**
     * Method to login to the Application
     *
     * @param username - username as string
     * @param password - password as string
     */
    public OrangeHRMHomePage loginToHRMPortal(String username, String password) {
        elementHelper.sendKeys(usernameTxtBox, WaitStrategy.CLICKABLE, username, "username");
        elementHelper.sendKeys(passwordTxtBox, password, "password");
        elementHelper.clickElement(loginButton, "Login Button");
        return new OrangeHRMHomePage();
    }

    /**
     * Method to get the title of the Login page
     *
     * @return String - The title as String
     */
    public String getLoginPageTitle() {
        return elementHelper.getTitle(loginPageTitle, "Login Page");
    }

    /**
     * Method to get the Invalid credentials error msg of the Login page
     *
     * @return String - The error message as String
     */
    public String getInvalidCredentialsErrorMessage() {
        return elementHelper.getText(invalidCredentials, "Invalid Credentials");
    }

    /**
     * Method to check if the Invalid credentials error msg is displayed
     *
     * @return boolean - True/False based on the Element
     */
    public boolean checkInvalidCredentialsErrorMessage() {
        return elementHelper.elementIsDisplayed(invalidCredentials, WaitStrategy.VISIBILITY);
    }

}
