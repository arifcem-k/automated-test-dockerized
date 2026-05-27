import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FundsTransferTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }
    @Test
    public void hesaplarArasiParaTransferTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("testng");
        driver.findElement(By.name("password")).sendKeys("Test1234");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Transfer Funds"))).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='fromAccountId']/option[1]")));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).sendKeys("100");


        driver.findElement(By.xpath("//input[@value='Transfer']")).click();


        WebElement basariElementi = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='showResult']/h1"))
        );


        String basariMetni = basariElementi.getText().trim();
        Assert.assertTrue(basariMetni.contains("Transfer Complete"),
                "Ekranda beklenen başarı mesajı görülemedi! Alınan metin: " + basariMetni);
    }


    @Test
    public void yetersizBakiyeTransferHataTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("testng");
        driver.findElement(By.name("password")).sendKeys("Test1234");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Transfer Funds"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).sendKeys("999999");
        driver.findElement(By.xpath("//input[@value='Transfer']")).click();

        WebElement basariElementi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));


        Assert.assertFalse(basariElementi.getText().contains("Transfer Complete"),
                "BUG: Yetersiz bakiyeye rağmen transfer başarıyla tamamlandı mesajı alındı!");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }
}
