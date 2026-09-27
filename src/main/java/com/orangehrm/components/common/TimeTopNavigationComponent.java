package com.orangehrm.components.common;

import com.orangehrm.base.BaseComponent;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TimeTopNavigationComponent extends BaseComponent {
    public TimeTopNavigationComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
    }
}
