package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.SearchResultPage;
import pages.LoginPage;
import pages.MainPage;
import utils.Utils;

import java.io.IOException;

public class TestChooseGift {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    // Pages variables
    MainPage mainPage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;

    private static final Logger logger = LogManager.getLogger(TestChooseGift.class);

    // Get all needed configs.
    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");
        driver = Utils.getDriver(browser, baseUrl);
    }

    @BeforeEach
    public void testSetup(){

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        searchResultPage = new SearchResultPage(driver);
    }

    @AfterAll
    public static void tearDown(TestInfo info){
        logger.info("Test finish");
        driver.quit();
    }

    /*
    Given: I am on the main page (logged in/not logged in just uncomment it)
    When: I am choosing the gift throughout all needed parameters for the gift and clicking search button.
    Then: I get the search result page.
     */

    @Test
    public void searchForItem(TestInfo info) throws Exception {
        logger.info("Begin test");
//        mainPage.goToLogin();
//        mainPage.doLogin(info);
        mainPage.choosePrice(info);
        searchResultPage.waitForVisibility(searchResultPage.searchResult);
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\" + info.getDisplayName() + ".png");
        Assertions.assertEquals(searchResultPage.searchResult.getText(),"תוצאות חיפוש");
        searchResultPage.clickElement(searchResultPage.productCard);
    }

}
