package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.io.IOException;

public class MainPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(MainPage.class);

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='seperator-link']")
    public WebElement linkToLogin;

    @FindBy(xpath = "//a[@class='chosen-single']")
    public WebElement dropDownPrice;

    @FindBy(xpath = "//ul[@class='chosen-results']/li[3]")
    public WebElement chosenPrice;

    @FindBy(xpath = "//a[@class='chosen-single']/span[text()='אזור']")
    public WebElement dropDownArea;

    @FindBy(xpath = "//div[@class='chosen-drop']/ul[@class='chosen-results']/li[text()='צפון']")
    public WebElement chosenArea;

    @FindBy(xpath = "//a[@class='chosen-single']/span[text()='קטגוריה']")
    public WebElement dropDownCategory;

    @FindBy(xpath = "//div[@class='chosen-drop']/ul[@class='chosen-results']/li[text()='גיפט קארד לארוחת בוקר ובתי קפה']")
    public WebElement chosenCategory;

    @FindBy(xpath = "//form[@class='form ember-view']/a")
    public WebElement searchBtn;

    // bonus section elements
    @FindBy(xpath = "//a[text()='careers']")
    public WebElement careersLinkBtn;

    public void goToLogin(TestInfo info) throws Exception {
        try {
            clickElement(linkToLogin);
            logger.info("Click on login button was successful.");
        } catch (Exception e) {
            logger.error("An error occurred while clicking on login.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\errors\\" + info.getDisplayName() + ".png");
            logger.info(e);
        }
    }

    public void doLogin(TestInfo info) throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginForm();
    }

    public void choosePrice(TestInfo info) throws Exception {
        try {
            clickElement(dropDownPrice);
            logger.info("Click on the dropdown price was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on dropdown price");
            logger.info(e);
        }
        try {
            clickElement(chosenPrice);
            logger.info("Click on the price price was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on price within price list");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\errors\\" + info.getDisplayName() + ".png");
            logger.info(e);
        }
        try {
            clickElement(dropDownArea);
            logger.info("Click on the area/district dropdown was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on area/district within dropdown list");
            logger.info(e);
        }
        try {
            clickElement(chosenArea);
            logger.info("Click on the area/district within list of areas was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on area/district within the list");
            logger.info(e);
        }
        try {
            clickElement(dropDownCategory);
            logger.info("Click on the category dropdown was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on category dropdown");
            logger.info(e);
        }
        try {
            clickElement(chosenCategory);
            logger.info("Click on the chosen category within dropdown was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on chosen category");
            logger.info(e);
        }
        try {
            scrollToElement(searchBtn);
            clickElement(searchBtn);
            logger.info("Click on the search button was successful.");
        } catch (Exception e) {
            logger.error(" An error occurred while clicking on search button");
            logger.info(e);
        }
    }

    public void bonus(TestInfo info) throws Exception {

        try {
            scrollToElement(careersLinkBtn);
            logger.info("Scrolling to career link successful.");
        } catch (Exception e) {
            logger.error("An error occurred while scrolling to career link.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\bonusImages\\" + info.getDisplayName() + ".png");
            logger.info(e);
        }
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\bonusImages\\" + info.getDisplayName() + ".png");
        try {
            clickElement(careersLinkBtn);
            logger.info("Click on career link successful.");
        } catch (Exception e) {
            logger.error("An error occurred while clicking on the career link.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\bonusImages\\" + info.getDisplayName() + ".png");
            logger.info(e);
        }
        switchToNewWindow();
    }


}
