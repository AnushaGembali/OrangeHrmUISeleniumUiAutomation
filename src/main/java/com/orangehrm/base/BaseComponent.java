package com.orangehrm.base;

import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

public class BaseComponent {

    protected ElementUtil elementUtil;
    protected BrowserUtil browserUtil;

    protected WebDriver driver;
    protected Properties prop;
    public BaseComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebDriver driver, Properties prop){
        this.elementUtil = elementUtil;
        this.browserUtil =browserUtil;
        this.driver = driver;
        this.prop = prop;
    }

}
