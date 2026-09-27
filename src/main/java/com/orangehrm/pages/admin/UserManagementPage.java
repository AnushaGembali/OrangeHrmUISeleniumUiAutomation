package com.orangehrm.pages.admin;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class UserManagementPage extends BasePage {
    public UserManagementPage(WebDriver driver, Properties prop) {
        super(driver, prop);
    }

    private By jobTabLocator = By.xpath("//span[normalize-space()='Job']");
    private By organizationLocator = By.xpath("//span[normalize-space()='Organization']");
    private By qualificationsLocator = By.xpath("//span[normalize-space()='Qualifications']");
    private By nationalitiesLocator = By.linkText("Nationalities");
    private By corporateBrandingLocator = By.linkText("Corporate Branding");
    private By configurationLocator = By.xpath("//span[normalize-space()='Configuration']");


}
