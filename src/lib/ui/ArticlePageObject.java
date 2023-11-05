package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ArticlePageObject extends MainPageObject {

    private static final String
            RESULT_ARTICLE_TITTLE_ID = "org.wikipedia:id/page_list_item_title",
            BUTTON_SAVE_ID = "org.wikipedia:id/page_save",
            BUTTON_ADD_TO_LIST = "//*[contains(@text,'Add to list')]",
            INPUT_LIST_NAME_STRING = "//*[contains(@text,'Name of this list')]",
            BUTTON_OK = "//*[contains(@text,'OK')]",
            SEARCH_BY_TEXT_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            BUTTON_BACK = "//android.widget.ImageButton[@content-desc='Navigate up']",
            ARTICLE_TITTLE_ID_TPL = "//android.view.View[contains(@content-desc,'{SUBSTRING}')]";

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
        this.waitForElementAndClick(By.id(BUTTON_SAVE_ID), "Cant find save button");
        this.waitForElementAndClick(By.xpath(BUTTON_ADD_TO_LIST),
                "Cant find add to list button");
        this.waitForElementAndSendKeys(
                By.xpath(INPUT_LIST_NAME_STRING),
                article_list_name,
                "Cannot find search input");
        this.waitForElementAndClick(By.xpath(BUTTON_OK)
                , "Cant find OK button");
    }

    public void addArticleToExistingList(String article_list_name) {
        this.waitForElementAndClick(By.id(BUTTON_SAVE_ID),
                "Cant find save button");
        this.waitForElementAndClick(By.xpath(BUTTON_ADD_TO_LIST),
                "Cant find add to list button");
        String provided_list = getElementByText(article_list_name);
        this.waitForElementAndClick(By.xpath(provided_list),
                "Cant find list: " + article_list_name);
    }

    private static String getElementByText(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getTittle(String substring) {
        return ARTICLE_TITTLE_ID_TPL.replace("{SUBSTRING}", substring);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(BUTTON_BACK),
                "Cant find back button");
    }

    public void checkTittle(String article_tittle) {
        String provided_tittle = getTittle(article_tittle);
        this.assertElementPresent(By.xpath(provided_tittle),
                "Not found element tittle");
    }

}
