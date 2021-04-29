package base;

import org.openqa.selenium.WebDriver;
import pages.ProfilePage;

public class LoggedInPageBase extends PageBase {

    public LoggedInPageBase(WebDriver driver) {
        super(driver);
    }

    public ProfilePage navigateToProfilePage() {
        this.clickOnUserIcon();
        return new ProfilePage(driver);
    }
}
