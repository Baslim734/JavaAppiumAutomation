import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class BaseMetods {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.1.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/nikitabirukov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }
    @After
    public void tearDrown()
    {
        driver.quit();
    }

    WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by,error_message,5);
    }
    WebElement waitForElementAndClick(By by, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.click();
        return element;
    }

    WebElement waitForElementWithTextAndClick(By by,String text, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        String tittle_element = element.getAttribute("text");
        Assert.assertEquals(
                error_message,
                text,
                tittle_element
        );
        element.click();
        return element;
    }

    WebElement waitForElementAndSendKeys(By by, String value, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.sendKeys(value);
        return element;
    }

    boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private  WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    WebElement assertElementHasText(By by, String textInElement, String error_message){
        WebElement tittle_element = waitForElementPresent(
                by, error_message
        );
        String article_tittle = tittle_element.getAttribute("text");

        Assert.assertEquals(
                error_message,
                textInElement,
                article_tittle
        );
        return tittle_element;
    }
    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width/2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message,int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).isEmpty()){

            if(already_swiped > max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
    protected void waitForElementsAndClick(By by, int elementIndex, String errorMessage) {
        waitForElementPresent(by, errorMessage);
        List<WebElement> elements = driver.findElements(by);
        if (elementIndex < elements.size()) {
            WebElement element = elements.get(elementIndex);
            element.click();
        } else {
            Assert.fail("Cannout find element with index =  " + elementIndex);
        }
    }


}
