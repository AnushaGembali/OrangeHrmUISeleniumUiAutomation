package com.orangehrm.factory;

import com.orangehrm.exceptions.BrowserException;
import com.orangehrm.exceptions.ConfigFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class DriverFactory {

    private WebDriver driver;
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    private Properties prop;

    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    public WebDriver init_driver()  {

        String browserVersion = prop.getProperty("browserVersion");
        String browserName = prop.getProperty("browser", "chrome");
        String testName = prop.getProperty("testName");
        Boolean remote = Boolean.parseBoolean(prop.getProperty("remote","false"));
        Boolean headless = Boolean.parseBoolean(prop.getProperty("headless","false"));
        Boolean incognito = Boolean.parseBoolean(prop.getProperty("incognito","false"));

        OptionsManagerBuilder builder = new OptionsManagerBuilder()
                                                .setBrowserName(browserName)
                                                .setHeadless(headless)
                                                .setIncognito(incognito)
                                                .setBrowserVersion(browserVersion)
                                                .setRemote(remote)
                                                .setTestName(testName) ;
        MutableCapabilities options = null;
        if(!browserName.equalsIgnoreCase("safari")){
            options = builder.build();
        }

        if(remote){
            log.info("============ RUNNING THE TESTS IN REMOTE ============");
            createRemoteDriver(browserName, options);
        }
        else{
            log.info("============ RUNNING THE TESTS IN LOCAL ============");
            createLocalDriver(browserName, options);
        }
        driver = getThreadLocalDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static WebDriver getThreadLocalDriver(){
        return tlDriver.get();
    }

    private void createRemoteDriver(String browserName, MutableCapabilities options)   {
        String remoteURL = prop.getProperty("huburl");

        try {
            switch (browserName.toLowerCase().trim()) {
                case "chrome":
                case "edge":
                case "firefox":
                    tlDriver.set(new RemoteWebDriver(new URL(remoteURL), options));
                    break;
                case "safari":
                    tlDriver.set(new SafariDriver());
                    break;
                default:
                    System.out.println("Invalid Browser Name: " + browserName);
                    throw new BrowserException("Invalid Browser Name Passed:  " + browserName);
            }
        }
         catch(MalformedURLException e){
                throw new BrowserException("=========== INVALID URL ===========");
            }
    }

    private void createLocalDriver(String browserName, MutableCapabilities options) {
        switch(browserName.toLowerCase().trim()){
            case "chrome":
                tlDriver.set(new ChromeDriver((ChromeOptions) options));
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver((FirefoxOptions) options));
                break;
            case "edge":
                tlDriver.set(new EdgeDriver((EdgeOptions) options));
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;
            default:
                log.error("Invalid Browser Name: " +  browserName);
                System.out.println("Invalid Browser Name: " +  browserName);
                throw new BrowserException("Invalid Browser Name Passed:  " + browserName);
        }
    }

    public Properties init_properties() {
        prop = new Properties();
        String filePath;
        String envName = System.getProperty("env");

        if(envName == null){
           envName = "qa";
        }
        log.info("============= RUNNING THE TESTS IN " + envName.toUpperCase() + " ENV ===============");
        System.out.println("============= RUNNING THE TESTS IN " + envName.toUpperCase() + " ENV ===============");
        switch (envName.toLowerCase().trim()){
            case "qa":{
                filePath = "./src/test/resources/config/config.qa.properties";
                break;
            }
            case "prod":{
                filePath = "./src/test/resources/config/config.prod.properties";
                break;
            }
            case "uat":{
                filePath = "./src/test/resources/config/config.uat.properties";
                break;
            }
            case "dev":{
                filePath = "./src/test/resources/config/config.dev.properties";
                break;
            }
            default:
                log.error("Passed Invalid Environment Name: " + envName);
                System.out.println("Passed Invalid Environment Name: " + envName);
                throw new ConfigFileException("Passed Invalid Environment Name: " + envName);
        }
        try {
            FileInputStream ip = new FileInputStream(filePath);
            prop.load(ip);
            return prop;
        } catch (FileNotFoundException e) {
            log.error("Config File is not found at the location: " + filePath);
            System.out.println("Config File is not found at the location: " + filePath);
            throw new ConfigFileException("Config File is not found at the location: " + filePath);
        } catch (IOException e) {
            log.error("Problem while loading the config file at location: " + filePath);
            System.out.println("Problem while loading the config file at location: " + filePath);
            throw new ConfigFileException("Problem while loading the config file at location: " + filePath);
        }

    }

    public void closeBrowser() {
        if(this.driver != null){
            driver.quit();
        }
    }

    public static String getScreenshotAsFile() throws IOException {
       File srcFile =  ((TakesScreenshot)getThreadLocalDriver()).getScreenshotAs(OutputType.FILE);
       String destPath = "./screenshots/"+System.currentTimeMillis()+".png";
       Path destFilePath = Path.of(destPath);
       Files.createDirectories(destFilePath.getParent());
       Files.copy(Paths.get(srcFile.getAbsolutePath()), destFilePath, StandardCopyOption.REPLACE_EXISTING);
       return destPath;
    }

    public static byte[] getScreenshotAsBytes(){
      byte[] screenshotInBytes =  ((TakesScreenshot)getThreadLocalDriver()).getScreenshotAs(OutputType.BYTES);
      return screenshotInBytes;
    }

    public static String getScreenshotAsBase64(){
        return ((TakesScreenshot)getThreadLocalDriver()).getScreenshotAs(OutputType.BASE64);

    }
}
