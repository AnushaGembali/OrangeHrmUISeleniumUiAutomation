package com.orangehrm.tests.leave;

import com.orangehrm.base.BaseTest;
import com.orangehrm.components.common.LeaveTopNavigationComponent;
import com.orangehrm.pages.leave.AddEntitlementsPage;
import com.orangehrm.pages.leave.EmployeeEntitlementsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddEntitlementsPageTest extends BaseTest {

    private AddEntitlementsPage addEntitlementsPage;

    @BeforeClass
    public void doNavigateToAddEntitlementsPage(){
        dashboardPage = loginPage.doLogin(userName, password);
        LeaveTopNavigationComponent leaveTopNavigationComponent = dashboardPage.getSidePanelcomponent().doNavigateToLeaveMenuItem();
        addEntitlementsPage = leaveTopNavigationComponent.doNavigateToAddEntitlementsPage();
    }

    @DataProvider
    public Object[][] addEntitlementsTestData(){
       return new Object[][]{
                {true, "Peter Mac Anderson", "CAN - Personal", "2026-01-01 - 2026-31-12", "2"}
        };
    }

    @Test(dataProvider = "addEntitlementsTestData")
    public void verifyAddEntitlements(boolean addToIndividualEmp, String empName, String leaveType, String leavePeriod, String numberOfEntitlements ){
        EmployeeEntitlementsPage empEntitlementsPage = addEntitlementsPage.addEntitlements(addToIndividualEmp, empName, leaveType, leavePeriod, numberOfEntitlements);
        Assert.assertTrue(actualURL.contains("/leave/viewLeaveEntitlements"));
    }


}
