package regression;

import annotations.CustomFrameworkAnnotations;
import base.BaseTest;
import com.github.javafaker.Faker;
import enums.TestCaseType;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMLoginPage;
import utils.StringDecodeImpl;

public final class OrangeHRMLoginPageTest extends BaseTest {

    private OrangeHRMLoginPageTest() {
    }

    private final String adminPassword = StringDecodeImpl.stringDecode("YWRtaW4xMjM=");

    @DataProvider
    public Object[][] getUsernameAndPassword() {
        Faker fs = new Faker();
        return new Object[][]{
                {fs.name().firstName(), fs.internet().password()},
                {fs.name().firstName(), fs.internet().password()},
                {fs.name().firstName(), fs.internet().password()}
        };
    }

    @CustomFrameworkAnnotations(testCaseType = TestCaseType.INTEGRATION, testCaseModule = "Login")
    @Test(testName = "Validate login to the OrangeHRM", groups = {"regression"})
    public void validateLogin() {
        String welcomeText = new OrangeHRMLoginPage().loginToHRMPortal("Admin", adminPassword).getWelcomeMessage();
        Assertions.assertThat(welcomeText)
                .containsIgnoringCase("welcome");

    }

    @CustomFrameworkAnnotations(testCaseType = TestCaseType.E2E, testCaseModule = "Login")
    @Test(testName = "Valdate login-logout operation in the OrangeHRM", groups = {"regression"})
    public void validateLoginLogoutOperation() {
        new OrangeHRMLoginPage().loginToHRMPortal("Admin   ", adminPassword).getWelcomeMessage();

        String loginPageTitle = new OrangeHRMHomePage().logout().getLoginPageTitle();
        Assertions.assertThat(loginPageTitle)
                .as("Object is Null").isNotNull()
                .as("It doesn't contain expected text").containsIgnoringCase("LOGIN Panel");

    }

    @CustomFrameworkAnnotations(testCaseType = TestCaseType.FUNCTIONAL, testCaseModule = "Login")
    @Test(testName = "Validate login with invalid credentials", dataProvider = "getUsernameAndPassword", groups = {"regression"})
    public void validateLoginWithInvalidCredentials(String username, String pwd) {
        new OrangeHRMLoginPage().loginToHRMPortal(username, pwd);
        Boolean errorMessageIsDisplayed = new OrangeHRMLoginPage().checkInvalidCredentialsErrorMessage();
        Assertions.assertThat(errorMessageIsDisplayed)
                .isTrue();

        String actualErrorMessage = new OrangeHRMLoginPage().getInvalidCredentialsErrorMessage();
        Assertions.assertThat(actualErrorMessage)
                .containsIgnoringCase("Invalid credentials");
    }

}
