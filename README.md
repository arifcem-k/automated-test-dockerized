#  E2E Test Automation Framework - ParaBank (POM & Dockerized Execution Altyapısı)

Bu proje, **ParaBank** simülasyon platformu üzerinde uçtan uca (E2E) iş süreçlerini doğrulamak amacıyla kurumsal standartlarda inşa edilmiş bir **Selenium WebDriver & TestNG** otomasyon framework'üdür. 

>  **Proje Gelişim Vizyonu (Storytelling):** Framework, bakım maliyetlerini sıfırlamak adına **Page Object Model (POM)** mimarisiyle tasarlanmıştır. Proje ismiyle müsemma olacak şekilde, bir sonraki aşamada local execution modelinden çıkılarak **Docker & Selenium Grid** mimarisine entegre edilecek ve DevOps süreçlerine taşınacaktır.

---

## 🛠️ Kullanılan Teknolojiler & Kütüphaneler
* **Programlama Dili:** Java 17
* **Test Otomasyon Aracı:** Selenium WebDriver (v4.x - Otomatik Driver Yönetimi)
* **Test Framework:** TestNG (Assertion & Test Yönetimi)
* **Tasarım Deseni (Pattern):** Page Object Model (POM)
* **Raporlama & Matris:** Excel Test Execution Matrix

---

##  Framework Mimari Yapısı (Page Object Model)
Proje, spagetti kod kirliliğinden kaçınmak ve element lokasyon değişikliklerini tek bir noktadan yönetmek için katmanlı mimariye (Layered Architecture) sahiptir:

*  `src/main/java/pages/` -> Sayfa elementlerini (Locators) ve sayfaya özel fonksiyonları barındıran Page Object sınıfları.
*  `src/test/java/tests/` -> İş mantığını, test senaryolarını ve `Assert` doğrulama adımlarını barındıran Test sınıfları.
*  `src/test/resources/` -> Projenin canlı takibini sağlayan Excel Test Execution Matrisi.

---

##  Test Senaryoları & Kapsam (Test Coverage)

Framework içerisinde 4 ana test grubu altında toplam **11 kritik senaryo** otomatikleştirilmiştir ve tüm test süreçleri başarıyla doğrulanmıştır (**%100 PASSED**):

1. **Kullanıcı Kayıt Yönetimi (`UserRegistrationTests`)**
   * Başarılı sıfırdan kullanıcı kaydı.
   * Sistemde mevcut bir kullanıcı adıyla mükerrer kayıt engelleme testi (Negatif Senaryo).
2. **Kimlik Doğrulama (`LoginTests`)**
   * Geçerli kimlik bilgileriyle başarılı sisteme giriş kontrolü.
   * Hatalı şifre denemelerinde dinamik hata mesajı (`.error` class) doğrulaması.
3. **Hesap Özeti (`AccountOverviewTests`)**
   * Web Table manipülasyonu ile dinamik tablo başlıklarının kontrolü.
   * Hesap bakiyesinin string karakterlerden (`$`) temizlenerek `double` tipine cast edilmesi ve sayısal bakiye doğrulaması (`> 0`).
4. **Finansal Hareketler & Servisler (`FundsTransferTests`, `BillPayTests`, `LoanTests`, `FindTransactionsTests`)**
   * Hesaplar arası para transferi süreci ve transfer onay mesajı kontrolü.
   * Fatura ödeme simülasyonu (Payee ad, adres ve tutar entegrasyonu).
   * Kredi başvurusu ve anlık onay durum mekanizması kontrolü.
   * İşlem geçmişinin sadece tutar kriterine göre filtrelenmesi tablosu testi.

---

##  Yakında Gelecek Özellikler (Next Roadmap)
* [ ] Kök dizine `Dockerfile` ve `docker-compose.yml` eklenmesi.
* [ ] Testlerin lokal tarayıcı bağımlılığından kurtarılarak **Selenium Grid** hub/node konteynerleri üzerinde izole koşulması.
* [ ] Jenkins/GitHub Actions entegrasyon alt yapısı.
