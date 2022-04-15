package regression;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMLoginPage;

public final class OrangeHRMLoginPageTest extends BaseTest {

    private OrangeHRMLoginPageTest(){}

    @DataProvider
    public Object[][] getUsernameAndPassword(){
        Faker fs = new Faker();
        return new Object[][]{
                {fs.name().firstName(), fs.internet().password()},
                {fs.name().firstName(), fs.internet().password()},
                {fs.name().firstName(), fs.internet().password()}
        };
    }

    @Test(testName = "Validate login to the OrangeHRM")
    public void validateLogin(){
        String welcomeText = new OrangeHRMLoginPage().loginToHRMPortal("Admin", "admin123").getWelcomeMessage();
        Assertions.assertThat(welcomeText)
                .containsIgnoringCase("welcome");

    }

    @Test(testName = "Valdate login-logout operation in the OrangeHRM")
    public void validateLoginLogoutOperation(){
        new OrangeHRMLoginPage().loginToHRMPortal("Admin   ", "admin123").getWelcomeMessage();

        String loginPageTitle = new OrangeHRMHomePage().logout().getLoginPageTitle();
        Assertions.assertThat(loginPageTitle)
                .containsIgnoringCase("LOGIN Panel");
    }

    @Test(testName = "Validate login with invalid credentials", dataProvider = "getUsernameAndPassword")
    public void validateLoginWithInvalidCredentials(String username, String pwd){
        new OrangeHRMLoginPage().loginToHRMPortal(username, pwd);
        Boolean errorMessageIsDisplayed  = new OrangeHRMLoginPage().checkInvalidCredentialsErrorMessage();
        Assertions.assertThat(errorMessageIsDisplayed)
                .isTrue();

        String actualErrorMessage =  new OrangeHRMLoginPage().getInvalidCredentialsErrorMessage();
                Assertions.assertThat(actualErrorMessage)
                .containsIgnoringCase("Invalid credentials");
    }

}
