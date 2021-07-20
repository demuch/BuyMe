package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import javax.rmi.CORBA.Util;

public class PurchasingPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(PurchasingPage.class);

    public PurchasingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[contains(@id,'ember') and contains(@type,'text')]")
    public WebElement receiverNameInputTxt;

    @FindBy(className = "selected-name")
    public WebElement celebratingReasonSelect;

    @FindBy(xpath = "//div[@class='dropdown']//li[4]")
    public WebElement celebrateReasonList;

    @FindBy(xpath = "//textarea")
    public WebElement celebratingText;

    //@FindBy(xpath = "//input[@type='file']")
    @FindBy(xpath = "//div[@class='mx-auto top-lr']//input")
    public WebElement fileUpload;

    @FindBy(xpath = "//div[@class='thumbnail']")
    public WebElement uploadedFileIcon;

    @FindBy(xpath = "//button[@gtm='המשך']")
    public WebElement confirm;

    public void fillGiftForm(TestInfo info) throws Exception {
        try {
            typeText(receiverNameInputTxt, "somebody");
            logger.info("Text typed successful into receiver name field.");
        }catch (Exception e) {
            logger.error("An error occurred while typing text into receiver field.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            clickElement(celebratingReasonSelect);
            logger.info("Celebrating reason dropdown clicked successful.");
        }catch (Exception e){
            logger.error("An error occurred while clicking celebrating reason dropdown.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            clickElement(celebrateReasonList);
            logger.info("Celebrating reason chose successful.");
        }catch (Exception e){
            logger.error("An error occurred while choosing celebrating reason from the list.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            typeText(celebratingText, "Happy Birthday!!!");
            logger.info("The text typed successful into celebrating text field.");
        }catch (Exception e){
            logger.error("An error occurred while typing celebrating text.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            Utils.takeSnapShot(driver,System.getProperty("user.dir")+"\\images\\picForUpload.png");
            uploadFile(fileUpload, System.getProperty("user.dir") + "\\images\\picForUpload.png");
            logger.info("The image uploaded successful.");
        }catch (Exception e){
            logger.error("An error occurred while uploading image.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
        try {
            waitForVisibility(uploadedFileIcon);
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\uploadedImagePage.png");
            clickElement(confirm);
            logger.info("Submit button clicked successful.");
        }catch (Exception e){
            logger.error("An error occurred while clicking on submit button.");
            Utils.takeSnapShot(driver, System.getProperty("user.dir")+"\\images\\errors\\"+info.getDisplayName()+".png");
            logger.info(e);
        }
    }
}
