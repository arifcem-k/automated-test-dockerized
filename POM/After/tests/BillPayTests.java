package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPayTests extends BaseTest {

    @Test
    public void faturaOdemeSimulasyonuTesti() {
        paraBankPage.login("john", "demo");
        paraBankPage.navigateToMenu("Bill Pay");
        paraBankPage.executeBillPay("EnerjiSA", "qww", "ui", "kr", "121", "02165551122", "98765", "50.00");
        Assert.assertTrue(paraBankPage.getBillPayResult().contains("Bill Payment Complete"), "Fatura ödeme başarı mesajı alınamadı!");
    }
}