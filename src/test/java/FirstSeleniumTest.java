import io.github.cdimascio.dotenv.Dotenv;
import models.DiningTable;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;


public class FirstSeleniumTest {
    private WebDriver driver;
    private final Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testCompleteWorkFlow() {
        // open
        MainPage mainPage = new MainPage(driver, true);
        assertTrue(mainPage.getBodyText().contains("\u00DCdv\u00F6zl\u00FCnk az IKEA-ban")); // Üdvözlünk az IKEA-ban
        LoginPage loginPage = mainPage.navigateToLoginPage();
        // log in
        ProfilePage profilePage = loginPage.login(dotenv.get("email"), dotenv.get("password"));
        // search for item
        SearchResultPage searchResultPage = profilePage.searchForItem("asztal");
        assertTrue(searchResultPage.getTitle().contains("asztal"));
        // log out
        profilePage = searchResultPage.navigateToProfilePage();
        mainPage = profilePage.logout();
    }

    @Test
    public void testCookies() {
        MainPage mainPage = new MainPage(driver, true);
        // generate random string
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        // add cookie
        mainPage.addCookie("testCookie", generatedString);
        // check if it's written
        assertEquals(generatedString, mainPage.getCookie("testCookie"));
        // delete all cookies
        mainPage.deleteAllCookies();
        // should not be equal anymore
        assertNotEquals(generatedString, mainPage.getCookie("testCookie"));
    }

    @Test
    public void testBrowserBackButton() {
        MainPage mainPage = new MainPage(driver, true);
        SearchResultPage searchResultPage = mainPage.searchForItem("asztal");
        driver.navigate().back();
        assertFalse(searchResultPage.getTitle().contains("asztal"));
    }

    @Test
    public void testCouchPrice() {
        CouchPage couchPage = new CouchPage(driver);
        assertEquals(couchPage.getPrice(), 129900);
    }

    @Test
    public void testFavouriteDiningTablePrices() {
        DiningTable[] diningTables = new DiningTable[] {
                new DiningTable("https://www.ikea.com/hu/hu/p/ekedalen-meghosszabbithato-asztal-feher-70340807/", 49990),
                new DiningTable("https://www.ikea.com/hu/hu/p/lerhamn-asztal-es-2-szek-vilagos-antik-pac-feherre-pacolt-vittaryd-bezs-s39306288/", 39970),
                new DiningTable("https://www.ikea.com/hu/hu/p/norbo-fali-lehajthato-asztal-nyir-80091713/", 14990),
        };

        for (DiningTable table : diningTables) {
            DiningTablePage diningTablePage = new DiningTablePage(driver, table);
            assertEquals(table.price, diningTablePage.getPrice());
        }
    }
    
    @AfterEach
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
