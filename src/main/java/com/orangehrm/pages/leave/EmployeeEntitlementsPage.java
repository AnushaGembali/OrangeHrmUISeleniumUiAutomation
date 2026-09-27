package com.orangehrm.pages.leave;

import com.orangehrm.base.BasePage;
import com.orangehrm.constants.AppTimeouts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class EmployeeEntitlementsPage extends BasePage {

    public EmployeeEntitlementsPage(WebDriver driver, Properties prop) {
        super(driver, prop);
    }

    private By employeeNameInputFieldLocator = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private By selectEmpNameFromDropDownLocator = By.xpath("//div[@role='listbox']//div[@role='option']//span[text()='Peter Mac Anderson']");
    private By leaveTypeDropDownBtnLocator = By.xpath("//label[text()='Leave Type']/ancestor::div[contains(@class,'oxd-input-field-bottom-space')]//i");
    private String leaveTypeOptionLocator = "//span[text()='{leaveTypeText}']";
    private By leavePeriodDropDownBtnLocator = By.xpath("//label[text()='Leave Period']/ancestor::div[contains(@class,'oxd-input-field-bottom-space')]//i");
    private String leavePeriodOptionLocator = "//span[text()='{leavePeriodText}']";
    private By searchBtnLocator = By.xpath("//button[@type='submit']");
    private By recordsTableLocator = By.className("oxd-table");

    public boolean searchForExistingEntitlements(String empName, String leaveType, String leavePeriod){
        elementUtil.doSendKeys(employeeNameInputFieldLocator, empName);
        elementUtil.doClick(selectEmpNameFromDropDownLocator, AppTimeouts.LARGE_TIMEOUT);

        elementUtil.doClick(leaveTypeDropDownBtnLocator);
        By finalLeaveTypeOptionLocator = By.xpath(leaveTypeOptionLocator.replace("{leaveTypeText}",leaveType));
        elementUtil.doClick(finalLeaveTypeOptionLocator);

        elementUtil.doClick(leavePeriodDropDownBtnLocator);
        By finalLeavePeriodOptionLocator = By.xpath(leavePeriodOptionLocator.replace("{leavePeriodText}", leavePeriod));
        elementUtil.doClick(finalLeavePeriodOptionLocator);
        elementUtil.doClick(searchBtnLocator);

    }

    public boolean validateEntitlementsTableIsPresent(){
        return elementUtil.doIsElementDisplayed(recordsTableLocator, AppTimeouts.LARGE_TIMEOUT);
    }
}
