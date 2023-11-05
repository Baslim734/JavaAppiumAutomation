package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    private SearchPageObject SearchPageObject;
    private ArticlePageObject ArticlePageObject;
    private NavigationUI NavigationUI;
    private MyListsPageObject MylistsPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        SearchPageObject = new SearchPageObject(driver);
    }

    @Test
    public void testSavingTwoArticles() {
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI = new NavigationUI(driver);
        MylistsPageObject = new MyListsPageObject(driver);

        SearchPageObject.startAppSkipButton();


        String article_tittle = "Cinnamon";
        String article_list_name = article_tittle + " list";
        String second_article_tittle = "Cinnamon (desktop environment)";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_tittle);
        SearchPageObject.clickBySearchResult(0);
        ArticlePageObject.createNewListAndAddArticle(article_list_name);
        ArticlePageObject.closeArticle();
        SearchPageObject.clickBySearchResult(1);
        ArticlePageObject.addArticleToExistingList(article_list_name);
        ArticlePageObject.closeArticle();
        NavigationUI.backToMainPage();
        NavigationUI.goToSavedList();
        MylistsPageObject.deleteFirstArticle(article_list_name);
        MylistsPageObject.checkArticleTitle(second_article_tittle);

    }

    @Test
    public void testCheckTittle() {
        SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject = new ArticlePageObject(driver);
        NavigationUI = new NavigationUI(driver);
        MylistsPageObject = new MyListsPageObject(driver);

        String article_tittle = "Cinnamon";
        String second_article_tittle = "Cinnamon (desktop environment)";

        SearchPageObject.startAppSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(article_tittle);
        SearchPageObject.clickBySearchResult(0);
        ArticlePageObject.checkTittle(second_article_tittle);

    }

}