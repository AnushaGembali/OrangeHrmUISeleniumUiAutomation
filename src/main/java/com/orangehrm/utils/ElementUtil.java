package com.orangehrm.utils;

import com.orangehrm.constants.AppTimeouts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ElementUtil {

    private static final Logger log = LogManager.getLogger(ElementUtil.class);
    private WebDriver driver;

    private Actions actions;

    private boolean isHighlight;
    private JavaScriptExecutorUtil jsUtil;

    public ElementUtil(WebDriver driver, Properties prop) {
        this.driver = driver;
        actions = new Actions(driver);
        isHighlight = Boolean.parseBoolean(prop.getProperty("highlight", String.valueOf(false)));
        jsUtil = new JavaScriptExecutorUtil(driver);
    }

    public void checkElementHighlight(WebElement element){
        if(isHighlight){
            jsUtil.doFlashElement(element);
        }
    }
    public WebElement doGetElement(By locator){
        try{
            WebElement element = driver.findElement(locator);
            checkElementHighlight(element);
            return element;
        }catch(NoSuchElementException e){
            log.error("======= No Element found with the given locator " + locator + " =========");
            throw new RuntimeException("======= No Element found with the given locator " + locator + " =========");
        }
    }
    public WebElement doGetElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if(element != null){
            checkElementHighlight(element);
            return element;
        }
        else{
            log.error("======= No Element found with the given locator " + locator + " =========");
            throw new RuntimeException("======= No Element found with the given locator " + locator + " =========");
        }
    }

    public WebElement doGetElement(WebElement parentElement, By locator){
        try{
            WebElement element = parentElement.findElement(locator);
            checkElementHighlight(element);
            return element;
        }catch(NoSuchElementException e){
            log.error("======= No Element found with the given locator " + locator + " =========");
            throw new RuntimeException("======= No Element found with the given locator " + locator + " =========");
        }
    }

    public WebElement doGetElement(WebElement parentElement, By locator, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(driver -> {
                try{
                    WebElement childElement = parentElement.findElement(locator);
                    return childElement;
                }catch(NoSuchElementException e){
                    throw new RuntimeException("No Element is found with the locator: " + locator);
                }
            });
            checkElementHighlight(element);
            return element;
        }catch(NoSuchElementException e){
            log.error("======= No Element found with the given locator " + locator + " =========");
            throw new RuntimeException("======= No Element found with the given locator " + locator + " =========");
        }
    }

    public List<WebElement> doGetElements(By locator){
        List<WebElement> elements = driver.findElements(locator);
        return elements;
    }
    public List<WebElement> doGetElements(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> doGetElements(WebElement parentElement, By locator){
        List<WebElement> elements = parentElement.findElements(locator);
        return elements;
    }
    public void doSendKeys(By locator, String value, int timeout) {
        WebElement ele = doGetElement(locator, timeout);
        ele.sendKeys(value);
    }

    public void doSendKeys(By locator, String value){
        WebElement element = doGetElement(locator);
        element.sendKeys(value);
    }

    public void doSendKeys(By locator, CharSequence... value){
        WebElement element = doGetElement(locator);
        element.sendKeys(value);
    }

    public void doSendKeys(WebElement parentElement, By locator, String value, int timeout) {
        WebElement element = doGetElement(parentElement, locator, timeout);
        element.clear();
        element.sendKeys(value);
    }

    public void doSendKeys(WebElement parentElement, By locator, String value){
        WebElement element = doGetElement(parentElement, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void doSendKeys(By locator, CharSequence value, int timeout) {
        WebElement element = doGetElement(locator, timeout);
        element.clear();
        element.sendKeys(value);
    }

    public void doClearInputField(By locator){
        WebElement element = doGetElement(locator);
        element.clear();
    }

    public void doClearInputField(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        element.clear();
    }
    public void doClick(By locator){
        WebElement element = doGetElement(locator);
        element.click();
    }

    public void doClick(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        element.click();
    }

    public void doClick(WebElement parentElement, By locator){
        WebElement element = doGetElement(parentElement, locator);
        element.click();
    }
    public void doWaitForElementToBeClickable(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public String doGetElementText(By locator){
        WebElement element = doGetElement(locator);
        String eleText = element.getText().trim();
        return eleText;
    }

    public String doGetElementText(WebElement element){
        String eleText = element.getText().trim();
        return eleText;
    }

    public String doGetElementText(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(driver -> {
            String text = null;
            if(element.isDisplayed())
            {
                text = element.getText();
            }
            return text;
        });
    }
    public String doGetElementText(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        String eleText = element.getText().trim();
        return eleText;
    }
    public String doGetElementText(WebElement parentElement, By locator){
        WebElement element = doGetElement(parentElement, locator);
        String eleText = element.getText().trim();
        return eleText;
    }

    public String doGetElementAttribute(By locator, String attName){
        WebElement element = doGetElement(locator);
        String attValue = element.getDomAttribute(attName).trim();
        return attValue;
    }

    public String doGetElementAttribute(WebElement parentElement, By locator, String attName){
        WebElement element = doGetElement(parentElement, locator);
        String attValue = element.getDomAttribute(attName).trim();
        return attValue;
    }
    public String doGetElementAttribute(By locator, String attName, int timeout){
        WebElement element = doGetElement(locator, timeout);
        String attValue = element.getDomAttribute(attName).trim();
        return attValue;
    }

    public String doGetElementProperty(By locator, String propName){
        WebElement element = doGetElement(locator);
        String propValue = element.getDomProperty(propName).trim();
        return propValue;
    }

    public String doGetElementProperty(WebElement parentElement, By locator, String propName){
        WebElement element = doGetElement(parentElement, locator);
        String propValue = element.getDomProperty(propName).trim();
        return propValue;
    }
    public String doGetElementProperty(By locator, String propName, int timeout){
        WebElement element = doGetElement(locator, timeout);
        String propValue = element.getDomProperty(propName).trim();
        return propValue;
    }

    public int doGetElementsCount(By locator){
        List<WebElement> elements = doGetElements(locator);
        return elements.size();
    }
    public int doGetElementsCount(WebElement parentElement, By locator){
        List<WebElement> elements = doGetElements(parentElement, locator);
        return elements.size();
    }


    public int doGetElementsCount(By locator, int timeout){
        List<WebElement> elements = doGetElements(locator, timeout);
        return elements.size();
    }
    public List<String> doGetElementsText(By locator){
        List<WebElement> elements = doGetElements(locator);
        List<String> elesText = elements.stream()
                                            .map(ele -> ele.getText())
                                            .filter(eleText -> eleText != null && !eleText.isEmpty())
                                            .collect(Collectors.toList());
        return elesText;
    }

    public List<String> doGetElementsText(WebElement parentElement, By locator){
        List<WebElement> elements = doGetElements(parentElement, locator);
        List<String> elesText = elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .collect(Collectors.toList());
        return elesText;
    }

    public List<String> doGetElementsText(By locator, int timeout){
        List<WebElement> elements = doGetElements(locator, timeout);
        List<String> elesText = elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .collect(Collectors.toList());
        return elesText;
    }

    public List<String> doGetElementsText(List<WebElement> elements){
        List<String> elesText = elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .collect(Collectors.toList());
        return elesText;
    }

    public void doPrintElementsText(By locator){
        List<WebElement> elements = doGetElements(locator);
        elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .forEach(System.out::println);

    }

    public void doPrintElementsText(WebElement parentElement, By locator){
        List<WebElement> elements = doGetElements(parentElement, locator);
        elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .forEach(System.out::println);

    }
    public void doPrintElementsText(By locator, int timeout){
        List<WebElement> elements = doGetElements(locator, timeout);
        elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .forEach(System.out::println);
    }

    public void doPrintElementsText(List<WebElement> elements){
       elements.stream()
                .map(ele -> ele.getText())
                .filter(eleText -> eleText != null && !eleText.isEmpty())
                .forEach(System.out::println);
    }

    public boolean doIsElementDisplayed(By locator){
        WebElement element = doGetElement(locator);
        return element.isDisplayed();
    }

    public boolean doIsElementDisplayed(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        return element.isDisplayed();
    }
    public boolean doIsElementDisplayed(WebElement parentElement, By locator){
        WebElement element = doGetElement(parentElement, locator);
        return element.isDisplayed();
    }

    public boolean doIsElementDisplayed(WebElement parentElement, By locator, int timeout) {
        WebElement element = doGetElement(parentElement, locator, timeout);
        return element.isDisplayed();
    }

    public boolean doIsElementEnabled(By locator){
        WebElement element = doGetElement(locator);
        return element.isEnabled();
    }

    public boolean doIsElementEnabled(WebElement parentElement, By locator){
        WebElement element = doGetElement(parentElement, locator);
        return element.isEnabled();
    }
    public boolean doIsElementEnabled(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        return element.isEnabled();
    }

    public boolean doIsElementSelected(By locator){
        WebElement element = doGetElement(locator);
        return element.isSelected();
    }

    public boolean doIsElementSelected(WebElement parentElement, By locator){
        WebElement element = doGetElement(parentElement,  locator);
        return element.isSelected();
    }

    public boolean doIsElementSelected(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        return element.isSelected();
    }

    //Utilites for selecting options from drop down

    private Select doGetSelectObjOnElement(By locator){
        WebElement element = doGetElement(locator);
        return new Select(element);
    }

    private Select doGetSelectObjOnElement(By locator, int timeout){
        WebElement element = doGetElement(locator, timeout);
        return new Select(element);
    }
    public void doSelectOptionUsingIndex(By locator, int optIndex, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        selectEle.selectByIndex(optIndex);
    }
    public void doSelectOptionUsingIndex(By locator, int optIndex){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.selectByIndex(optIndex);
    }
    public void doSelectOptionUsingValue(By locator, String optValue, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator,timeout);
        selectEle.selectByValue(optValue);
    }

    public void doSelectOptionUsingValue(By locator, String optValue){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.selectByValue(optValue);
    }

    public void doSelectOptionUsingText(By locator, String text, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        selectEle.selectByVisibleText(text);
    }

    public void doSelectOptionUsingText(By locator, String text){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.selectByVisibleText(text);
    }

    public void doDeselectOptionUsingIndex(By locator, int optNum, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        selectEle.deselectByIndex(optNum);
    }

    public void doDeselectOptionUsingIndex(By locator, int optNum){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.deselectByIndex(optNum);
    }

    public void doDeselectOptionUsingValue(By locator, String optValue, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        selectEle.deselectByValue(optValue);
    }
    public void doDeselectOptionUsingValue(By locator, String optValue){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.deselectByValue(optValue);
    }

    public void doDeselectOptionUsingText(By locator, String text, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        selectEle.deselectByVisibleText(text);
    }

    public void doDeselectOptionUsingText(By locator, String text){
        Select selectEle = doGetSelectObjOnElement(locator);
        selectEle.deselectByVisibleText(text);
    }

    private List<WebElement> doGetAllOptionsInDropDown(By locator){
        Select selectEle = doGetSelectObjOnElement(locator);
        List<WebElement> allOptions = selectEle.getOptions();
        return allOptions;
    }

    private List<WebElement> doGetAllOptionsInDropDown(By locator, int timeout){
        Select selectEle = doGetSelectObjOnElement(locator, timeout);
        List<WebElement> allOptions = selectEle.getOptions();
        return allOptions;
    }

    public int doGetCountOfOptionsInDropDown(By locator){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator);
        return allElements.size();
    }

    public int doGetCountOfOptionsInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator, timeout);
        return allElements.size();
    }

    public List<String> doGetAllOptionsTextInDropDown(By locator){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator);
        return doGetElementsText(allElements);
    }

    public List<String> doGetAllOptionsTextInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator, timeout);
        return doGetElementsText(allElements);
    }

    public void doPrintAllOptionsTextInDropDown(By locator){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator);
        doPrintElementsText(allElements);
    }

    public void doPrintAllOptionsTextInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllOptionsInDropDown(locator, timeout);
        doPrintElementsText(allElements);
    }


    private List<WebElement> doGetAllSelectedOptions(By locator){
        Select element = doGetSelectObjOnElement(locator);
        List<WebElement> allSelectedOptions = element.getAllSelectedOptions();
        return allSelectedOptions;
    }

    private List<WebElement> doGetAllSelectedOptions(By locator, int timeout){
        Select element = doGetSelectObjOnElement(locator, timeout);
        List<WebElement> allSelectedOptions = element.getAllSelectedOptions();
        return allSelectedOptions;
    }

    public int doGetCountOfSelectedOptionsInDropDown(By locator){
        List<WebElement> allElements = doGetAllSelectedOptions(locator);
        return allElements.size();
    }

    public int doGetCountOfSelectedOptionsInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllSelectedOptions(locator, timeout);
        return allElements.size();
    }

    public List<String> doGetAllSelectedOptionsTextInDropDown(By locator){
        List<WebElement> allElements = doGetAllSelectedOptions(locator);
        return doGetElementsText(allElements);
    }

    public List<String> doGetAllSelectedOptionsTextInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllSelectedOptions(locator, timeout);
        return doGetElementsText(allElements);
    }

    public void doPrintAllSelectedOptionsTextInDropDown(By locator){
        List<WebElement> allElements = doGetAllSelectedOptions(locator);
        doPrintElementsText(allElements);
    }

    public void doPrintAllSelectedOptionsTextInDropDown(By locator, int timeout){
        List<WebElement> allElements = doGetAllSelectedOptions(locator, timeout);
        doPrintElementsText(allElements);
    }

    private void doSelectOption(List<WebElement> elements, String value) {
        for(WebElement ele : elements){
            if (ele.getText().equalsIgnoreCase(value)) {
                ele.click();
                return;
            }
        }
    }

    public void doSelOptionFromDropDownWithoutSelect(By locator, String value){
        List<WebElement> elements = doGetElements(locator);
        doSelectOption(elements, value);
    }

    public void doSelOptionFromDropDownWithoutSelect(By locator, String value, int timeout){
        List<WebElement> elements = doGetElements(locator, timeout);
        doSelectOption(elements, value);
    }

    public void doSelOptionFromDropDownWithSelect(By locator, String value){
        Select element = doGetSelectObjOnElement(locator);
        doSelectOption(element.getOptions(), value);
    }

    public void doSelOptionFromDropDownWithSelect(By locator, String value, int timeout){
        Select element = doGetSelectObjOnElement(locator, timeout);
        doSelectOption(element.getOptions(), value);
    }

    public void doClickUsingActions(By locator){
        WebElement element = doGetElement(locator);
        actions.click(element)
                .build()
                .perform();
    }

    public void doSendKeysUsingActions(By locator, String value){
        WebElement element = doGetElement(locator);
        actions.moveToElement(element)
                .sendKeys(value)
                .build()
                .perform();
    }

    public void doSendKeysUsingActionsWithPause(By locator, String value, long pauseValue){
        WebElement element = doGetElement(locator);
        actions.moveToElement(element)
                .sendKeys(value)
                .pause(pauseValue)
                .build()
                .perform();
    }

    public void doActionsRightClick(By locator){
        WebElement element = doGetElement(locator);
        actions.moveToElement(element)
                .contextClick()
                .build()
                .perform();
    }

    public void doActionsDragAndDrop(By sourceLocator, By destLocator){
        WebElement sourceElement = doGetElement(sourceLocator);
        WebElement destElement = doGetElement(destLocator);
        actions.dragAndDrop(sourceElement, destElement)
                .build()
                .perform();

//        actions.moveToElement(sourceElement)
//                .clickAndHold()
//                .moveToElement(destElement)
//                .release()
//                .build().perform();
    }

    public void doActionsPageTop(){
        actions.sendKeys(Keys.CONTROL)
                .sendKeys(Keys.HOME)
                .build()
                .perform();
    }

    public void doActionsPageBotton(){
        actions.sendKeys(Keys.CONTROL)
                .sendKeys(Keys.END)
                .build()
                .perform();
    }

    public void doActionsPageUp(){
        actions.sendKeys(Keys.PAGE_UP)
                .build()
                .perform();
    }

    public void doActionsPageDown(){
        actions.sendKeys(Keys.PAGE_DOWN)
                .build()
                .perform();
    }

    public void doActionsScrollToElement(By locator){
        WebElement element = doGetElement(locator);
        actions.scrollToElement(element)
                .build()
                .perform();
    }

    //=========================================== ALERT UTILITIES ========================================================

    private Alert doWaitForAlert(int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert;
    }

    public void doAcceptAlert(int timeout){
        Alert alert = doWaitForAlert(timeout);
        alert.accept();
    }

    public void doDismissAlert(int timeout){
        Alert alert = doWaitForAlert(timeout);
        alert.dismiss();
    }

    public void doSendKeysToAlert(String value, int timeout){
        Alert alert = doWaitForAlert(timeout);
        alert.sendKeys(value);
    }

    public String doGetTextFromAlert(int timeout){
        Alert alert = doWaitForAlert(timeout);
        String text = alert.getText();
        return text;
    }

    //=========================================== FRAME UTILITIES ========================================================

    public void doSwitchToFrame(int index, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    public void doSwitchToFrame(By locator, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void doSwitchToFrame(WebElement element, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public void doSwitchToFrame(String frameName, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    public void doSwitchToMainFrame(){
        driver = driver.switchTo().defaultContent();
    }

    public void doSwitchToParentFrame(){
        driver = driver.switchTo().parentFrame();
    }

    public boolean doWaitForNewWindowOrTab(int expNumOfWindowsOrTabs, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        try {
            return wait.until(ExpectedConditions.numberOfWindowsToBe(expNumOfWindowsOrTabs));
        }
        catch(TimeoutException e) {
            System.out.println("Number of windows are not matched");
            return false;
        }
    }

    public boolean doWaitForPageLoad(int timeout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        String pageStatus = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
        return Boolean.parseBoolean(pageStatus);
    }

}
