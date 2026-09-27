package com.orangehrm.base;

import com.orangehrm.components.common.SidePanelComponent;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class BasePage {

    protected Properties prop;
    protected WebDriver driver;
    protected ElementUtil elementUtil;
    protected BrowserUtil browserUtil;
    protected SidePanelComponent sidePanelComponent;

    public BasePage(WebDriver driver, Properties prop){
        this.prop = prop;
        this.driver = driver;
        elementUtil = new ElementUtil(driver, prop);
        browserUtil = new BrowserUtil(driver);
        sidePanelComponent = new SidePanelComponent(elementUtil, browserUtil,driver, prop);
    }

    public SidePanelComponent getSidePanelcomponent(){
        return this.sidePanelComponent;
    }

    public String getPageTitle(){
        String title = browserUtil.doGetTitle();
        return title;
    }

    public String getPageUrl(){
        String url = browserUtil.doGetPageURL();
        return url;
    }
}
