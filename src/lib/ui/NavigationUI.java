package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            BUTTON_BACK,
            NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE,
            NAVIGATION_BAR_SAVED_BUTTON;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void backToMainPage() {
        this.waitForElementAndClick(BUTTON_BACK,
                "Cant find back button");
        this.checkForElementIsEnabled(NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE);
    }

    public void goToSavedList() {
        this.waitForElementAndClick(NAVIGATION_BAR_SAVED_BUTTON,
                "Cant find back button");
        this.checkForElementIsEnabled(NAVIGATION_BAR_SAVED_BUTTON);
    }

}
