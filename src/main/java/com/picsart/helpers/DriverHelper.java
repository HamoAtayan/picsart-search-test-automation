package com.picsart.helpers;

import com.picsart.utils.Config;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class DriverHelper {
    private Config config;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static DriverHelper driverHelper;

    private DriverHelper() {
    }

    Config getConfig() {
        if (config == null) {
            config = new Config("app.properties");
        }
        return config;
    }

    public static synchronized DriverHelper getInstance() {
        if (driverHelper == null) {
            driverHelper = new DriverHelper();
        }
        return driverHelper;
    }

    public WebDriver initDriver(int width, int height) {
        if (driver.get() != null) {
            return driver.get();
        }

        String driverName = (System.getProperty("driver") != null) ? System.getProperty("driver") : "chrome";

        switch (driverName) {
            case "chrome" -> driver.set(new ChromeDriver());
            case "firefox" -> driver.set(new FirefoxDriver());
            default -> throw new IllegalArgumentException("Invalid browser specified: " + driverName);
        }

        driver.get().manage().window().setSize(new Dimension(width, height));
        return driver.get();
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
