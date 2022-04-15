package regression;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMLoginPage;

public final class OrangeHRMLoginPageTest extends BaseTest {

    private OrangeHRMLoginPageTest(){}

    @Test
    public void validateLogin(){
        String welcomeText = new OrangeHRMLoginPage().loginToHRMPortal("Admin   ", "admin123").getWelcomeMessage();
        Assertions.assertThat(welcomeText)
                .containsIgnoringCase("welcome");

        String loginPageTitle = new OrangeHRMHomePage().logout().getLoginPageTitle();
        Assertions.assertThat(loginPageTitle)
                .containsIgnoringCase("LOGIN Panel");
    }
}
