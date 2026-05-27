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

public class LoanTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void krediBasvurusuTesti() {
        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Request Loan"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("amount"))).sendKeys("5000");
        driver.findElement(By.id("downPayment")).sendKeys("500");


        driver.findElement(By.xpath("//input[@value='Apply Now']")).click();

        WebElement sonucElementi = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loanStatus")));
        Assert.assertNotNull(sonucElementi.getText(), "Kredi başvuru sonucu ekranda görünmedi!");

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }



    }
}
