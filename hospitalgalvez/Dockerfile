# Use the official Maven image to build the application with JDK 17
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the official OpenJDK 17 image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/hospitalgalvez-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]