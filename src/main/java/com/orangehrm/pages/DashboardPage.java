package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.components.dashboard.DashboardWidgetComponent;
import com.orangehrm.components.dashboard.QuickLaunchWidgetComponent;
import com.orangehrm.components.dashboard.TimeAtWorkWidgetComponent;
import com.orangehrm.constants.AppTimeouts;
import com.orangehrm.pages.leave.ApplyLeavePage;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class DashboardPage extends BasePage {

    private By widgetGrid = By.cssSelector(".orangehrm-dashboard-grid");
    private By dashboardWidget = By.cssSelector(".oxd-grid-item.oxd-grid-item--gutters.orangehrm-dashboard-widget");
    private By widgetBody = By.cssSelector(".orangehrm-dashboard-widget-body");
    private By dashboardWidgetHeader = By.className("orangehrm-dashboard-widget-header");

    public DashboardPage(WebDriver driver, Properties prop) {
        super(driver, prop);
    }

    private WebElement getWidgetElement(String widgetTitle){

            boolean isPageLoaded = elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
            if(isPageLoaded){
                WebElement widgetGridElement = elementUtil.doGetElement(widgetGrid, AppTimeouts.LARGE_TIMEOUT);
                List<WebElement> allWidgetElements = elementUtil.doGetElements(widgetGridElement, dashboardWidget);
                System.out.println("Number of widgets: " + allWidgetElements.size());

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return allWidgetElements.stream()
                                        .filter(element -> {
                                            elementUtil.checkElementHighlight(element);
                                            WebElement widgetHeader = elementUtil.doGetElement(element, dashboardWidgetHeader, AppTimeouts.MEDIUM_TIMEOUT);
                                            if(widgetHeader.getText().trim().equalsIgnoreCase(widgetTitle)) {
                                                return true;
                                            }
                                            return false;
                                        })
                                        .findFirst().get();
            }
            else {
                throw new RuntimeException("Dashboard Page and the widgets inside it are not loaded within sleep time of " + AppTimeouts.LARGE_TIMEOUT);
            }
    }

    private <T extends DashboardWidgetComponent> T getWidget(String widgetTitle, Class<T> widgetClass){
        WebElement widgetElement = getWidgetElement(widgetTitle);
        try{
            return widgetClass
                    .getDeclaredConstructor(ElementUtil.class, BrowserUtil.class, WebElement.class, WebDriver.class, Properties.class)
                    .newInstance(elementUtil, browserUtil, widgetElement, driver, prop);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to create Widget Object with title: " + widgetTitle);
        }
    }

    public int getDashboardWidgetCount(){
        return elementUtil.doGetElementsCount(dashboardWidget, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public List<String> getWidgetHeaders(){
        return elementUtil.doGetElementsText(dashboardWidgetHeader, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public boolean isEmployeeImagePresentInTimeAtWorkWidget(String widgetTitle){
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.isEmployeeImagePresent();
    }

    public String getEmployeeAttendanceState(String widgetTitle){
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.doGetAttendanceState();
    }

    public String getEmployeeAttendanceDetails(String widgetTitle) {
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.doGetAttendanceDetails();
    }

    public String getEmployeeAttendanceBar(String widgetTitle) {
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.doGetAttendanceBarText();
    }

    public boolean getAttendanceTimeOutBtnPresent(String widgetTitle){
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.doCheckTimeOutButtonPresent();
    }

    public String doClickOnTimeOutButton(String widgetTitle){
        TimeAtWorkWidgetComponent widgetObj = getWidget(widgetTitle, TimeAtWorkWidgetComponent.class);
        return widgetObj.doClickOnTimeOutButton();
    }
    public String doClickOnAssignLeaveButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        return quickLaunchWidgetComponent.doClickOnAssignLeave();
    }

    public String doClickOnLeaveListButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        return quickLaunchWidgetComponent.doClickOnLeaveList();
    }
    public String doClickOnTimesheetsButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        return quickLaunchWidgetComponent.doClickOnTimeSheets();
    }
    public String doClickOnApplyLeaveButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        ApplyLeavePage applyLeavePage = quickLaunchWidgetComponent.doClickOnApplyLeave();
        return applyLeavePage.getPageURL();
    }
    public String doClickOnMyLeaveButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        return quickLaunchWidgetComponent.doClickOnMyLeave();
    }
    public String doClickOnMyTimesheetsButton(String widgetTitle) {
        QuickLaunchWidgetComponent quickLaunchWidgetComponent = getWidget(widgetTitle, QuickLaunchWidgetComponent.class);
        return quickLaunchWidgetComponent.doClickOnMyTimeSheet();
    }
}
