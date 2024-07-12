package com.picsart.pages;

import com.picsart.constants.PagePaths;
import com.picsart.helpers.WaitHelper;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

@Slf4j
public class SearchPage extends BasePage<SearchPage> {

    private final String licenseCheckboxes = "(//*[@data-testid='checkbox-item-check'])[%d]/parent::span";


    @FindBy(css = "[data-testid='search-header-filter']")
    private WebElement filterButton;

    @FindBy(css = "[data-testid='com.picsart.social.search']")
    private WebElement iframe;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesBtn;

    @FindBy(xpath = "//label[text()='Personal']")
    private WebElement personalCheckbox;

    @FindBy(css = "[data-testid='like-button-root']")
    private WebElement likeButton;

    @FindBy(css = "[data-testid='save-button-root']")
    private WebElement saveButton;

    @FindBy(css = "[data-testid='registration-modal-container']")
    private WebElement loginPopup;

    @FindBy(css = "[data-testid='modal-close-icon']")
    private WebElement loginPopupCloseIcon;

    @FindBy(css = "[data-testid='search-filter-header-clear']")
    private WebElement clearFilterButton;

    @FindBy(css = "[data-automation='search-item-premium']")
    private List<WebElement> plusItems;

    @FindBy(css = "[data-automation='search-item-fte']")
    private List<WebElement> personLItems;

    @FindBy(css = "[data-testid='try-now-button-root']")
    private WebElement tryNowButtons;

    @FindBy(css = "[data-testid='regenerate-button-root']")
    private WebElement regenerateButtons;

    @FindBy(css = "[data-testid='base-card-link']")
    private List<WebElement> allSearchItems;


    public SearchPage(WebDriver driver) {
        super(driver, PagePaths.IMAGES);
    }

    public void clickFilterButton() {
        click(filterButton);
    }

    public void clickClearButton() {
        click(clearFilterButton);
    }

    public void clickPersonalCheckbox() {
        click(By.xpath(String.format(licenseCheckboxes, 3)));
    }

    public void clickCommercialCheckbox() {
        click(By.xpath(String.format(licenseCheckboxes, 2)));
    }

    public void clickLikeButton() {
        click(likeButton);
    }

    public void clickLoginPopupCloseButton() {
        click(loginPopupCloseIcon);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void clickTryNowButton() {
        hoverPlusItems();
        click(tryNowButtons);
    }

    public boolean isLikeButtonDisplayed() {
        WaitHelper.waitForVisibility(getDriver(), allSearchItems.get(0));
        for (WebElement element : allSearchItems
        ) {
            hoverOnElement(element);
            if (isElementExists(getDriver()
                    .findElement(By.xpath("(//button[contains(@id, 'like_button_item')])[1]")))) {
                return true;
            }
        }
        return false;
    }


    public boolean isTryNowDisplayed() {
        WaitHelper.waitForVisibility(getDriver(), allSearchItems.get(0));
        for (WebElement element : allSearchItems
        ) {
            hoverOnElement(element);
            if (isElementExists(getDriver()
                    .findElement(By.xpath("(//button[contains(@id, 'try_now_button_item')])[1]")))) {
                return true;
            }
        }
        return false;
    }

    public void hoverPlusItems() {
        WaitHelper.waitForVisibility(getDriver(), plusItems.get(0));
        hoverOnElement(plusItems.get(0));
    }

    public boolean isLikeBtnVisible() {
        return isElementExists(likeButton);
    }

    public boolean isSaveBtnVisible() {
        return isElementExists(saveButton);
    }

    public boolean isTryNowBtnVisible() {
        return isElementExists(tryNowButtons);
    }

    public boolean isLoginPopupVisible() {
        WaitHelper.waitForVisibility(getDriver(), loginPopup);
        return isElementExists(loginPopup);
    }

    public boolean isRegenerateBtnVisible() {
        return isElementExists(regenerateButtons);
    }


    public boolean isFilterSectionVisible() {
        return filterButton.getAttribute("data-automation").contains("close");
    }


    public boolean isPlusItemsVisible() {
        try {
            WaitHelper.waitForVisibility(getDriver(), plusItems.get(0));
            for (WebElement element : plusItems
            ) {
                if (isElementExists(element)) {
                    return true;
                }
            }
        } catch (IndexOutOfBoundsException | TimeoutException ignored) {
        }
        return false;
    }

    public void switchToFrame() {
        WaitHelper.waitForVisibility(getDriver(), iframe);
        getDriver().switchTo().frame(iframe);
    }

    public void switchToDefaultFrame() {
        getDriver().switchTo().defaultContent();
    }

    public void clickOnAcceptCookieBtn() {
        try {

            click(acceptCookiesBtn, Duration.ofSeconds(5));
        } catch (NoSuchElementException ignored) {
            log.info("accept cookies is not dispplayed : " + acceptCookiesBtn.getText() + "  " + ignored.getMessage());
        }
    }


}
