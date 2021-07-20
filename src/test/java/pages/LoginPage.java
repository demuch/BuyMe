package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.Test_Registration;
import utils.Utils;

import java.io.IOException;

public class LoginPage extends BasePage{

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='מייל']")
    public WebElement username;

    @FindBy(xpath = "//input[@placeholder='סיסמה']")
    public WebElement password;

    @FindBy(xpath = "//button[@gtm='כניסה ל-BUYME']")
    public WebElement loginBtn;

    @FindBy(xpath = "//span[@class='text-link theme']")
    public WebElement subscribeLink;

    // bonus section elements
    @FindBy(xpath = "//ul[@class='parsley-errors-list filled']/li")
    public WebElement errorMsg;

    public void goToRegistrationPage() {
        clickElement(subscribeLink);
    }

    public void fillLoginForm() throws IOException {
        typeText(username, Utils.getProperty("username"));
        typeText(password, Utils.getProperty("password"));
        clickElement(loginBtn);
    }

    public void doEmptyLogin(TestInfo info) throws Exception {
        try {
            typeText(username, "");
            logger.info("The username field filled successful.");
        }catch (Exception e){
            logger.error("An error occurred while filling the username field");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            typeText(password, "");
            logger.info("The password field filled successful.");
        }catch (Exception e){
            logger.error("An error occurred while filling the password field");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            clickElement(loginBtn);
            logger.info("Click on submit button successful.");
        }catch (Exception e) {
            logger.error("An error occurred while clicking on submit button.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir") + "\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
    }

    public boolean checkErrorMsg(String givenErrorMessage){
        return givenErrorMessage.equalsIgnoreCase("כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה");
    }


}
