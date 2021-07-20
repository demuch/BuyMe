package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressPage extends BasePage{

    public AddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='circle-area']")
    public WebElement smsBtn;

    @FindBy(id = "sms")
    public WebElement cellularNumberTxt;



    public void submitAddress(){
        clickElement(smsBtn);
        typeText(cellularNumberTxt, "12346789");

    }
}
