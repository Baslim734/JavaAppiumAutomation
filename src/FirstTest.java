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

public class FirstTest extends BaseMetods{

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

}
