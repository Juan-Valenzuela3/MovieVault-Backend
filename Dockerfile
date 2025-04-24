# --- Fase 1: Construcción ---
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Usa maven directamente en lugar de mvnw
RUN mvn clean package -DskipTests

# --- Fase 2: Ejecución ---
FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]