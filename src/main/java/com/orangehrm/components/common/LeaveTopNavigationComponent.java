package com.orangehrm.components.common;

import com.orangehrm.base.BaseComponent;
import com.orangehrm.constants.AppTimeouts;
import com.orangehrm.pages.leave.AddEntitlementsPage;
import com.orangehrm.pages.leave.ApplyLeavePage;
import com.orangehrm.pages.leave.EmployeeEntitlementsPage;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class LeaveTopNavigationComponent extends BaseComponent {

    public LeaveTopNavigationComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
    }

    private By applyLeaveLocator = By.linkText("Apply");

    private By myLeaveLocator = By.linkText("My Leave");
    private By leaveListLocator = By.xpath("Leave List");
    private By assignLeaveLocator = By.linkText("Assign Leave");
    private By entitlementsLocator = By.xpath("//span[normalize-space()='Entitlements']");
    private By reportsLocator = By.xpath("//span[normalize-space()='Reports']");
    private By configureLocator = By.xpath("//span[normalize-space()='Configure']");

    private By addEntitlementsLocator = By.linkText("Add Entitlements");
    private By employeeEntitlementsLocator = By.linkText("Employee Entitlements");


    public ApplyLeavePage clickApplyLeave(){
        elementUtil.doClick(applyLeaveLocator, AppTimeouts.SMALL_TIMEOUT);
        return new ApplyLeavePage(driver, prop);
    }
    public AddEntitlementsPage doNavigateToAddEntitlementsPage() {
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(entitlementsLocator, AppTimeouts.MEDIUM_TIMEOUT);
        elementUtil.doClick(addEntitlementsLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new AddEntitlementsPage(driver, prop);
    }

    public EmployeeEntitlementsPage doNavigateToEmployeeEntitlementsPage() {
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(entitlementsLocator, AppTimeouts.MEDIUM_TIMEOUT);
        elementUtil.doClick(employeeEntitlementsLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new EmployeeEntitlementsPage(driver, prop);
    }
}
