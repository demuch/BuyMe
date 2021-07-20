package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;

public class Test_Registration {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    private static final Logger logger = LogManager.getLogger(Test_Registration.class);

    // Pages variables
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage regPage;


    // Get all needed configs.
    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");
        driver = Utils.getDriver(browser, baseUrl);
    }

    @AfterAll
    public static void tearDown(TestInfo info){
        logger.info("Test finish");
        driver.quit();
    }

    @BeforeEach
    public void testSetup(){

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
    }

    /*
    Given: Getting to base URL
    When: Access the registration page
    Then: All pages exists and enabled
     */


    @Test
    public void testVerifyRegistrationPage(TestInfo info) throws Exception {

        logger.info("Begin test");
        mainPage.goToLogin(info);
        loginPage.goToRegistrationPage();
        regPage.doSubscribe(info);
    }
}
