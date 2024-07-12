package com.picsart.tests.ui_tests;


import com.picsart.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseUiTest {

    @Test
    public void verifySearchTest() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.get();
        searchPage.clickOnAcceptCookieBtn();
        searchPage.switchToFrame();
        searchPage.clickFilterButton();
        Assert.assertTrue(searchPage.isFilterSectionVisible());
        searchPage.clickFilterButton();
        searchPage.clickPersonalCheckbox();
        Assert.assertFalse(searchPage.isPlusItemsVisible());
        searchPage.clickClearButton();
        searchPage.clickPersonalCheckbox();
        Assert.assertTrue(searchPage.isLikeButtonDisplayed());
        Assert.assertTrue(searchPage.isSaveBtnVisible());
        Assert.assertTrue(searchPage.isTryNowDisplayed());
        searchPage.clickLikeButton();
        searchPage.switchToDefaultFrame();
        Assert.assertTrue(searchPage.isLoginPopupVisible());
        searchPage.clickLoginPopupCloseButton();
        searchPage.switchToFrame();
        searchPage.clickClearButton();
        searchPage.clickCommercialCheckbox(); //the page crashed here

//        searchPage.hoverPlusItems();
//        Assert.assertTrue(searchPage.isTryNowBtnVisible());
//        Assert.assertFalse(searchPage.isLikeBtnVisible());
//        Assert.assertFalse(searchPage.isSaveBtnVisible());
//        searchPage.clickTryNowButton();
    }
}
