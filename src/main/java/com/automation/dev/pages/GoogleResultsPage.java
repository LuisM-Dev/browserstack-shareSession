package com.automation.dev.pages;

import com.automation.dev.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleResultsPage extends BasePage {


    @FindAll(@FindBy(tagName = "h3"))
    private List<WebElement> searchResults;

    public boolean checkPageResults(String result){
        for(int i = 0; i < searchResults.size(); i++){
            if(searchResults.get(i).getText().contains(result)){
                return true;
            }
        }
        return false;
    }
}
