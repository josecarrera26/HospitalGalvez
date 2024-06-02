# Etapa 1: Construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

# Copiar solo el archivo pom.xml inicial para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto de la aplicación y construir el artefacto
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación usando OpenJDK 17
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el artefacto construido desde la etapa anterior
COPY --from=build /app/target/hospitalgalvez-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8486 para que la aplicación sea accesible
EXPOSE 8486

# Definir el comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
