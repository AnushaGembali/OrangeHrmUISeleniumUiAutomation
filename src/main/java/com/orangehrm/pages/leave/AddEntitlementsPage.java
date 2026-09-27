package com.orangehrm.pages.leave;

import com.orangehrm.base.BasePage;
import com.orangehrm.constants.AppTimeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class AddEntitlementsPage extends BasePage {
    public AddEntitlementsPage(WebDriver driver, Properties prop) {
        super(driver, prop);
    }

    private By multipleEmployeesRadioBtnLocator = By.xpath("//label[text()='Add to']/parent::div/following-sibling::div//label[normalize-space()='Multiple Employees']");
    private By individualEmployeeRadioBtnLocator = By.xpath("//label[text()='Add to']/parent::div/following-sibling::div//label[normalize-space()='Individual Employee']");
    private  By employeeNameInputFieldLocator = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By selectEmpNameFromDropDownLocator = By.xpath("//div[@role='listbox']//div[@role='option']//span[text()='Peter Mac Anderson']");
    private By leaveTypeDropDownBtnLocator = By.xpath("//label[text()='Leave Type']/ancestor::div[contains(@class,'oxd-input-field-bottom-space')]//i");
    private String leaveTypeOptionLocator = "//span[text()='{leaveTypeText}']";
    private By leavePeriodDropDownBtnLocator = By.xpath("//label[text()='Leave Period']/ancestor::div[contains(@class,'oxd-input-field-bottom-space')]//i");
    private String leavePeriodOptionLocator = "//span[text()='{leavePeriodText}']";
    private By entitlementsInputFieldLocator = By.xpath("//label[text()='Entitlement']/ancestor::div[contains(@class,'oxd-input-field-bottom-space')]//input");
    private By saveBtnLocator = By.xpath("//button[normalize-space()='Save']");
    private By entitlementConfirmbtnLocator = By.xpath("//div[contains(@class,'oxd-dialog-sheet')]//button[normalize-space()='Confirm']");

    public EmployeeEntitlementsPage addEntitlements(boolean addToIndEmp, String EmpName, String LeaveType, String LeavePeriod, String entitlement)  {
        if(!addToIndEmp){
            elementUtil.doClick(multipleEmployeesRadioBtnLocator, AppTimeouts.LARGE_TIMEOUT);
        }
        else{
            elementUtil.doClick(individualEmployeeRadioBtnLocator, AppTimeouts.LARGE_TIMEOUT);
        }
        elementUtil.doSendKeys(employeeNameInputFieldLocator, EmpName);
        elementUtil.doClick(selectEmpNameFromDropDownLocator, AppTimeouts.LARGE_TIMEOUT);

        elementUtil.doClick(leaveTypeDropDownBtnLocator);
        By finalLeaveTypeOptionLocator = By.xpath(leaveTypeOptionLocator.replace("{leaveTypeText}",LeaveType));
        elementUtil.doClick(finalLeaveTypeOptionLocator);

        elementUtil.doClick(leavePeriodDropDownBtnLocator);
        By finalLeavePeriodOptionLocator = By.xpath(leavePeriodOptionLocator.replace("{leavePeriodText}", LeavePeriod));
        elementUtil.doClick(finalLeavePeriodOptionLocator);

        elementUtil.doSendKeys(entitlementsInputFieldLocator,entitlement);
        elementUtil.doClick(saveBtnLocator);

        elementUtil.doClick(entitlementConfirmbtnLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new EmployeeEntitlementsPage(driver, prop);
    }

}
