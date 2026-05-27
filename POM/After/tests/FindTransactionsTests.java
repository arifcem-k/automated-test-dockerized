package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTransactionsTests extends BaseTest {

    @Test
    public void islemGecmisiFiltrelemeTesti() {
        paraBankPage.login("john", "demo");
        paraBankPage.navigateToMenu("Find Transactions");
        paraBankPage.filterTransactionsByAmount("100.00");
        Assert.assertTrue(paraBankPage.isTransactionTableDisplayed(), "İşlem geçmişi tablosu filtrelenemedi veya yüklenemedi!");
    }
}