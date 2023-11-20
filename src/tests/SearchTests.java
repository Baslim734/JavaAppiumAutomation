package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private lib.ui.ArticlePageObject ArticlePageObject;

    protected void setUp() throws Exception {
        super.setUp();
        SearchPageObject = SearchPageObjectFactory.get(driver);
    }

    @Test
    public void testSearch() {
        SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSearchAndCancel() {
        SearchPageObject = SearchPageObjectFactory.get(driver);

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
        SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.compareSearchInputedText("Search Wikipedia");

    }

    @Test
    public void testCompareWordsInSearchResults() {
        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Oil");
        ArticlePageObject.expectTheTextInEachResult("Oil");
    }

    @Test
    public void testCheckResultByTittleAndDescrption(){

        SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Electricity");

        SearchPageObject.findThreeSearchResultByTittleAndDescription("Electricity","electric");

    }
}
