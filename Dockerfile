# Step 1: Build del JAR con Maven installato
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Installa Maven
RUN apk add --no-cache maven bash

# Copia i file del progetto
COPY pom.xml .
COPY src ./src

# Build del progetto e generazione JAR eseguibile Spring Boot
RUN mvn clean package spring-boot:repackage -DskipTests

# Step 2: immagine runtime più leggera
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia il JAR compilato
COPY --from=builder /app/target/*.jar app.jar

# Esponi porta 8080 (opzionale, utile per test locali)
EXPOSE 8080

# Entry point per l'app
ENTRYPOINT ["java","-jar","app.jar"]