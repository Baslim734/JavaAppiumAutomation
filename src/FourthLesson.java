import org.junit.Test;
import org.openqa.selenium.By;

public class FourthLesson extends BaseMetods {

    @Test
    public void testSavingTwoArticles(){
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
        "Cannot find skip button");
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input");

        String article_tittle = "Cinnamon";
        String article_list_name = article_tittle + " list";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                article_tittle,
                "Cannot find search input");
        String page_list_id = "org.wikipedia:id/page_list_item_title";
        String button_save_id = "org.wikipedia:id/page_save";
        waitForElementsAndClick(By.id(page_list_id),0,"Cant find first tittle");
        waitForElementAndClick(By.id(button_save_id),"Cant find save button");
        waitForElementAndClick(By.xpath("//*[contains(@text,'Add to list')]"),
                "Cant find add to list button");
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Name of this list')]"),
                article_list_name,
                "Cannot find search input");
        waitForElementAndClick(By.xpath("//*[contains(@text,'OK')]")
                ,"Cant find OK button");
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find back button");
        waitForElementsAndClick(By.id(page_list_id),1,
                "Cant find second tittle");
        String second_article_tittle = "Cinnamon (desktop environment)";
        waitForElementAndClick(By.id(button_save_id),
                "Cant find save button");
        waitForElementAndClick(By.xpath("//*[contains(@text,'Add to list')]"),
                "Cant find add to list button");
        waitForElementAndClick(By.xpath("//*[contains(@text,'" + article_list_name + "')]"),
                "Cant find new created list button");
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find back button");
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cant find back button");
        waitForElementAndClick(By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cant find saved button");
        swipeUpQuick();
        waitForElementAndClick(By.xpath("//android.widget.TextView[@text='"+ article_list_name +"']"),
                "Cant find cinnamon list");
        swipeElementToLeft(By.id(page_list_id),
                "Not found saved pages");
        waitForElementWithTextAndClick(By.id(page_list_id),
                second_article_tittle,
                "Second saved page not found");
        waitForElementPresent(By.xpath("//android.view.View[@content-desc='"+ second_article_tittle +"']"),
                "Title does not match");

    }

    @Test
    public void testCheckTittle(){
        waitForElementAndClick(By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find skip button");
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input");

        String article_tittle = "Cinnamon";
        String second_article_tittle = "Cinnamon (desktop environment)";
        String page_list_id = "org.wikipedia:id/page_list_item_title";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                article_tittle,
                "Cannot find search input");
        waitForElementsAndClick(By.id(page_list_id),1,
                "Cant find second tittle");
        assertElementPresent(By.xpath("//android.view.View[@content-desc='"+ second_article_tittle +"']"),
                "Not found element tittle");

    }
}
