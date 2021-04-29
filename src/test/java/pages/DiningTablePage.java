package pages;

import base.LoggedOutPageBase;
import models.DiningTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DiningTablePage extends LoggedOutPageBase {

    private final By priceElement = By.xpath("//span[contains(@class,'range-revamp-price')]/span[@class='range-revamp-price__integer']");

    public DiningTablePage(WebDriver driver, DiningTable diningTable) {
        super(driver);
        driver.get(diningTable.url);
    }

    public int getPrice() {
        String priceStrWithSpaces = this.waitAndReturnElement(priceElement).getText();
        String priceStr = priceStrWithSpaces.replace(" ", "");
        return Integer.parseInt(priceStr);
    }
}
