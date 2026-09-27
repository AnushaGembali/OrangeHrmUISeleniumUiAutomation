package com.orangehrm.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.orangehrm.factory.DriverFactory;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


//@Listeners(ChainTestListener.class)
public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    private WebDriver driver;
    private DriverFactory driverFactory;
    protected Properties prop;
    protected String userName;
    protected String password;

    private static ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @Parameters({"browser", "browserVersion", "testName"})
    @BeforeTest
    public void setUp(@Optional String browserName, @Optional String browserversion, @Optional String testname){
        driverFactory = new DriverFactory();
        prop = driverFactory.init_properties();

        if(browserName != null){
            prop.setProperty("browser", browserName);
        }
        if(browserversion != null){
            prop.setProperty("browserVersion", browserversion);
        }
        if(testname != null){
            prop.setProperty("testName", testname);
        }
        driver = driverFactory.init_driver();
        loginPage = new LoginPage(driver, prop);
        userName = prop.getProperty("userName");
        password = prop.getProperty("password");
    }

    protected SoftAssert getSoftAssert(){
        return softAssert.get();
    }

    @BeforeMethod
    public void initSoftAssert() {
        softAssert.set(new SoftAssert());
    }

    @AfterMethod
    public void tearDownSoftAssert() {
        try{
           getSoftAssert().assertAll();
        }finally {
            softAssert.remove();
        }
    }

    @AfterTest
    public void tearDown(){
        driverFactory.closeBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void attachScreenshotToChaintestReport(ITestResult result){

        if(!result.isSuccess()) {
            try {
                String path = driverFactory.getScreenshotAsFile();
                ChainTestListener.embed(new File(path), "image/png");
            } catch (IOException e) {
                System.out.println("=========== Error while taking or attaching screenshot to the report =================");
                log.error("=========== Error while taking or attaching screenshot to the report =================");
            }
        }
    }
}
