package com.picsart.tests.ui_tests;


import com.picsart.helpers.DriverHelper;
import com.picsart.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseUiTest extends BaseTest {
    protected WebDriver driver;

    @BeforeTest
    @Parameters({"width", "height"})
    public void setUp(int width, int height) {
        DriverHelper driverHelper = DriverHelper.getInstance();
        driver = driverHelper.initDriver(width, height);
    }

    @AfterTest
    protected void tearDown() {
        if (driver != null)
            driver.quit();
        }
    }