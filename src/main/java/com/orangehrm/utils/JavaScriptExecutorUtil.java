package com.orangehrm.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class JavaScriptExecutorUtil {
    private JavascriptExecutor jsUtil;

    public JavaScriptExecutorUtil(WebDriver driver){
        jsUtil = (JavascriptExecutor) driver;
    }

    public List<WebElement> doGetWebElements(String cssSelectorString){
        List<WebElement> elements = (List<WebElement>) jsUtil.executeScript("return document.querySelectorAll('"+cssSelectorString+"')" );
        return elements;
    }

    public void doPageRefreshUsingJS(){
        jsUtil.executeScript("window.history.go(0)");
    }

    public void doNavigateBackUsingJS(){
        jsUtil.executeScript("window.history.go(-1)");
    }

    public void doNavigateForwardUsingJS(){
        jsUtil.executeScript("window.history.go(1)");
    }

    public String doGetPageTitleUsingJS(){
        String title = jsUtil.executeScript("return document.title").toString();
        return title;
    }

    public String doGetPageUrlUsingJS(){
        String title = jsUtil.executeScript("return document.URL").toString();
        return title;
    }

    public void doScrollToElement(WebElement element){
        jsUtil.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void doScrollToPageTop(){
        jsUtil.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void doScrollToPageBottom(){
        jsUtil.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void doScrollToPageMiddile(){
        jsUtil.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
    }

    public void doScrollToPageHeight(int height)   {
        jsUtil.executeScript("window.scrollTo(0," + height + ")");
    }

    public String doGetElementText(WebElement element){
       String elementText = jsUtil.executeScript("return arguments[0].innerText",element).toString();
       return elementText;
    }

    public void doZoom(String zoomHeight){
        jsUtil.executeScript("document.body.style.zoom='" + zoomHeight + "'");
    }

    public void doFirefoxZoom(String zoomPercent){
        jsUtil.executeScript("document.body.style.zoom=scale'("+zoomPercent+")'");
    }

    public void doDrawBorder(WebElement element){
        jsUtil.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void doRemoveBorder(WebElement element){
        jsUtil.executeScript("arguments[0].style.border='none'", element);
    }

    public void doChangeBackgroundColor(String color, WebElement element){
        jsUtil.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
    }

    public void doFlashElement(WebElement element){
        String backgroundColor = element.getDomProperty("backgroundColor");
        doDrawBorder(element);
        for(int i = 0; i < 10; i++){
            doChangeBackgroundColor("rgb(152,300,152)", element);
            doChangeBackgroundColor(backgroundColor, element);
        }
        doRemoveBorder(element);
    }

    public void doSendKeys(WebElement element, String value){
        jsUtil.executeScript("arguments[0].value='"+ value +"'",element);
    }
}
