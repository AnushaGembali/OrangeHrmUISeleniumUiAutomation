package com.orangehrm.factory;

import com.orangehrm.exceptions.BrowserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.HashMap;
import java.util.Map;

public class OptionsManagerBuilder {

    private static final Logger log = LogManager.getLogger(OptionsManagerBuilder.class);
    private String browserVersion;
    private Boolean inCognito;
    private Boolean headless;
    private Boolean remote;
    private String testName;

    private String browserName;

    public OptionsManagerBuilder setBrowserName(String browserName){
        this.browserName = browserName;
        return this;
    }

    public OptionsManagerBuilder setBrowserVersion(String browserVersion){
        this.browserVersion = browserVersion;
        return this;
    }

    public OptionsManagerBuilder setTestName(String testName){
        this.testName = testName;
        return this;
    }

    public OptionsManagerBuilder setIncognito(Boolean incognito){
        this.inCognito = incognito;
        return this;
    }

    public OptionsManagerBuilder setHeadless(Boolean headless){
        this.headless = headless;
        return this;
    }

    public OptionsManagerBuilder setRemote(Boolean remote){
        this.remote = remote;
        return this;
    }

    public MutableCapabilities build(){

        MutableCapabilities options = getCapabilities();

        if(this.remote){

            options.setCapability("browserName",  this.browserName);
            log.info("============ RUNNING THE TESTS IN BROWSER "+ browserName +" ============");

            if (browserVersion != null) {
                log.info("============ RUNNING THE TESTS IN BROWSER VERSION "+ browserVersion +" ============");
                options.setCapability("browserVersion", browserVersion);
            }

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("screenResolution", "1920x1080x24");
            if(testName != null)    selenoidOptions.put("name", this.testName);
            options.setCapability("selenoid:options", selenoidOptions);
        }
        return options;
    }

    private MutableCapabilities getCapabilities() {

        MutableCapabilities options;

        log.info("============ RUNNING THE TESTS IN BROWSER " + browserName.toUpperCase() + " ============");

        if(this.browserName.equalsIgnoreCase("chrome")){
            ChromeOptions co = new ChromeOptions();
            if(this.inCognito != null && this.inCognito) {
                log.info("============ RUNNING THE TESTS IN INCOGNITO ============");
                co.addArguments("--incognito");
            }
            if(this.headless != null && this.headless){
                log.info("============ RUNNING THE TESTS IN HEADLESS ============");
                co.addArguments("--headless");
            }
            options = co;
        }
        else if(this.browserName.equalsIgnoreCase("firefox")){
            FirefoxOptions fo = new FirefoxOptions();
            if(this.inCognito != null && this.inCognito){
                log.info("============ RUNNING THE TESTS IN INCOGNITO ============");
                fo.addArguments("--private");
            }
            if(this.headless != null && this.headless){
                log.info("============ RUNNING THE TESTS IN HEADLESS ============");
                fo.addArguments("--headless");
            }
            options = fo;
        }
        else if (this.browserName.equalsIgnoreCase("edge")){
            EdgeOptions eo = new EdgeOptions();
            if(this.inCognito != null && this.inCognito){
                log.info("============ RUNNING THE TESTS IN INCOGNITO ============");
                eo.addArguments("--inPrivate");
            }
            if(this.headless != null && this.headless) {
                log.info("============ RUNNING THE TESTS IN HEADLESS ============");
                eo.addArguments("--headless");
            }
            options = eo;
        }
        else {
            log.error("=================== INVALID BROWSER NAME: " + browserName.toUpperCase() + " =========================");
            throw new BrowserException("=================== INVALID BROWSER NAME: " + browserName.toUpperCase() + " =========================");
        }
        return options;
    }

}
