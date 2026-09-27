package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.constants.DashboardPageConstants;
import com.orangehrm.constants.LoginPageConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @Test
    public void doLoginTest(){
        dashboardPage = loginPage.doLogin(userName, password);
        String actualDashboardPageTitle = dashboardPage.getPageTitle();
        System.out.println("Actual Title: " + actualDashboardPageTitle);
        Assert.assertEquals(actualDashboardPageTitle, DashboardPageConstants.PAGE_TITLE);
    }

    @Test
    public void doPageTitleTest(){
      String actualTitle =  loginPage.getPageTitle();
      log.info("======= Actual Title "+ actualTitle +" ========");
        Assert.assertEquals(actualTitle, LoginPageConstants.PAGE_TITLE);
    }

    @Test
    public void doPageURLTest(){
        String actualURL =  loginPage.getPageUrl();
        log.info("======= Actual URL "+ actualURL +" ========");
        Assert.assertTrue(actualURL.contains(LoginPageConstants.PAGE_URL_CONTAINS));
    }
}
