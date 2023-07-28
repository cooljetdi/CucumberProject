package pages;

import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class BasePage {

    protected WebDriver driver;
    //private Rand rand;
    private By button = By.cssSelector("button[type='button']");
    private By ripple = By.cssSelector(".ripple-loader-container");
    private By organizationDropDown = By.xpath("(//div[@class='chip_outer_div']//span)[1]");
    private String organizationDropdownList = "//div[@class='dropdown-list dropdown-factory']//div[contains(@id, '%s')][text()='%s']";
    private By confirmBtn = By.xpath("//button[@type='button'][text()='Confirm']");
    private By doneBtn = By.xpath("//button[@type='button'][text()='Done']");

    public BasePage(WebDriver driver){
        this.driver = driver;
        //this.rand = new Rand();
    }

    public void sendText(By locator, String text){
        //waitForPresenceOfElementLocated(locator);
        waitForVisibilityOfElementLocated(locator);
        waitForElementToBeClickable(locator);
        driver.findElement(locator).sendKeys(text);
    }

    /*
     Send text to the locator using JavascriptExecutor.
    */
    public void jsSendText(By locator, String text){
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + text + "';", findBy(locator));
    }

    /*
      Change the attribute name's value using JavascriptExecutor.
   */
    public void jsSetAttribute(By locator, String attributeName, String attributeValue){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",
                findBy(locator), attributeName, attributeValue);
    }

    public void clearTextField(By locator){
        waitForVisibilityOfElementLocated(locator);
        waitForElementToBeClickable(locator);
        driver.findElement(locator).clear();
    }

    public void clearTextFieldBySendKeys(By locator){
        waitForVisibilityOfElementLocated(locator);
        waitForElementToBeClickable(locator);
        driver.findElement(locator).sendKeys(Keys.CONTROL + "a");
        driver.findElement(locator).sendKeys(Keys.DELETE);
    }

    public void clickBtn() {
        jsClickButtonBy(button);
    }

    public void clickConfirmBtn() {
        jsClickButtonBy(confirmBtn);
    }

    public void clickDoneBtn(){
        jsClickButtonBy(doneBtn);
    }

    public JavascriptExecutor getJSObject() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public void jsClick(WebElement element) {
        getJSObject().executeScript("arguments[0].click();", element);
    }

    public void jsClickBy(By locator) {
        WebElement element = driver.findElement(locator);
        getJSObject().executeScript("arguments[0].click();", element);
    }

    public void jsClickButtonBy(By button){
        waitForVisibilityOfElementLocated(button);
        waitForElementToBeClickable(button);
        WebElement element = driver.findElement(button);
        jsClick(element);
    }

    public void jsClickButtonBy(WebElement button){
        waitForVisibilityOfElement(button);
        waitForElementToBeClickable(button);
        jsClick(button);
    }

    public void clickButtonBy(By button){
        waitForVisibilityOfElementLocated(button);
        waitForElementToBeClickable(button);
        driver.findElement(button).click();
    }

    /*
    The bottom of the element will be aligned to the bottom of the visible area of the scrollable ancestor.
     */
    public void jsScrollAbove(WebElement element){
        getJSObject().executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /*
    The top of the element will be aligned to the top of the visible area of the scrollable ancestor.
     */
    public void jsScrollBelow(WebElement element){
        getJSObject().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForVisibilityOfElementLocated(By locator){
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForVisibilityOfElement(WebElement element){
        driverWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForInvisibilityOfElementLocated(By locator){
        driverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForInvisibilityOfElement(WebElement element){
        driverWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitForPresenceOfElementLocated(By locator){
        driverWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator){
        driverWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeClickable(WebElement element){
        driverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForUrlToBe(String url){
        driverWait().until(ExpectedConditions.urlToBe(url));
    }

    public void verifyUrl(String expectedUrl){
        waitForUrlToBe(expectedUrl);
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    public void verifyPageTitle(String expectedPageTitle, By actualPageTitleLocator){
        waitForVisibilityOfElementLocated(actualPageTitleLocator);
        expectedPageTitle = expectedPageTitle.trim().toLowerCase();
        String actualPageTitle = driver.findElement(actualPageTitleLocator).getText().trim().toLowerCase();
        Assert.assertEquals("Page titles do not match.", expectedPageTitle, actualPageTitle);
    }

    public void waitForPageToLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public void verifyPageUrl(String expectedUrl){
        waitForUrlToBe(expectedUrl);
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    public void reloadPage() {
        getJSObject().executeScript("location.reload(true);");
    }

    public void waitForRippleAnimation(){
        waitForInvisibilityOfElementLocated(ripple);
    }

    public WebDriverWait driverWait(){
        return new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    protected WebElement selectClickableElement(By selector) {
        waitForElementToBeClickable(selector);
        return driver.findElement(selector);
    }

    public void selectAnOrganizationByOrgNameAndOrgID(String expectedOrganizationName, String expectedOrgID) {
        // Wait for the ripple animation to disappear.
        waitForInvisibilityOfElementLocated(ripple);

        // Click the organization dropdown menu.
        jsClickButtonBy(organizationDropDown);

        waitForVisibilityOfElementLocated(By.xpath(String.format(organizationDropdownList, expectedOrgID, expectedOrganizationName)));
        waitForElementToBeClickable(By.xpath(String.format(organizationDropdownList, expectedOrgID, expectedOrganizationName)));

        // Select an organization by org ID and org name.
        jsClickButtonBy(By.xpath(String.format(organizationDropdownList, expectedOrgID, expectedOrganizationName)));
    }

    public WebElement findBy(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> findsBy(By locator){
        return driver.findElements(locator);
    }

    public void refresh(){
        driver.navigate().refresh();
    }

    public void selectItemInDropDown(String itemName, By dropDownLocator, By dropDownTableLocator, By dropDownListLocator){
        // Open the drop down.
        clickButtonBy(dropDownLocator);

        // Wait for the drop down table to be visible on the page.
        waitForVisibilityOfElementLocated(dropDownTableLocator);

        // Get a list of items from the drop down.
        List<WebElement> listOfItems = findsBy(dropDownListLocator);

        // Make sure there are items in the drop down.
        assertThat(listOfItems).hasSizeGreaterThan(0);
        //log.info("Total items in the drop down: " + listOfItems.size());

        boolean isItemFound = false;

        // Search for the item and select it.
        for(WebElement tradingPairAsset: listOfItems) {
            try {
                if(tradingPairAsset.getText().toLowerCase().equalsIgnoreCase(itemName.toLowerCase()))
                {
                    jsClickButtonBy(tradingPairAsset);
                    isItemFound = true;
                    break;
                }

            }
            catch(Exception e){
                listOfItems = findsBy(dropDownListLocator);
            }
        }

        // Make sure the item is found in the drop down.
        assertThat(isItemFound).isTrue();
    }
}
