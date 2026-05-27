package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountOverviewTests extends BaseTest {

    @Test
    public void hesapOzetiTabloDogrulamasi() {
        paraBankPage.login("testng", "Test1234");
        Assert.assertEquals(paraBankPage.getAccountTableHeader(1), "Account");
        Assert.assertEquals(paraBankPage.getAccountTableHeader(2), "Balance*");
        Assert.assertEquals(paraBankPage.getAccountTableHeader(3), "Available Amount");
    }

    @Test
    public void bakiyeMiktariKontrolTesti() {
        paraBankPage.login("testng", "Test1234");
        double bakiye = paraBankPage.getFirstRowBalance();
        Assert.assertTrue(bakiye > 0, "Hata: Hesap bakiyesi sıfırdan büyük değil! Mevcut bakiye: " + bakiye);
    }
}