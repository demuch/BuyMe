package tests;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.CareersPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBonus {

    public static WebDriver driver;
    public static String baseUrl;
    static String browser;

    private static final Logger logger = LogManager.getLogger(TestBonus.class);

    // Pages variables
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage regPage;
    CareersPage careersPage;


    // Get all needed configs.
    @BeforeAll
    public static void setup() throws IOException {

        PropertyConfigurator.configure(System.getProperty("user.dir") + "\\log4j.properties");
        baseUrl = Utils.getProperty("baseurl");
        browser = Utils.getProperty("browser");
        driver = Utils.getDriver(browser, baseUrl);
    }

    @BeforeEach
    public void testSetup() {

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
        careersPage = new CareersPage(driver);
    }


    @AfterAll
    public static void tearDown(TestInfo info) {
        logger.info("Test finish");
        driver.quit();
    }

    /*
    Given: I am on the main page
    When: I am scrolling down to the career link and taking a screenshot.
    When: I clicking in the link and switching to the career page.
    Then: I checking if there at least one open vacancy.
     */

    @Test
    @Order(1)
    public void careerTest(TestInfo info) throws Exception {
        logger.info("Test starts: " + info.getDisplayName().substring(0, 10));
        mainPage.bonus(info);
        Assertions.assertTrue(careersPage.checkIfJob(careersPage.jobList));
        careersPage.closeAndSwitchToMainWindow();

    }

    @Test
    @Order(2)
    public void errorMsgOnLogin(TestInfo info) throws Exception {
        logger.info("Test starts: " + info.getDisplayName().substring(0, 15));
        mainPage.goToLogin(info);
        loginPage.doEmptyLogin(info);
        Assertions.assertTrue(loginPage.checkErrorMsg(loginPage.errorMsg.getText()));
}
}
