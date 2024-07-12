package com.picsart.helpers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class WaitHelper {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    public static void waitForVisibility(WebDriver driver, WebElement element) {
        waitForVisibility(driver, element, TIMEOUT);
    }

    public static void waitForVisibility(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        waitForElementToBeClickable(driver, element, TIMEOUT);
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        waitForElementToBeClickable(driver, locator, TIMEOUT);
    }

    public static void waitForInvisibility(WebDriver driver, WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitUntilPageLoads(WebDriver driver) {
        waitUntilJSReady(driver);
        waitUntilJQueryReady(driver);
        waitUntilAngularReady(driver);
        waitUntilAngular5Ready(driver);
    }

    private static void waitUntilJQueryReady(WebDriver driver) {
        Boolean jQueryDefined = (Boolean) ((JavascriptExecutor) driver).executeScript("return typeof jQuery != 'undefined'");
        if (Boolean.TRUE.equals(jQueryDefined)) {
            try {
                boolean jqueryReady = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
                if (!Boolean.TRUE.equals(jqueryReady)) {
                    WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
                    ExpectedCondition<Boolean> jQueryLoad = webDriver ->
                            ((Long) ((JavascriptExecutor) driver)
                                    .executeScript("return jQuery.active") == 0);
                    wait.until(jQueryLoad);
                }
            } catch (WebDriverException ex) {
                log.info(ex.getMessage());
            }
        }
    }

    private static void waitUntilJSReady(WebDriver driver) {
        try {
            String readyState = "return document.readyState";
            boolean jsReady = ((JavascriptExecutor) driver).executeScript(readyState).toString().equals("complete");
            if (!jsReady) {
                WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
                ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) driver)
                        .executeScript(readyState).toString().equals("complete");
                wait.until(jsLoad);
            }
        } catch (WebDriverException ex) {
            log.info(ex.getMessage());
        }
    }

    private static void waitUntilAngularReady(WebDriver driver) {
        try {
            Boolean angularUnDefined = (Boolean) ((JavascriptExecutor) driver).executeScript("return window.angular === undefined");
            if (!Boolean.TRUE.equals(angularUnDefined)) {
                Boolean angularInjectorUnDefined = (Boolean) ((JavascriptExecutor) driver).executeScript("return angular.element(document).injector() === undefined");
                if (!Boolean.TRUE.equals(angularInjectorUnDefined)) {
                    String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
                    angularLoads(driver, angularReadyScript);
                }
            }
        } catch (WebDriverException ex) {
            log.info(ex.getMessage());
        }
    }

    private static void waitUntilAngular5Ready(WebDriver driver) {
        try {
            Object angular5Check = ((JavascriptExecutor) driver).executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");
            if (angular5Check != null) {
                String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
                Boolean angularPageLoaded = (Boolean) ((JavascriptExecutor) driver).executeScript(angularReadyScript);
                if (!Boolean.TRUE.equals(angularPageLoaded)) {
                    angularLoads(driver, angularReadyScript);
                }
            }
        } catch (WebDriverException ex) {
            log.info(ex.getMessage());
        }
    }

    private static void angularLoads(WebDriver driver, String angularReadyScript) {
        try {
            Boolean angularReady = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(angularReadyScript).toString());
            if (!Boolean.TRUE.equals(angularReady)) {
                WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
                wait.until(webDriver -> Boolean.valueOf(((JavascriptExecutor) driver)
                        .executeScript(angularReadyScript).toString()));
            }
        } catch (WebDriverException ex) {
            log.info(ex.getMessage());
        }
    }

    private WaitHelper() {
        throw new UnsupportedOperationException("Not allowed to crate an instance of WaitHelper class");
    }
}
