package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static String AppiumURL="http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone 13 TEST");
        capabilities.setCapability("platformVersion","15.5");
        capabilities.setCapability("app","/Users/nikitabirukov/Desktop/JavaAppiumAutomationFromGit/JavaAppiumAutomation/apks/Wikipedia.app");

        driver = new IOSDriver(new URL(AppiumURL),capabilities);
    }


    protected void tearDrown() throws Exception {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
    }

}
