package com.orangehrm.components.dashboard;

import com.orangehrm.constants.AppTimeouts;
import com.orangehrm.constants.DashboardPageConstants;
import com.orangehrm.pages.leave.ApplyLeavePage;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class QuickLaunchWidgetComponent extends DashboardWidgetComponent{
    public QuickLaunchWidgetComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebElement element, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
        quickLaunchWidgetElement = element;
    }

    private WebElement quickLaunchWidgetElement;

    private By quickLaunchHeadingLocator = By.className("orangehrm-quick-launch-heading");

    private By assignLeaveLocator = By.xpath("//button[@title='Assign Leave']");
    private By leaveListLocator = By.cssSelector("button[title='Leave List']");
    private By timeSheetsLocator = By.cssSelector("button[title='Timesheets']");
    private By applyLeaveLocator = By.cssSelector("button[title='Apply Leave']");
    private By myLeaveLocator = By.cssSelector("button[title='My Leave']");
    private By myTimeSheetLocator = By.cssSelector("button[title='My Timesheet']");

    public String doClickOnAssignLeave(){
        elementUtil.doClick(assignLeaveLocator, AppTimeouts.SMALL_TIMEOUT);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.ASSIGN_LEAVE_URL, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public String doClickOnLeaveList(){
        elementUtil.doClick(leaveListLocator, AppTimeouts.SMALL_TIMEOUT);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.LEAVE_LIST_URL, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public String doClickOnTimeSheets(){
        elementUtil.doClick(timeSheetsLocator, AppTimeouts.SMALL_TIMEOUT);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.TIMESHEETS_URL, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public ApplyLeavePage doClickOnApplyLeave(){
        elementUtil.doClick(applyLeaveLocator, AppTimeouts.SMALL_TIMEOUT);
        return new ApplyLeavePage(driver, prop);
    }

    public String doClickOnMyLeave(){
        elementUtil.doClick(myLeaveLocator, AppTimeouts.SMALL_TIMEOUT);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.My_LEAVE_URL, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public String doClickOnMyTimeSheet(){
        elementUtil.doClick(myTimeSheetLocator, AppTimeouts.SMALL_TIMEOUT);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.MY_TIMESHEET_URL, AppTimeouts.MEDIUM_TIMEOUT);
    }

}
