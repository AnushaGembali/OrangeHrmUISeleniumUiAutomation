package com.orangehrm.components.dashboard;

import com.orangehrm.base.BaseComponent;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Properties;

public class DashboardWidgetComponent extends BaseComponent {

    public DashboardWidgetComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
    }

}
