package pages;

import base.LoggedOutPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CouchPage extends LoggedOutPageBase {


    private final By priceElement = By.xpath("//span[contains(@class,'range-revamp-price')]/span[@class='range-revamp-price__integer']");

    public CouchPage(WebDriver driver) {
        super(driver);
        driver.get("https://www.ikea.com/hu/hu/p/friheten-3-szemelyes-kinyithato-kanape-hyllie-bezs-00431716/");
    }

    public int getPrice() {
        String priceStrWithSpaces = this.waitAndReturnElement(priceElement).getText();
        String priceStr = priceStrWithSpaces.replace(" ", "");
        return Integer.parseInt(priceStr);
    }
}
