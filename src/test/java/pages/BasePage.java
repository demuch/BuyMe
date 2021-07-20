package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestGiftSubmit;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    //General Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 8);
        js = (JavascriptExecutor) driver;
    }

    //General click (strong with wait + scroll into)
    public void clickElement(WebElement element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //General send keys + clear before
    public void typeText(WebElement element, String text) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(text);
    }

    //upload file
    public void uploadFile(WebElement element, String path) {
        element.sendKeys(path);
    }

    //Select by value from select object
    public void selectByValue(WebElement element, String value) {

        Select selection = new Select(element);
        selection.selectByValue(value);
    }

    public void deselectAll(WebElement element) {

        Select selection = new Select(element);
        selection.deselectAll();
    }


    //Switch to new window (after open it)
    public void switchToNewWindow() {
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();


        for (String h : handles) {
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }


    //Switch to new window (after open it)
    public void closeAndSwitchToMainWindow() {
        String baseHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        driver.close();

        for (String h : handles) {
            if (!h.equals(baseHandle))
                driver.switchTo().window(h);
        }
    }


    //Switch to frame by id
    public void switchToFrameByID(String frameId) {
        driver.switchTo().frame(frameId);
    }

    //Switch to frame by index
    public void switchToFrameByID(int frameIndex) {
        driver.switchTo().frame(frameIndex);
    }

    public void waitForPageLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int i = 0;
        while (i != 180) {
            String pageState = (String) js.executeScript("return document.readyState;");
            if (pageState.equals("complete")) {
                break;
            } else {
                waitLoad(1);
            }
        }
        waitLoad(2);

        i = 0;
        while (i != 180) {
            Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
            if (jsState) {
                break;
            } else {
                waitLoad(1);
            }
        }
    }

    public void waitLoad(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
