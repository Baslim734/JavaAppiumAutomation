package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            BUTTON_BACK = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            NAVIGATION_BAR_EXPLORE_BUTTON_MAIN_PAGE = "id:org.wikipedia:id/navigation_bar_item_active_indicator_view",
            NAVIGATION_BAR_SAVED_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";

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
