package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanTests extends BaseTest {

    @Test
    public void krediBasvurusuTesti() {
        paraBankPage.login("john", "demo");
        paraBankPage.navigateToMenu("Request Loan");
        paraBankPage.executeLoanRequest("5000", "500");
        Assert.assertNotNull(paraBankPage.getLoanStatus(), "Kredi başvuru sonucu ekranda görünmedi!");
    }
}