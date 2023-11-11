package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;


public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassThroughWelcome() throws InterruptedException {
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);
        Thread.sleep(5000);
        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForSearchOnManyLanguages();
        WelcomePage.clickNextButton();

        WelcomePage.waitForHelpToImproveApp();
        WelcomePage.clickStartButton();
    }
}
