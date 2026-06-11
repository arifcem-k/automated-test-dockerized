package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.ParaBankPage;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;
    protected ParaBankPage paraBankPage;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();


        String remoteUrl = System.getenv("SELENIUM_REMOTE_URL");

        if (remoteUrl != null) {
            driver = new RemoteWebDriver(new URL(remoteUrl), options);
        } else {

            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/");
        paraBankPage = new ParaBankPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
