package com.orangehrm.components.common;

import com.orangehrm.base.BaseComponent;
import com.orangehrm.constants.AppTimeouts;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.leave.LeaveListPage;
import com.orangehrm.utils.BrowserUtil;
import com.orangehrm.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class SidePanelComponent extends BaseComponent {
    public SidePanelComponent(ElementUtil elementUtil, BrowserUtil browserUtil, WebDriver driver, Properties prop) {
        super(elementUtil, browserUtil, driver, prop);
    }

    private By adminMenuItemLocator = By.xpath("//span[text()='Admin']/parent::a");

    private By pimMenuItemLocator = By.xpath("//span[text()='PIM']/parent::a");
    private By leaveMenuItemLocator = By.xpath("//span[text()='Leave']/parent::a");

    private By timeMenuItemLocator = By.xpath("//span[text()='Time']/parent::a");
    private By recruitmentMenuItemLocator = By.xpath("//span[text()='Recruitment']/parent::a");
    private By myInfoMenuItemLocator = By.xpath("//span[text()='My Info']/parent::a");

    private By performanceMenuItemLocator = By.xpath("//span[text()='Performance']/parent::a");
    private By dashboardMenuItemLocator = By.xpath("//span[text()='Dashboard']/parent::a");
    private By directoryMenuItemLocator = By.xpath("//span[text()='Directory']/parent::a");
    private By maintenanceMenuItemLocator = By.xpath("//span[text()='Maintenance']/parent::a");
    private By claimMenuItemLocator = By.xpath("//span[text()='Claim']/parent::a");
    private By buzzMenuItemLocator = By.xpath("//span[text()='Buzz']/parent::a");

    public void doNavigateToAdminMenuItem(){
        elementUtil.doClick(adminMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public void doNavigateToAPIMMenuItem(){
        elementUtil.doClick(pimMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public LeaveTopNavigationComponent doNavigateToLeaveMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(leaveMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new LeaveTopNavigationComponent(elementUtil, browserUtil, driver, prop);
    }

    public void doNavigateToTimeMenuItem(){
        elementUtil.doClick(timeMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }
    public TimeTopNavigationComponent doNavigateToRecruitmentMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(recruitmentMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new TimeTopNavigationComponent(elementUtil, browserUtil, driver, prop);
    }

    public void doNavigateToMyInfoMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(myInfoMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }
    public void doNavigateToPerformanceMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(performanceMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public DashboardPage doNavigateToDashboardMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(dashboardMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
        return new DashboardPage(driver, prop);
    }

    public void doNavigateToDirectoryMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(directoryMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }
    public void doNavigateToMaintenanceMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(maintenanceMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }

    public void doNavigateToClaimMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(claimMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }
    public void doNavigateToBuzzMenuItem(){
        elementUtil.doWaitForPageLoad(AppTimeouts.LARGE_TIMEOUT);
        elementUtil.doClick(buzzMenuItemLocator, AppTimeouts.MEDIUM_TIMEOUT);
    }
}
