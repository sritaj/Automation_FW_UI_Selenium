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

}
