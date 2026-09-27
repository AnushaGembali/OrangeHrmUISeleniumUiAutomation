package com.orangehrm.utils;

import com.orangehrm.exceptions.BrowserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserUtil {
    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(BrowserUtil.class);

    public BrowserUtil(WebDriver driver){
        this.driver = driver;
    }

    public String doGetTitle() {
        String title = driver.getTitle();
        return title;
    }

    public String doGetPageTitleContains(String titleContains, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean doesTitleContains = wait.until(ExpectedConditions.titleContains(titleContains));
        return doesTitleContains ? doGetTitle() : "";
    }

    public String doGetPageTitleIs(String titleText, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean isTitlePresent = wait.until(ExpectedConditions.titleIs(titleText));
        return isTitlePresent ? doGetTitle() : "";
    }

    public String doGetPageURL(){
        String pageURL = driver.getCurrentUrl();
        return pageURL;
    }

    public String doGetPageURLContains(String urlContainsText, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean doesUrlContains = wait.until(ExpectedConditions.urlContains(urlContainsText));
        return doesUrlContains ? doGetPageURL() : "";
    }

    public String doGetPageURLIs(String urlIs, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        boolean isUrlPresent = wait.until(ExpectedConditions.urlToBe(urlIs));
        return isUrlPresent ? doGetPageURL() : "";
    }

    public void doNavigateBack(){
        driver.navigate().back();
    }

    public void doNavigateForward(){
        driver.navigate().forward();
    }

    public void doPageRefresh(){
        driver.navigate().refresh();
    }

    public void doCreateNewWindow(){
       driver = driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public void doCreateNewTab(){
        driver = driver.switchTo().newWindow(WindowType.TAB);
    }


    public void launchURL(String url) {
        if(url.isEmpty()){
            log.error("Url is null or Empty");
            System.out.println("Url is null or Empty");
            throw new BrowserException("Url is null or Empty");
        }
        if(url.indexOf("http") != 0){
            log.error("Url does not contain protocol" + url);
            System.out.println("Url does not contain protocol" + url);
            throw new BrowserException("Url does not contain protocol" + url);
        }
        driver.get(url);
    }
}
