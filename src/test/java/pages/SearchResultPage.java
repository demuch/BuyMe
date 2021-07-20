package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BasePage{


    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class='item']")
    public WebElement searchResult;

    @FindBy(xpath = "//li[contains(@id,'ember')][2]/a/div")
    public WebElement productCard;

}
