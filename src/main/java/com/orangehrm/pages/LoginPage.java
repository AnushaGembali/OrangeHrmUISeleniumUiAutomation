package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.constants.AppTimeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LoginPage extends BasePage {

//    Locators of Login page
    private By userNameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginBtnLocator = By.tagName("button");

    public LoginPage(WebDriver driver, Properties prop){
        super(driver, prop);
    }

    public DashboardPage doLogin(String userName, String password){

        String url = prop.getProperty("url");
        browserUtil.launchURL(url);
        elementUtil.doSendKeys(userNameLocator, userName, AppTimeouts.MEDIUM_TIMEOUT);
        elementUtil.doSendKeys(passwordLocator, password);
        elementUtil.doClick(loginBtnLocator);
        return new DashboardPage(driver, prop);
    }



}
