package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            ARTICLE_IN_LIST = "id:org.wikipedia:id/page_list_item_title",
            FOLDER_LIST_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']",
            ARTICLE_TITTLE_TPL = "xpath://android.view.View[@content-desc='{SUBSTRING}']";


    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForArticleToAppearByTittle(String article_tittle){
        String articleXpath= getTittle(article_tittle);
        this.waitForElementPresent(articleXpath,"Cannot find article: " + article_tittle,10);
    }

    public void waitForArticleToDisappearByTittle(String article_tittle){
        String articleXpath= getTittle(article_tittle);
        this.waitForElementPresent(articleXpath,"Found article: " + article_tittle,10);
    }

    private static String getFolderList(String substring) {
        return FOLDER_LIST_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTittle(String substring) {
        return ARTICLE_TITTLE_TPL.replace("{SUBSTRING}", substring);
    }

    public void deleteFirstArticle(String article_list_name) {
        this.swipeUpQuick();
        String folder_name = getFolderList(article_list_name);
        this.waitForElementAndClick(folder_name,
                "Cant find " + article_list_name + " list");
        this.swipeElementToLeft(ARTICLE_IN_LIST,
                "Not found saved articles");
    }

    public void checkArticleTitle(String article_list_name) {
        this.waitForElementWithTextAndClick(ARTICLE_IN_LIST,
                article_list_name,
                "Cant find article with name: " + article_list_name);
        String provided_tittle = getTittle(article_list_name);
        this.waitForElementPresent(provided_tittle,
                "Title does not match");

    }
}
