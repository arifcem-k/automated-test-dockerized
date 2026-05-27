import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Hiçbir driver yüklemeden direkt Chrome'u çağırıyoruz!
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void basariliGirisTesti() {
        driver.get("https://parabank.parasoft.com/");

        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("demo");

        driver.findElement(By.xpath("//input[@value='Log In']")).click();

        String beklenenBaslik = "ParaBank | Accounts Overview";
        Assert.assertEquals(driver.getTitle(), beklenenBaslik, "Giriş başarısız, sayfa başlığı uyuşmuyor!");
    }

    @Test
    public void gecersizSifreGirisHataTesti() {

        driver.get("https://parabank.parasoft.com/");
        driver.findElement(By.name("username")).sendKeys("john");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();


        WebElement hataMesajElementi = driver.findElement(By.className("error"));
        String hatamesajı = hataMesajElementi.getText();

        String beklenenHataMesaji = "The username and password could not be verified.";

        Assert.assertEquals(hatamesajı, beklenenHataMesaji, "Hata mesajı ekranda doğru görünmedi!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }
}
