package com.orangehrm.tests.dashboard;

import com.orangehrm.base.BaseTest;
import com.orangehrm.constants.DashboardPageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DashboardPageTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(DashboardPageTest.class);

    @BeforeClass
    public void doLogin(){
        dashboardPage = loginPage.doLogin(userName, password);
    }

    @Test
    public void doPageTitleTest(){
        String actualTitle =  dashboardPage.getPageTitle();
        log.info("======= Actual Title "+ actualTitle +" ========");
        Assert.assertEquals(actualTitle, DashboardPageConstants.PAGE_TITLE);
    }

    @Test
    public void doPageURLTest(){
        String actualURL =  dashboardPage.getPageUrl();
        log.info("======= Actual URL "+ actualURL +" ========");
        Assert.assertTrue(actualURL.contains(DashboardPageConstants.PAGE_URL_CONTAINS));
    }

    @Test
    public void doCheckDashboardWidgesCount(){
        int actualCount = dashboardPage.getDashboardWidgetCount();
        log.info("Actual count of dashboard widges is: " + actualCount);
        Assert.assertEquals(actualCount, DashboardPageConstants.DASHBOARD_WIDGES_COUNT, "The number of widges is not equal to 7");
    }

    @Test
    public void doCheckWidgesHeadersPresence(){
        List<String> actualHeaderNames = dashboardPage.getWidgetHeaders();
        log.info("Actual Header Names of dashboard widges are: " + actualHeaderNames.toString());
        Assert.assertEquals(actualHeaderNames, DashboardPageConstants.DASHBOARD_WIDGES_HEADERS);
    }

}
