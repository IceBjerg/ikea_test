package pages;

import base.LoggedOutPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainPage extends LoggedOutPageBase {

    private final By greetingBox = By.xpath("//div[@class='hp5wssc']/h1");


    public MainPage(WebDriver driver, boolean shouldLoad) {
        super(driver);
        if (shouldLoad) {
            // forcing reload or load
            this.driver.get("https://www.ikea.com/hu/hu/");
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(greetingBox));
    }
}
