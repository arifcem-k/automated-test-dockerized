package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void basariliGirisTesti() {
        paraBankPage.login("john", "demo");
        Assert.assertEquals(driver.getTitle(), "ParaBank | Accounts Overview", "Giriş başarısız, sayfa başlığı uyuşmuyor!");
    }

    @Test
    public void gecersizSifreGirisHataTesti() {
        paraBankPage.login("john", "123");
        Assert.assertEquals(paraBankPage.getLoginErrorMessage(), "The username and password could not be verified.", "Hata mesajı eksik veya yanlış!");
    }
}