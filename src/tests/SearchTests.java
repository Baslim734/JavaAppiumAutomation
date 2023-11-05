package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private lib.ui.ArticlePageObject ArticlePageObject;

    protected void setUp() throws Exception {
        super.setUp();
        SearchPageObject = new SearchPageObject(driver);
    }

    @Test
    public void testSearch() {
        SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSearchAndCancel() {
        SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Oil");
        SearchPageObject.waitForSearchResult("Viscous water-insoluble liquid");
        SearchPageObject.waitForSearchResult("Programme headed by the United Nations");
        SearchPageObject.waitForSearchResult("Oil platform");
        SearchPageObject.closeSearch();
        SearchPageObject.waitForSearchResultDissapear("Viscous water-insoluble liquid");
        SearchPageObject.waitForSearchResultDissapear("Programme headed by the United Nations");
        SearchPageObject.waitForSearchResultDissapear("Oil platform");
    }

    @Test
    public void testCompareInstalledTextInField() {
        SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.compareSearchInputedText("Search Wikipedia");

    }

    @Test
    public void testCompareWordsInSearchResults() {
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Oil");
        ArticlePageObject.expectTheTextInEachResult("Oil");
    }

    @Test
    public void testCheckResultByTittleAndDescrption(){

        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Electricity");

        SearchPageObject.findThreeSearchResultByTittleAndDescription("Electricity","electric");

    }
}
