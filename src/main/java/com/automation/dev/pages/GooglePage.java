package com.automation.dev.pages;

import com.automation.dev.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInput;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public void insertText(String text){
        driver.inputData(searchInput, "Selenium");
    }

    public GoogleResultsPage search(){
        driver.clickElement(searchButton);
        return new GoogleResultsPage();
    }

}
