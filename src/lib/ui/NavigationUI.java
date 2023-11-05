package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            BUTTON_BACK = "//android.widget.ImageButton[@content-desc='Navigate up']",
            NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE = "org.wikipedia:id/navigation_bar_item_active_indicator_view",
            NAVIGATION_BAR_SAVED_BUTTON = "org.wikipedia:id/nav_tab_reading_lists";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void backToMainPage() {
        this.waitForElementAndClick(By.xpath(BUTTON_BACK),
                "Cant find back button");
        this.checkForElementIsEnabled(By.id(NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE));
    }

    public void goToSavedList() {
        this.waitForElementAndClick(By.id(NAVIGATION_BAR_SAVED_BUTTON),
                "Cant find back button");
        this.checkForElementIsEnabled(By.id(NAVIGATION_BAR_SAVED_BUTTON));
    }

}
