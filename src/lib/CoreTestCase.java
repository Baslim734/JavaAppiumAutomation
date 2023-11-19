package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverByPlatformEnv(new URL(AppiumURL), capabilities);
    }


    protected void tearDrown() throws Exception {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)) {
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.1.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/nikitabirukov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

        } else if (platform.equals(PLATFORM_IOS)) {
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 13 TEST");
            capabilities.setCapability("platformVersion", "15.5");
            capabilities.setCapability("app", "/Users/nikitabirukov/Desktop/JavaAppiumAutomationFromGit/JavaAppiumAutomation/apks/Wikipedia.app");
        } else {
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }
        return capabilities;
    }

    private AppiumDriver getDriverByPlatformEnv(URL url, DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(url, capabilities);
        } else if (platform.equals(PLATFORM_IOS)) {
            driver = new IOSDriver(url, capabilities);
        } else {
            throw new Exception("Cannot get DRIVER type by platform from env variable. Platform value " + platform);
        }
        return driver;
    }

}
