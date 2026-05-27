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

public class BillPayTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void faturaOdemeSimulasyonuTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Bill Pay"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("payee.name"))).sendKeys("EnerjiSA");
        driver.findElement(By.name("payee.address.street")).sendKeys("qww");
        driver.findElement(By.name("payee.address.city")).sendKeys("ui");
        driver.findElement(By.name("payee.address.state")).sendKeys("kr");
        driver.findElement(By.name("payee.address.zipCode")).sendKeys("121");
        driver.findElement(By.name("payee.phoneNumber")).sendKeys("02165551122");
        driver.findElement(By.name("payee.accountNumber")).sendKeys("98765");
        driver.findElement(By.name("verifyAccount")).sendKeys("98765");
        driver.findElement(By.name("amount")).sendKeys("50.00");


        driver.findElement(By.xpath("//input[@value='Send Payment']")).click();


        WebElement basariElementi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='billpayResult']/h1")));
        Assert.assertTrue(basariElementi.getText().contains("Bill Payment Complete"), "Fatura ödeme başarı mesajı alınamadı!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }


    }
}
