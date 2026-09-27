package com.orangehrm.components.dashboard;

import com.orangehrm.constants.AppTimeouts;
import com.orangehrm.constants.DashboardPageConstants;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;

public class TimeAtWorkWidgetComponent extends DashboardWidgetComponent {

    private WebElement widgetElement;
    private By employeeImage = By.className("employee-image");

    private By attendanceState = By.cssSelector(".orangehrm-attendance-card-state");
    private By attendanceDetails = By.cssSelector(".orangehrm-attendance-card-details");
    private By attendanceBarText = By.cssSelector(".orangehrm-attendance-card-bar>.orangehrm-attendance-card-fulltime");
    private By attendanceTimeoutButton = By.cssSelector(".orangehrm-attendance-card-action");

    private By attendanceWeekSummary = By.className("orangehrm-attendance-card-summary-week");
    private By getAttendanceWeekSummary = By.className("orangehrm-attendance-card-summary-total");

    public TimeAtWorkWidgetComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebElement element, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
        widgetElement = element;
    }

    public boolean isEmployeeImagePresent(){
       return  elementUtil.doIsElementDisplayed(this.widgetElement, employeeImage);
    }

    public String doGetAttendanceState(){
        return elementUtil.doGetElementText(this.widgetElement, attendanceState);
    }

    public String doGetAttendanceDetails(){
        return elementUtil.doGetElementText(this.widgetElement, attendanceDetails);
    }

    public String doGetAttendanceBarText(){
        return elementUtil.doGetElementText(this.widgetElement, attendanceBarText);
    }

    public boolean doCheckTimeOutButtonPresent(){
        return elementUtil.doIsElementDisplayed(this.widgetElement, attendanceTimeoutButton);
    }
    public String doClickOnTimeOutButton(){
        elementUtil.doClick(this.widgetElement, attendanceTimeoutButton);
        return browserUtil.doGetPageURLContains(DashboardPageConstants.PUNCH_URL_CONTAINS, AppTimeouts.MEDIUM_TIMEOUT);
    }


}
