package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FundsTransferTests extends BaseTest {

    @Test
    public void hesaplarArasiParaTransferTesti() {
        paraBankPage.login("testng", "Test1234");
        paraBankPage.navigateToMenu("Transfer Funds");
        paraBankPage.executeTransfer("100");
        Assert.assertTrue(paraBankPage.getTransferResultText().contains("Transfer Complete"), "Ekranda beklenen başarı mesajı görülemedi!");
    }

    @Test
    public void yetersizBakiyeTransferHataTesti() {
        paraBankPage.login("testng", "Test1234");
        paraBankPage.navigateToMenu("Transfer Funds");
        paraBankPage.executeTransfer("999999");

        // 🔥 Simülasyon açığını yakalayıp bilerek testi FAIL eden QA Dokunuşu!
        Assert.assertFalse(paraBankPage.getGeneralTitle().contains("Transfer Complete"),
                "BUG: Yetersiz bakiyeye rağmen transfer başarıyla tamamlandı mesajı alındı!");
    }
}