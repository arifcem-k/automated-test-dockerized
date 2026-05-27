package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTests extends tests.BaseTest {

    @Test
    public void yeniKullaniciKayitTesti() {
        paraBankPage.navigateToRegister();
        paraBankPage.fillRegistrationForm("AC123", "K45", "qww", "ui", "kr", "121", "0555 ", "123-456-789", "testng", "Test1234");
        Assert.assertEquals(paraBankPage.getGeneralTitle(), "Welcome testng", "Welcome mesajı ekranda doğru görünmedi!");
    }

    @Test
    public void mevcutKullaniciylaYenidenKayitHataTesti() {
        paraBankPage.navigateToRegister();
        paraBankPage.fillRegistrationForm("AC123", "K45", "qww", "ui", "kr", "121", "0555 ", "123-456-789", "testng", "Test1234");
        Assert.assertEquals(paraBankPage.getRegistrationError(), "This username already exists.", "Hata mesajı ekranda doğru görünmedi!");
    }
}