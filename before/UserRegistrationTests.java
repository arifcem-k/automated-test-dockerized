import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRegistrationTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Hiçbir driver yüklemeden direkt Chrome'u çağırıyoruz!
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void yeniKullaniciKayitTesti() {
        driver.get("https://parabank.parasoft.com/");

        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.id("customer.firstName")).sendKeys("AC123");
        driver.findElement(By.id("customer.lastName")).sendKeys("K45");
        driver.findElement(By.id("customer.address.street")).sendKeys("qww");
        driver.findElement(By.id("customer.address.city")).sendKeys("ui");
        driver.findElement(By.id("customer.address.state")).sendKeys("kr");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("121");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("0555 ");
        driver.findElement(By.id("customer.ssn")).sendKeys("123-456-789");
        driver.findElement(By.id("customer.username")).sendKeys("testng");
        driver.findElement(By.id("customer.password")).sendKeys("Test1234");
        driver.findElement(By.id("repeatedPassword")).sendKeys("Test1234");

        driver.findElement(By.cssSelector("input.button[value='Register']")).click();

        WebElement welcomeMesaj = driver.findElement(By.className("title"));
        String gercekMesaj = welcomeMesaj.getText();

        String beklenenMesaj = "Welcome testng";

        Assert.assertEquals(gercekMesaj, beklenenMesaj, "Welcome mesajı ekranda doğru görünmedi!");

    }

    @Test
    public void mevcutKullaniciylaYenidenKayitHataTesti() {
        driver.get("https://parabank.parasoft.com/");

        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.id("customer.firstName")).sendKeys("AC123");
        driver.findElement(By.id("customer.lastName")).sendKeys("K45");
        driver.findElement(By.id("customer.address.street")).sendKeys("qww");
        driver.findElement(By.id("customer.address.city")).sendKeys("ui");
        driver.findElement(By.id("customer.address.state")).sendKeys("kr");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("121");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("0555 ");
        driver.findElement(By.id("customer.ssn")).sendKeys("123-456-789");
        driver.findElement(By.id("customer.username")).sendKeys("testng");
        driver.findElement(By.id("customer.password")).sendKeys("Test1234");
        driver.findElement(By.id("repeatedPassword")).sendKeys("Test1234");

        driver.findElement(By.cssSelector("input.button[value='Register']")).click();

        WebElement hatamesaj = driver.findElement(By.id("customer.username.errors"));
        String gercekMesaj = hatamesaj.getText();

        String beklenenMesaj = "This username already exists.";

        Assert.assertEquals(gercekMesaj, beklenenMesaj, "hata mesajı ekranda doğru görünmedi!");


    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


    }
}
