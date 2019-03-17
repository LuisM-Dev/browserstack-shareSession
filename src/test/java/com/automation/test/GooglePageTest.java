package com.automation.test;

import com.automation.dev.base.Driver;
import com.automation.dev.pages.GooglePage;
import com.automation.dev.pages.GoogleResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GooglePageTest {

    @BeforeTest
    public void setUp(){
        Driver.getInstance();
        // Print browserStack Session URL
        System.out.println("BrowserStack SessionULR:\n"+Driver.getInstance().getBrowserStackSessionUrl());
    }

    @AfterTest
    public void tearDown(){
        Driver.getInstance().close();
    }

    @Test
    public void seachGoogleTest(){
        GooglePage googlePage = new GooglePage();
        googlePage.insertText("Selenium");
        GoogleResultsPage googleResultsPage = googlePage.search();
        Assert.assertTrue(googleResultsPage.checkPageResults("Selenium"));
    }
}
