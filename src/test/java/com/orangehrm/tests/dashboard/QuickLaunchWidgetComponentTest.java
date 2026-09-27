package com.orangehrm.tests.dashboard;

import com.orangehrm.base.BaseTest;
import com.orangehrm.constants.DashboardPageConstants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class QuickLaunchWidgetComponentTest extends BaseTest {

    private static final String widgetTitle = "Quick Launch";

    @BeforeClass
    public void doLoginToApplication(){
        dashboardPage = loginPage.doLogin(userName, password);
    }

    @AfterMethod
    public void doNavigateBackToDashboard(){
        dashboardPage = dashboardPage.getSidePanelcomponent().doNavigateToDashboardMenuItem();
    }

    @Test
    public void verifyClickOnAssignLeave(){
       String actualURL =  dashboardPage.doClickOnAssignLeaveButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.ASSIGN_LEAVE_URL));
    }

    @Test
    public void verifyClickOnLeaveList(){
        String actualURL =  dashboardPage.doClickOnLeaveListButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.LEAVE_LIST_URL));
    }
    @Test
    public void verifyClickOnTimeSheets(){
        String actualURL =  dashboardPage.doClickOnTimesheetsButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.TIMESHEETS_URL));
    }
    @Test
    public void verifyClickOnApplyLeave(){
        String actualURL =  dashboardPage.doClickOnApplyLeaveButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.APPLY_LEAVE_URL));
    }
    @Test
    public void verifyClickOnMyLeave(){
        String actualURL =  dashboardPage.doClickOnMyLeaveButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.My_LEAVE_URL));
    }
    @Test
    public void verifyClickOnMyTimesheets(){
        String actualURL =  dashboardPage.doClickOnMyTimesheetsButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.MY_TIMESHEET_URL));
    }
}
