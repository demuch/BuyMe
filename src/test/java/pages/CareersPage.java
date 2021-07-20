package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareersPage extends BasePage{
    public CareersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[starts-with(@class,'positionsList')]/div")
    public List<WebElement> jobList;

    public boolean checkIfJob(List<WebElement> elements){
        return elements.size()>0;
    }
}
