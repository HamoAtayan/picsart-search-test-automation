package com.picsart.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


public interface ComponentHelper {

    WebDriver getDriver();

    default boolean isElementExists(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    default void hoverOnElement(WebElement element) {
        Actions action = new Actions(getDriver());
        action.moveToElement(element).perform();
    }



    default void click(WebElement element) {
        WaitHelper.waitForElementToBeClickable(getDriver(), element);
        element.click();
    }

    default void click(WebElement element, Duration duration) {
        WaitHelper.waitForElementToBeClickable(getDriver(), element, duration);
        element.click();
    }

    default void click(By element) {
        WaitHelper.waitForElementToBeClickable(getDriver(), element);
        getDriver().findElement(element).click();
    }
}
