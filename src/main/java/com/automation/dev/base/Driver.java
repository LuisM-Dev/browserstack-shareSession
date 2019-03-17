package com.automation.dev.base;

import com.automation.dev.browserstack.API;
import com.automation.dev.browserstack.SessionLog;
import com.automation.dev.utils.PropertiesUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static Driver driverInstance;
    private  WebDriver driver;
    private String browserStackSessionUrl;

    private static PropertiesUtil properties = PropertiesUtil.getInstance();

    public static final Long TIMEOUT_CONFIG = 30l;
    public static final Long IMPLICITWAIT_CONFIG = 10l;

    private Driver() {
    }

    public static Driver getInstance() {
        if ( driverInstance == null ) {
            synchronized (Driver.class){
                driverInstance = new Driver();
                driverInstance.initialization();
            }
        }
        return driverInstance;
    }

    public void initialization(){

        String browserStackDriverPath = properties.getValue("browserStackUrl");
        browserStackDriverPath = browserStackDriverPath.replace("<browserStackUsername>",properties.getValue("browserStackUsername"));
        browserStackDriverPath = browserStackDriverPath.replace("<browserStackPassword>",properties.getValue("browserStackPassword"));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", properties.getValue("browserStackBrowser"));
        caps.setCapability("browser_version", properties.getValue("browserStackBrowserVersion"));
        caps.setCapability("os", properties.getValue("browserStackOS"));
        caps.setCapability("os_version", properties.getValue("browserStackOSVersion"));
        caps.setCapability("resolution", properties.getValue("browserStackOSResolution"));
        caps.setCapability("project",properties.getValue("browserStackProject"));
        caps.setCapability("build",properties.getValue("browserStackBuild"));
        caps.setCapability("browserstack.debug","true");

        try{
            driver = new RemoteWebDriver(new URL(browserStackDriverPath), caps);
            browserStackSessionUrl = SessionLog.getSessionUrl(((RemoteWebDriver) driver).getSessionId().toString());
        } catch (IOException e){
            e.printStackTrace();
        }

        driver.manage().timeouts().pageLoadTimeout(TIMEOUT_CONFIG, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICITWAIT_CONFIG, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(properties.getValue("url"));
    }

    public WebDriver getDriver(){
        return driver;
    }

    public String getBrowserStackSessionUrl(){
        return browserStackSessionUrl;
    }

    private void waitUntilElementClickable(WebElement element) {
        new WebDriverWait(driver, IMPLICITWAIT_CONFIG).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElement(WebElement element){
        waitUntilElementClickable(element);
        element.click();
    }

    public void inputData(WebElement element, String text){
        waitUntilElementClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    public void close(){
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
        driverInstance = null;
    }
}
