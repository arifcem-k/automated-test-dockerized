package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ParaBankPage extends BasePage {

    public ParaBankPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // 🎯 LOCATORS (Locator Havuzu - Tek Bir Yerde!)
    // ==========================================

    // Login ve Genel Elementler
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//input[@value='Log In']");
    private final By loginErrorLabel = By.className("error");
    private final By generalTitle = By.className("title");

    // Kayıt Formu Elementleri
    private final By registerLink = By.xpath("//a[text()='Register']");
    private final By firstNameField = By.id("customer.firstName");
    private final By lastNameField = By.id("customer.lastName");
    private final By streetField = By.id("customer.address.street");
    private final By cityField = By.id("customer.address.city");
    private final By stateField = By.id("customer.address.state");
    private final By zipCodeField = By.id("customer.address.zipCode");
    private final By phoneField = By.id("customer.phoneNumber");
    private final By ssnField = By.id("customer.ssn");
    private final By regUsernameField = By.id("customer.username");
    private final By regPasswordField = By.id("customer.password");
    private final By repeatedPasswordField = By.id("repeatedPassword");
    private final By registerButton = By.cssSelector("input.button[value='Register']");
    private final By regErrorLabel = By.id("customer.username.errors");

    // Sol Menü Bağlantıları
    private final By billPayLink = By.linkText("Bill Pay");
    private final By requestLoanLink = By.linkText("Request Loan");
    private final By transferFundsLink = By.linkText("Transfer Funds");
    private final By findTransactionsLink = By.linkText("Find Transactions");

    // Hesap Özeti (Account Overview) Tablosu
    private final By accountHeader = By.xpath("//table[@id='accountTable']//th[1]");
    private final By balanceHeader = By.xpath("//table[@id='accountTable']//th[2]");
    private final By availableAmountHeader = By.xpath("//table[@id='accountTable']//th[3]");
    private final By firstRowBalanceCell = By.xpath("//table[@id='accountTable']/tbody/tr[1]/td[2]");

    // Para Transferi (Transfer Funds)
    private final By transferAmountField = By.id("amount");
    private final By transferButton = By.xpath("//input[@value='Transfer']");
    private final By transferSuccessHeader = By.xpath("//div[@id='showResult']/h1");
    private final By transferFromAccountOption = By.xpath("//select[@id='fromAccountId']/option[1]");

    // Fatura Ödeme (Bill Pay)
    private final By payeeNameField = By.name("payee.name");
    private final By payeeStreetField = By.name("payee.address.street");
    private final By payeeCityField = By.name("payee.address.city");
    private final By payeeStateField = By.name("payee.address.state");
    private final By payeeZipField = By.name("payee.address.zipCode");
    private final By payeePhoneField = By.name("payee.phoneNumber");
    private final By payeeAccountField = By.name("payee.accountNumber");
    private final By payeeVerifyAccountField = By.name("verifyAccount");
    private final By billAmountField = By.name("amount");
    private final By sendPaymentButton = By.xpath("//input[@value='Send Payment']");
    private final By billPayResultHeader = By.xpath("//div[@id='billpayResult']/h1");

    // Kredi Başvurusu (Request Loan)
    private final By loanAmountField = By.id("amount");
    private final By downPaymentField = By.id("downPayment");
    private final By applyNowButton = By.xpath("//input[@value='Apply Now']");
    private final By loanStatusField = By.id("loanStatus");

    // İşlem Arama (Find Transactions)
    private final By findByAmountField = By.id("amount");
    private final By findByAmountButton = By.id("findByAmount");
    private final By transactionTable = By.id("transactionTable");

    // ==========================================
    // 🛠️ ACTIONS (Testlerin Çağıracağı Fonksiyonlar)
    // ==========================================

    public void login(String user, String pass) {
        writeText(usernameField, user);
        writeText(passwordField, pass);
        click(loginButton);
    }

    public String getLoginErrorMessage() { return readText(loginErrorLabel); }
    public String getGeneralTitle() { return readText(generalTitle); }

    public void navigateToRegister() { click(registerLink); }

    public void fillRegistrationForm(String fName, String lName, String street, String city,
                                     String state, String zip, String phone, String ssn,
                                     String user, String pass) {
        writeText(firstNameField, fName);
        writeText(lastNameField, lName);
        writeText(streetField, street);
        writeText(cityField, city);
        writeText(stateField, state);
        writeText(zipCodeField, zip);
        writeText(phoneField, phone);
        writeText(ssnField, ssn);
        writeText(regUsernameField, user);
        writeText(regPasswordField, pass);
        writeText(repeatedPasswordField, pass);
        click(registerButton);
    }

    public String getRegistrationError() { return readText(regErrorLabel); }

    public void navigateToMenu(String menuName) {
        if (menuName.equalsIgnoreCase("Bill Pay")) click(billPayLink);
        else if (menuName.equalsIgnoreCase("Request Loan")) click(requestLoanLink);
        else if (menuName.equalsIgnoreCase("Transfer Funds")) click(transferFundsLink);
        else if (menuName.equalsIgnoreCase("Find Transactions")) click(findTransactionsLink);
    }

    public String getAccountTableHeader(int colIndex) {
        if (colIndex == 1) return readText(accountHeader);
        if (colIndex == 2) return readText(balanceHeader);
        return readText(availableAmountHeader);
    }

    public double getFirstRowBalance() {
        String raw = readText(firstRowBalanceCell);
        return Double.parseDouble(raw.replace("$", "").trim());
    }

    public void executeTransfer(String amount) {
        // Asenkron yüklenen dropdown opsiyonunun dolmasını akıllıca bekle
        wait.until(ExpectedConditions.presenceOfElementLocated(transferFromAccountOption));
        writeText(transferAmountField, amount);
        click(transferButton);
    }

    public String getTransferResultText() { return readText(transferSuccessHeader); }

    public void executeBillPay(String name, String street, String city, String state,
                               String zip, String phone, String account, String amount) {
        writeText(payeeNameField, name);
        writeText(payeeStreetField, street);
        writeText(payeeCityField, city);
        writeText(payeeStateField, state);
        writeText(payeeZipField, zip);
        writeText(payeePhoneField, phone);
        writeText(payeeAccountField, account);
        writeText(payeeVerifyAccountField, account);
        writeText(billAmountField, amount);
        click(sendPaymentButton);
    }

    public String getBillPayResult() { return readText(billPayResultHeader); }

    public void executeLoanRequest(String amount, String downPayment) {
        writeText(loanAmountField, amount);
        writeText(downPaymentField, downPayment);
        click(applyNowButton);
    }

    public String getLoanStatus() { return readText(loanStatusField); }

    public void filterTransactionsByAmount(String amount) {
        wait.until(ExpectedConditions.urlContains("findtrans"));
        writeText(findByAmountField, amount);
        click(findByAmountButton);
    }

    public boolean isTransactionTableDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTable)).isDisplayed();
    }
}