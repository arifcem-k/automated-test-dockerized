package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    // Ortak metin gönderme fonksiyonu (Görünmesini bekler, temizler ve yazar)
    protected void writeText(By elementBy, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy)).clear();
        driver.findElement(elementBy).sendKeys(text);
    }

    // Ortak tıklama fonksiyonu (Tıklanabilir olmasını bekler ve tıklar)
    protected void click(By elementBy) {
        wait.until(ExpectedConditions.elementToBeClickable(elementBy)).click();
    }

    // Ortak metin okuma fonksiyonu
    protected String readText(By elementBy) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy)).getText();
    }
}