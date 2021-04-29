package base;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoggedOutPageBase extends PageBase {

    public LoggedOutPageBase(WebDriver driver) {
        super(driver);
    }


    public LoginPage navigateToLoginPage() {
        this.clickOnUserIcon();
        return new LoginPage(driver);
    }
}
