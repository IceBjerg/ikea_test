package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.SearchResultPage;


class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By userIcon = By.xpath("//li[@class='hnf-header__profile-link']/a[contains(@class,'hnf-btn')]");
    private final By searchInput = By.xpath("//input[@type='search']");
    private final By results = By.xpath("//div[@class='results']");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    public SearchResultPage searchForItem(String item) {
        this.waitAndReturnElement(searchInput).sendKeys(item);
        this.waitAndReturnElement(searchInput).sendKeys(Keys.ENTER);
        this.waitAndReturnElement(results); // wait until results are shown
        return new SearchResultPage(driver);
    }


    public void clickOnUserIcon() {
        this.waitAndReturnElement(userIcon).click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void addCookie(String key, String value) {
        driver.manage().addCookie(new Cookie(key, value));
    }

    public String getCookie(String key) {
        Cookie cookie = driver.manage().getCookieNamed(key);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }
}
