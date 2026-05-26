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

public class FindTransactionsTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void islemGecmisiFiltrelemeTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Find Transactions"))).click();


        wait.until(ExpectedConditions.urlContains("findtrans"));


        WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("amount")
        ));
        amountField.clear();
        amountField.sendKeys("100.00");


        driver.findElement(By.id("findByAmount")).click();


        WebElement tabloElementi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transactionTable")));
        Assert.assertTrue(tabloElementi.isDisplayed(), "İşlem geçmişi tablosu filtrelenemedi veya yüklenemedi!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }



    }
}
