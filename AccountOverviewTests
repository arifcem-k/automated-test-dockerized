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

public class AccountOverviewTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Hiçbir driver yüklemeden direkt Chrome'u çağırıyoruz!
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }
    @Test public void hesapOzetiTabloDogrulamasi() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("testng");
        driver.findElement(By.name("password")).sendKeys("Test1234");

        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebElement accountBaslikElementi = driver.findElement(By.xpath("//table[@id='accountTable']//th[1]"));
        WebElement balanceBaslikElementi = driver.findElement(By.xpath("//table[@id='accountTable']//th[2]"));
        WebElement availableAmountBaslikElementi = driver.findElement(By.xpath("//table[@id='accountTable']//th[3]"));

        String gercekAccountBasligi = accountBaslikElementi.getText();
        String gercekBalanceBasligi = balanceBaslikElementi.getText();
        String gercekAvailableAmountBasligi = availableAmountBaslikElementi.getText();


        Assert.assertEquals(gercekAccountBasligi, "Account", "Account sütun başlığı hatalı veya yüklenemedi!");
        Assert.assertEquals(gercekBalanceBasligi, "Balance*", "Balance sütun başlığı hatalı veya yüklenemedi!");

        Assert.assertEquals(gercekAvailableAmountBasligi, "Available Amount", "Available Amount sütun başlığı hatalı veya yüklenemedi!");

    }

    @Test
    public void bakiyeMiktariKontrolTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("testng");
        driver.findElement(By.name("password")).sendKeys("Test1234");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        By tabloXpath = By.xpath("//table[@id='accountTable']/tbody/tr[1]/td[2]");
        WebElement bakiyeElementi = wait.until(ExpectedConditions.visibilityOfElementLocated(tabloXpath));


        String bakiyeMetni = bakiyeElementi.getText();


        String temizBakiyeMetni = bakiyeMetni.replace("$", "").trim();
        double gercekBakiye = Double.parseDouble(temizBakiyeMetni);


        Assert.assertTrue(gercekBakiye > 0, "Hata: Hesap bakiyesi sıfırdan büyük değil! Mevcut bakiye: " + gercekBakiye);

        System.out.println("Test Başarılı! Doğrulanan bakiye miktarı: " + gercekBakiye);
    }




    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }
}
