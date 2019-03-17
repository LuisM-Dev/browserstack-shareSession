package com.automation.dev.base;

import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected static Driver driver;

    protected BasePage() {
        driver = Driver.getInstance();
        PageFactory.initElements(driver.getDriver(),this);
    }
}
