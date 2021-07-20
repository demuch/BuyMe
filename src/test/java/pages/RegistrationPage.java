package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;


public class RegistrationPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='שם פרטי']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='מייל']")
    public WebElement email;

    @FindBy(xpath = "//input[@placeholder='סיסמה']")
    public WebElement password;

    @FindBy(xpath = "//input[@placeholder='אימות סיסמה']")
    public WebElement validationPassword;

    @FindBy(id = "ember1443")
    public WebElement subscribeBtn;

    @FindBy(id = "times")
    public WebElement closeWindowBtn;


    // fills the fields in subscribe form, takes the screenshot and clicks on submit/close.
    public void doSubscribe(TestInfo info) throws Exception {
        try {
            typeText(firstName, "username");
            logger.info("The username field filled successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while filling username field");
            logger.info(e);
        }
        try {
            typeText(email, "mail@mail.com");
            logger.info("The email field filled successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while filling email field");
            logger.info(e);
        }
        try {
            typeText(password, "password123");
            logger.info("The password field filled successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while filling password field");
            logger.info(e);
        }
        try {
            typeText(validationPassword, "password123");
            logger.info("The password verification field filled successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while filling password verification field");
            logger.info(e);
        }
        //clickElement(subscribeBtn);
        Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\" + info.getDisplayName() + ".png");
        clickElement(closeWindowBtn);
    }
}
