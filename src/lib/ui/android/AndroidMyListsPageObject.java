package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
            ARTICLE_IN_LIST = "id:org.wikipedia:id/page_list_item_title";
            FOLDER_LIST_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
            ARTICLE_TITTLE_TPL = "xpath://android.view.View[@content-desc='{SUBSTRING}']";}



    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

}
