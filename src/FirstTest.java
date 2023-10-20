import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

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

    @Test
    public void testSearchAndCancel(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input");
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Oil",
                "Cannot find search input");

        waitForElementPresent(By.xpath("//*[contains(@text,'Viscous water-insoluble liquid')]"),
                "First result was not found");
        waitForElementPresent(By.xpath("//*[contains(@text,'Oil platform')]"),
                "Second result was not found");
        waitForElementPresent(By.xpath("//*[contains(@text,'Oil spill')]"),
                "Third result was not found");
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Search Wikipedia' input");
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Viscous water-insoluble liquid')]"),
                "First result still on screen",
                5);
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Oil platform')]"),
                "Second result still on screen",
                5);
        waitForElementNotPresent(By.xpath("//*[contains(@text,'Oil spill')]"),
                "Third result still on screen",
                5);
    }

    @Test
    public void testCompareInstalledTextInField(){
        //так как android.widget.TextView не содержит своего уникального id,а завязаться на класс не самая лучшая идея
        // (так как android.widget.TextView может быть несколько на странице)
        assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"),"Search Wikipedia",
                "'Search Wikipedia' was not found in text field"
                );

    }

    @Test
    public void testCompareWordsInSearchResults(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input");
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Oil",
                "Cannot find search input");
        assertElementHasText(By.xpath("//*[contains(@text,'Oil')]"),"Oil",
                "'Oil' was not found in text field"
        );
        assertElementHasText(By.xpath("//*[contains(@text,'Oil platform')]"),"Oil platform",
                "'Oil platform' was not found in text field"
        );
        assertElementHasText(By.xpath("//*[contains(@text,'Oil spill')]"),"Oil spill",
                "'Oil spill' was not found in text field"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message){
       return waitForElementPresent(by,error_message,5);
    }
    private WebElement waitForElementAndClick(By by, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by,String value, String error_message){
        WebElement element = waitForElementPresent(by,error_message,5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message,long timeoutInSeconds){
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

    private WebElement assertElementHasText(By by,String textInElement, String error_message){
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

}
