package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lib.Platform;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iOSNavigationUI;
import org.openqa.selenium.By;

import java.util.List;

import static org.junit.Assert.assertTrue;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            RESULT_ARTICLE_TITTLE_ID,
            BUTTON_SAVE_ID,
            BUTTON_ADD_TO_LIST,
            INPUT_LIST_NAME_STRING,
            BUTTON_CREATE_NEW_LIST,
            BUTTON_OK,
            SEARCH_BY_TEXT_TPL,
            BUTTON_BACK,
            ARTICLE_TITTLE_ID_TPL;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void expectTheTextInEachResult(String textInElement) {
        List<MobileElement> elements = driver.findElements(By.id(RESULT_ARTICLE_TITTLE_ID));
        for (MobileElement element : elements) {
            String text = element.getText();
            assertTrue("Expected text in tittle:" + textInElement + "\nBut found: " + text, text.contains(textInElement));
        }
    }

    public void createNewListAndAddArticle(String article_list_name) {
        this.waitForElementAndClick(BUTTON_SAVE_ID, "Cant find save button");
        this.waitForElementAndClick(BUTTON_ADD_TO_LIST,
                "Cant find add to list button");
        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(BUTTON_CREATE_NEW_LIST,
                    "Cant find create new list button");;
        }
        this.waitForElementAndSendKeys(
                INPUT_LIST_NAME_STRING,
                article_list_name,
                "Cannot find search input");
        this.waitForElementAndClick(BUTTON_OK
                , "Cant find OK button");
    }

    public void addArticleToExistingList(String article_list_name) {
        this.waitForElementAndClick(BUTTON_SAVE_ID,
                "Cant find save button");
        this.waitForElementAndClick(BUTTON_ADD_TO_LIST,
                "Cant find add to list button");
        String provided_list = getElementByText(article_list_name);
        this.waitForElementAndClick(provided_list,
                "Cant find list: " + article_list_name);
    }

    private static String getElementByText(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTittle(String substring) {
        return ARTICLE_TITTLE_ID_TPL.replace("{SUBSTRING}", substring);
    }

    public void closeArticle() {
        this.waitForElementAndClick(BUTTON_BACK,
                "Cant find back button");
    }

    public void checkTittle(String article_tittle) {
        String provided_tittle = getTittle(article_tittle);
        this.assertElementPresent(provided_tittle,
                "Not found element tittle, by PATH: " + provided_tittle);
    }

}
