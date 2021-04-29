package pages;

import base.LoggedOutPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends LoggedOutPageBase {

    private final By emailInput = By.xpath("//div[@id='root']//input[@id='username']");
    private final By passwordInput = By.xpath("//div[@id='root']//input[@id='password']");
    private final By loginButton = By.xpath("//div[@id='root']//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage login(String email, String password) {
        this.waitAndReturnElement(emailInput).sendKeys(email);
        this.waitAndReturnElement(passwordInput).sendKeys(password);
        this.waitAndReturnElement(loginButton).click();
        return new ProfilePage(driver);
    }
}
