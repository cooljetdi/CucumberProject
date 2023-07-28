package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoinTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);


        driver.get("https://coinmarketcap.com/");

        String highlight = "//div[@class='sc-427fe1bb-0 sc-427fe1bb-3 isSveL']/div";
        String trigger = "//label[@id='1']";

        // To trigger off
        driver.findElement(By.xpath(trigger)).click();
        Thread.sleep(2000);

        // validate the highlight has 0 highlights.
        List<WebElement> highlightElements= driver.findElements(By.xpath(highlight));
        System.out.println("Trigger off Highlights is " + highlightElements.size());
        Assert.assertEquals(0, highlightElements.size());


        // To trigger on
        driver.findElement(By.xpath(trigger)).click();
        Thread.sleep(2000);

        // validate the 3 highlight .
        highlightElements= driver.findElements(By.xpath(highlight));
        System.out.println("Trigger on Highlights is " + highlightElements.size());
        Assert.assertEquals(3 , highlightElements.size());
    }
}
