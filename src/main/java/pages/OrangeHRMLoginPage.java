package pages;

import org.openqa.selenium.By;

public final class OrangeHRMLoginPage extends BasePage {

    public OrangeHRMLoginPage() {
        super();
    }

    private final By usernameTxtBox = By.id("txtUsername");
    private final By passwordTxtBox = By.id("txtPassword");
    private final By loginButton = By.id("btnLogin");
    private final By loginPageTitle = By.id("logInPanelHeading");
    private final By invalidCredentials = By.id("spanMessage");

    /**
     * Method to login to the Application
     *
     * @param username - username as string
     * @param password - password as string
     */
    public OrangeHRMHomePage loginToHRMPortal(String username, String password) {
        elementHelper.sendKeys(usernameTxtBox, username);
        elementHelper.sendKeys(passwordTxtBox, password);
        elementHelper.clickElement(loginButton);
        return new OrangeHRMHomePage();
    }

    /**
     * Method to get the title of the Login page
     *
     * @return String - The title as String
     */
    public String getLoginPageTitle() {
        return elementHelper.getTitle(loginPageTitle);
    }

    /**
     * Method to get the Invalid credentials error msg of the Login page
     *
     * @return String - The error message as String
     */
    public String getInvalidCredentialsErrorMessage() {
        return elementHelper.getTitle(invalidCredentials);
    }

    /**
     * Method to check if the Invalid credentials error msg is displayed
     *
     * @return boolean - True/False based on the Element
     */
    public boolean checkInvalidCredentialsErrorMessage() {
        return elementHelper.elementIsDisplayed(invalidCredentials);
    }

}
