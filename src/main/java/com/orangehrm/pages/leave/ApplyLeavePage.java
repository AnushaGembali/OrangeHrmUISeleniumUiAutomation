package com.orangehrm.pages.leave;

import com.orangehrm.base.BasePage;
import com.orangehrm.constants.AppTimeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplyLeavePage extends BasePage {
    public ApplyLeavePage(WebDriver driver, Properties prop) {
        super(driver, prop);
    }

    private By applyLeaveHeader = By.xpath("//h6[text()='Apply Leave']");
    public boolean isApplyLeaveHeaderPresent(){
        return elementUtil.doIsElementDisplayed(applyLeaveHeader, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public String getPageURL(){
        return browserUtil.doGetPageURL();
    }
}
