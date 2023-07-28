package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class FinancialTimesPage extends BasePage{
    //private WebDriver driver;
    private String financialTimesHomeUrl = "https://ft.com";
    private String searchIcon = ".//a[@href = '#o-header-search-primary']";
    private String searchTxtField = "//input[@id='o-header-search-term-primary']";
    private String searchBtn = "//div[@id = 'o-header-search-primary']//button[@class ='o-header__search-submit']";
    private String viewingResult = "//h2[@class ='o-teaser-collection__heading o-teaser-collection__heading--half-width']";


    //@FindBy(xpath = ".//a[@href = '#o-header-search-primary']")
    //public WebElement searchBtn;

    public FinancialTimesPage(WebDriver driver){
        super(driver);
        //this.driver = driver;
    }

    public void openFinancialTimesHomePage(){
        driver.get(financialTimesHomeUrl);
    }

    public void clickSearchIcon(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(searchIcon)));
        driver.findElement(By.xpath(searchIcon)).click();

        System.out.println("Search icon is clicked!!!");
    }

    public void enterText(String text) {
        sendText(By.xpath(searchTxtField), text);
    }

    public void clickSearchBtn(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(searchBtn)));
        driver.findElement(By.xpath(searchBtn)).click();
    }

    public int getResult(){
        String[] str = driver.findElement(By.xpath(viewingResult)).getText().split(" ");

        return Integer.parseInt(str[str.length -1]);
    }
}
