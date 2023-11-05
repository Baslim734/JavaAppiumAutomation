package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "org.wikipedia:id/search_container",
            START_APP_SKIP_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_BY_TEXT_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            CLOSE_SEARCH_BUTTON = "org.wikipedia:id/search_close_btn",
            PAGE_LIST_ID = "org.wikipedia:id/page_list_item_title";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementPresent(By.id(SEARCH_INIT_ELEMENT), "Input element not on the screen");
        this.waitForElementAndClick(By.id(SEARCH_INIT_ELEMENT), "Cannot find 'Search Wikipedia' input");
    }

    public void closeSearch() {
        this.waitForElementPresent(By.id(CLOSE_SEARCH_BUTTON), "Close button not on the screen");
        this.waitForElementAndClick(By.id(CLOSE_SEARCH_BUTTON), "Cannot find close button");
    }

    public void startAppSkipButton() {
        this.waitForElementPresent(By.id(START_APP_SKIP_BUTTON), "Skip button not on the screen");
        this.waitForElementAndClick(By.id(START_APP_SKIP_BUTTON), "Cannot find skip button");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "Cannot find and type into search input");
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find result with substring: " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot click result with substring: " + substring);
    }

    public void clickBySearchResult(Integer newInt) {
        this.waitForElementsAndClickWithText(By.id(PAGE_LIST_ID), newInt, "There is no result with index: " + newInt);
    }

    public void waitForSearchResultDissapear(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(By.xpath(search_result_xpath), "Found result on the screen with substring: " + substring, 10);
    }

    public void compareSearchInputedText(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementHasText(By.xpath(search_result_xpath), substring, "Other text in searchline inputed, must be:" + substring);
    }

}
