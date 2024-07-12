package com.picsart.pages;


import com.picsart.constants.PagePaths;
import com.picsart.helpers.ComponentHelper;
import com.picsart.helpers.WaitHelper;
import com.picsart.utils.Config;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.slf4j.Logger;

@Slf4j
public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> implements ComponentHelper {

    private final WebDriver driver;
    private final String serverUrl;
    private final String pagePath;

    private Config config;

    @FindBy(xpath = "//a[@href='/insights']")
    protected WebElement insightsBtn;

    @FindBy(id = "collections")
    private WebElement collectionsBtn;

    private String dropdownValue = "(//div[contains(@id,'react-select')])[%d]";

    public void selectDropDownValueByOrder(int order) {
        WaitHelper.waitForElementToBeClickable(getDriver(), By.xpath(String.format(dropdownValue, order)));
        getDriver().findElement(By.xpath(String.format(dropdownValue, order))).click();

    }

    protected BasePage(WebDriver driver, PagePaths pagePaths) {
        this.driver = driver;
        this.serverUrl = getUrl();
        this.pagePath = pagePaths.getPath();

        PageFactory.initElements(driver, this);
    }

    public Logger getLog() {
        return log;
    }

    public WebDriver getDriver() {
        return driver;
    }


    @Override
    protected void load() {
        driver.get(serverUrl + pagePath);
        WaitHelper.waitUntilPageLoads(driver);
    }


    @Override
    protected void isLoaded() {
        assert isOnPage();
    }

    public boolean isOnPage() {
        return getCurrentUrl().endsWith(pagePath);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getUrl() {
        if (config == null) {
            config = new Config("app.properties");
        }
        return config.get("url");
    }


    public String getExpectedUrl() {
        return String.format("%s%s", serverUrl, pagePath);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public String getPagePath() {
        return pagePath;
    }
}
