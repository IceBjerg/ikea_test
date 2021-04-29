package pages;

import base.LoggedInPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends LoggedInPageBase {

    private final By logoutAnchor = By.xpath("//a[contains(@class,'profile__link')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public MainPage logout() {
        this.waitAndReturnElement(logoutAnchor).click();
        return new MainPage(driver, false);
    }
}
