

# E2E Test Automation Framework - ParaBank (POM & Dockerized Altyapısı)

Bu proje, **ParaBank** simülasyon platformu üzerinde uçtan uca (E2E) iş süreçlerini doğrulamak amacıyla kurumsal standartlarda inşa edilmiş; **Page Object Model (POM)** ve **Docker/Selenium Grid** mimarilerini harmanlayan Maven tabanlı bir test otomasyon framework'üdür.

> **Proje Gelişim Hikayesi:** Proje ilk aşamada spagetti kod kirliliğinden arındırılarak sürdürülebilir **POM** mimarisine taşınmıştır. İkinci fazda ise "benim makinemde çalışıyordu" konfor alanı tamamen terk edilerek, tüm test çevresi altyapı bağımlılıklarından izole bir şekilde **Multi-Container (Docker Compose)** dünyasına taşınmıştır.

---

## Kullanılan Teknolojiler & Altyapı

* **Programlama Dili:** Java 17
* **Test Otomasyon Aracı:** Selenium WebDriver (v4.x)
* **Test Framework:** TestNG (Assertion, Suite & Test Yönetimi)
* **Konteynerizasyon:** Docker & Docker Compose
* **Dağıtık Altyapı:** Selenium Grid (Hub & Chrome Node Mimarisini)
* **Raporlama & Matris:** Excel Test Execution Matrix

---

## Proje Klasör Hiyerarşisi & Evrim Süreci

Projenin gelişim evrelerini ve mimari dönüşümünü net bir şekilde göstermek adına depo (repository) yapısı aşağıdaki gibi segmentlere ayrılmıştır:

```text
├── Dockerized/               # Altyapıdan bağımsız, konteynerize edilmiş güncel çalışma alanı
│   ├── Dockerfile            # Maven test-runner imaj yapılandırması
│   ├── docker-compose.yml    # Multi-Container (Hub, Node, Runner) orkestrasyonu
│   ├── pom.xml               # Docker çevre değişkenlerine duyarlı Maven bağımlılıkları
│   └── BaseTest.java         # RemoteWebDriver ve Grid ağına uyumlu dinamik sürücü yönetimi
│
├── POM/                      # Kod mimarisinin evrim arşiv alanı
│   ├── Before/               # İlk aşamadaki düz/spagetti test sınıfları
│   └── After/                # Temiz mimariye geçiş alanı
│       ├── pages/            # Sayfa elementleri (Locators) ve saf fonksiyonlar
│       └── tests/            # İş mantığı, test adımları ve assertions (src/test/java hiyerarşisi)
│
└── test-cases/               
    └── test-case.xlsx        # 11 kritik iş senaryosunun canlı takip matrisi

```

>  **Önemli Teknik Kazanım:** Projenin Dockerize edilme sürecinde, lokal IDE'lerin sağladığı esnekliğin aksine, Docker konteynerlerindeki Maven yapısının endüstri standartlarındaki klasör hiyerarşisine (`src/test/java`) ne kadar katı ve sıkı sıkıya bağlı olduğu deneyimlenmiş; proje yapısı bu standartlara tam uyumlu olacak şekilde refactor edilmiştir.

---

## Test Senaryoları & Kapsam (Test Coverage)

Framework içerisinde 4 ana iş grubu altında toplanan **11 kritik senaryo** otomatikleştirilmiştir. Tüm test süreçleri Docker konteynerleri içerisinde başarıyla doğrulanmıştır (**%100 PASSED**):

1. **Kullanıcı Kayıt Yönetimi (`UserRegistrationTests`)**
* Başarılı sıfırdan dinamik kullanıcı kaydı süreci.
* Sistemde mevcut bir kullanıcı adıyla mükerrer kayıt engelleme testi (Negatif Senaryo).


2. **Kimlik Doğrulama (`LoginTests`)**
* Geçerli kimlik bilgileriyle başarılı sisteme giriş kontrolü.
* Hatalı şifre denemelerinde dinamik hata mesajı (`.error` class) doğrulaması.


3. **Hesap Özeti (`AccountOverviewTests`)**
* Web Table manipülasyonu ile dinamik tablo başlıklarının kontrolü.
* Hesap bakiyesinin string karakterlerden (`$`) temizlenerek `double` tipine cast edilmesi ve sayısal bakiye doğrulaması (`> 0`).


4. **Finansal Hareketler & Servisler (`FundsTransferTests...`)**
* Hesaplar arası para transferi süreci ve transfer onay mesajı kontrolü.
* Fatura ödeme simülasyonu (Payee ad, adres ve tutar entegrasyonu).
* Kredi başvurusu ve anlık onay durum mekanizması kontrolü.
* İşlem geçmişinin sadece tutar kriterine göre filtrelenmesi tablosu testi.



---

## Yol Haritası & Sonraki Adımlar (Roadmap)

* [x] Kod mimarisinin POM desenine dönüştürülmesi.
* [x] Kök dizine `Dockerfile` ve `docker-compose.yml` entegrasyonu.
* [x] Lokal tarayıcı bağımlılığının bitirilerek testlerin **Selenium Grid Hub/Node** mimarisinde izole koşulması.
* [x] Docker ortamındaki Maven standartlarına tam uyumlu klasör yapısı refactoring'i.
