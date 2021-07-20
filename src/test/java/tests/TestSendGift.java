package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.Utils;

import java.io.IOException;

public class TestSendGift {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    // Pages variables
    MainPage mainPage;
    LoginPage loginPage;
    SearchResultPage searchResultPage;
    PurchasingPage purchasingPage;
    ChosenProductPage chosenProductPage;
    AddressPage addressPage;

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
        purchasingPage = new PurchasingPage(driver);
        chosenProductPage = new ChosenProductPage(driver);
        addressPage = new AddressPage(driver);

    }

    @AfterAll
    public static void tearDown(TestInfo info){
        logger.info("Test finish");
        driver.quit();
    }

     /*
    Given: I am on the gift card details page (logged in/not logged in just uncomment it)
    When: I am filling all the fields are needed.
    Then: I get the last page before a payment.
     */

    @Test
    public void sendGift(TestInfo info) throws Exception {
        logger.info("Begin test");
//        mainPage.goToLogin();
//        mainPage.doLogin(info);
        mainPage.choosePrice(info);
        searchResultPage.clickElement(searchResultPage.productCard);
        searchResultPage.waitForPageLoad();
        chosenProductPage.clickElement(chosenProductPage.productCard);
        chosenProductPage.waitForPageLoad();
        purchasingPage.fillGiftForm(info);
        addressPage.submitAddress();
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\" + info.getDisplayName() + ".png");


    }
}
