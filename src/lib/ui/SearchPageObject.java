package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;


public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container",
            START_APP_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button",
            SEARCH_INPUT = "id:org.wikipedia:id/search_src_text",
            SEARCH_BY_TEXT_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]",
            CLOSE_SEARCH_BUTTON = "id:org.wikipedia:id/search_close_btn",
            PAGE_LIST_ID = "id:org.wikipedia:id/page_list_item_title",
            TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL = "xpath://android.view.ViewGroup[.//android.widget.TextView[contains(@text, '{SUBSTRING_TITTLE}')] and .//android.widget.TextView[contains(@text, '{SUBSTRING_DESC}')]]";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_BY_TEXT_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getLocatorByTittleAndDescription(String tittle, String description) {
        String newXpath = TITLE_AND_DESCRIPTION_SEARCH_RESULT_TPL.replace("{SUBSTRING_TITTLE}", tittle);
        return newXpath.replace("{SUBSTRING_DESC}", description);
    }

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Input element not on the screen");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find 'Search Wikipedia' input");
    }

    public void closeSearch() {
        this.waitForElementPresent(CLOSE_SEARCH_BUTTON, "Close button not on the screen");
        this.waitForElementAndClick(CLOSE_SEARCH_BUTTON, "Cannot find close button");
    }

    public void startAppSkipButton() {
        this.waitForElementPresent(START_APP_SKIP_BUTTON, "Skip button not on the screen");
        this.waitForElementAndClick(START_APP_SKIP_BUTTON, "Cannot find skip button");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input");
        driver.hideKeyboard();
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find result with substring: " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot click result with substring: " + substring);
    }

    public void clickBySearchResult(Integer newInt) {
        this.waitForElementsAndClickWithText(PAGE_LIST_ID, newInt, "There is no result with index: " + newInt);
    }

    public void waitForSearchResultDissapear(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(search_result_xpath, "Found result on the screen with substring: " + substring, 10);
    }

    public void compareSearchInputedText(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementHasText(search_result_xpath, substring, "Other text in searchline inputed, must be:" + substring);
    }

    public void waitForElementByTittleAndDescription(String tittle, String description) {
        String newXpath = getLocatorByTittleAndDescription(tittle, description);
        this.waitForElementPresent(newXpath, "Not found result with title: " + tittle + " and description: " + description, 10);
    }

    public void findThreeSearchResultByTittleAndDescription(String tittle, String description) {
        waitForElementByTittleAndDescription(tittle, description);
        String newXpath = getLocatorByTittleAndDescription(tittle, description);
        List<MobileElement> elements = driver.findElements(By.xpath(newXpath));
        assertTrue("Found three or more search result:", elements.size() >= 3);
        assertFalse("Found least then three search result: " + elements.size(), elements.size() < 3);
    }

}

