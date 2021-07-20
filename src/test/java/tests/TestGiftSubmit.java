package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.ChosenProductPage;
import pages.LoginPage;
import pages.MainPage;
import pages.SearchResultPage;
import utils.Utils;

import java.io.IOException;

public class TestGiftSubmit {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    // Pages variables
    MainPage mainPage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;
    ChosenProductPage chosenProductPage;

    private static final Logger logger = LogManager.getLogger(TestGiftSubmit.class);

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
        chosenProductPage = new ChosenProductPage(driver);
    }

    @AfterAll
    public static void tearDown(TestInfo info){
        logger.info("Test finish");
        driver.quit();
    }


    /*
   Given: I am on the main page (logged in/not logged in just uncomment it)
   When: I am choosing the gift throughout all needed parameters for the gift and clicking search button.
   When: I get the search result page and click on the gift card.
   Then: I get gift card page.
    */


    @Test
    public void submitGift(TestInfo info) throws Exception {
        logger.info("Begin test " + info.getTestClass());
        mainPage.goToLogin(info);
        mainPage.doLogin(info);
        mainPage.choosePrice(info);
        searchResultPage.waitForVisibility(searchResultPage.searchResult);
        searchResultPage.clickElement(searchResultPage.productCard);
        searchResultPage.waitForPageLoad();
        chosenProductPage.clickElement(chosenProductPage.productCard);
        chosenProductPage.waitForPageLoad();
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\" + info.getDisplayName() + ".png");

    }
}
