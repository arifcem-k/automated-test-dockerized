# Temel imaj olarak Maven ve Java 17 kullanıyoruz
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Önce pom.xml'i kopyalayıp bağımlılıkları indiriyoruz (Cache için)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Şimdi kodlarımızı kopyalıyoruz
COPY src ./src

# Testi çalıştıracak varsayılan komut (Surefire plugini *Tests sınıflarını otomatik bulur)
CMD ["mvn", "clean", "test"]