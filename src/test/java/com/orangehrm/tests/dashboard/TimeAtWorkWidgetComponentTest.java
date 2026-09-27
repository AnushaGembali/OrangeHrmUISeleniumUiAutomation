package com.orangehrm.tests.dashboard;

import com.orangehrm.base.BaseTest;
import com.orangehrm.constants.DashboardPageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TimeAtWorkWidgetComponentTest extends BaseTest {

    private static final String widgetTitle = "Time at Work";
    private static final Logger log = LogManager.getLogger(TimeAtWorkWidgetComponentTest.class);

    @BeforeClass
    public void doLogin(){
        dashboardPage = loginPage.doLogin(userName, password);
    }

    @Test
    public void verifyEmpImgPresentInAttendanceCard(){
        boolean isImgPresent = dashboardPage.isEmployeeImagePresentInTimeAtWorkWidget(widgetTitle);
        Assert.assertTrue(isImgPresent, "Employee Image is not present");
    }

    @Test
    public void verifyEmpAttendanceState(){
        String actualState = dashboardPage.getEmployeeAttendanceState(widgetTitle);
        getSoftAssert().assertNotNull(actualState, "Attendance state is either not present or is not displayed ");
        getSoftAssert().assertTrue(DashboardPageConstants.TIME_AT_WORK_ATTENDANCE_STATE.contains(actualState.trim()));
    }

    @Test
    public void verifyEmpAttendanceDetails(){
        String actualAttendanceDetails = dashboardPage.getEmployeeAttendanceDetails(widgetTitle);
        getSoftAssert().assertNotNull(actualAttendanceDetails, "Attendance Details are either not present or not dispalyed");
        boolean isDetailsPresent = false;
       for(String value : DashboardPageConstants.TIME_AT_WORK_ATTENDANCE_DETAILS){
           if(actualAttendanceDetails.contains(value))
               isDetailsPresent = true;
        }
        getSoftAssert().assertTrue(isDetailsPresent, "The Attendance details are not as expected");
    }

    @Test
    public void verifyEmpAttendanceBar(){
        String actualBarText = dashboardPage.getEmployeeAttendanceBar(widgetTitle);
        Assert.assertNotNull(actualBarText, "Attendance Bar is either not present or is not displayed ");
    }

    @Test
    public void verifyAttendanceTimeOutBtnPresent(){
        boolean isTimeOutBtnPresent = dashboardPage.getAttendanceTimeOutBtnPresent(widgetTitle);
       Assert.assertNotNull(isTimeOutBtnPresent, "TimeOut Button is either not present or is not displayed in the attendance bar");
    }

    @Test(priority = Integer.MIN_VALUE)
    public void verifyGoToPunchOutPageOnClickingTimeOutBtn(){
        String actualURL = dashboardPage.doClickOnTimeOutButton(widgetTitle);
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.PUNCH_URL_CONTAINS));
    }

    @AfterMethod
    public void navigateBackToDashboard(){
        dashboardPage = dashboardPage.getSidePanelcomponent().doNavigateToDashboardMenuItem();
    }
}
