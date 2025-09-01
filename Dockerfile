# Stage 1: Build the app
FROM gradle:8.3.3-jdk21 AS build
WORKDIR /app

# Copy Gradle files first (for caching dependencies)
COPY build.gradle.kts settings.gradle.kts gradle.properties ./
COPY gradlew ./
COPY gradle ./gradle

# Copy source code
COPY src ./src

# Build the JAR
RUN ./gradlew clean build -x test

# Stage 2: Run the app
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Use Railway PORT env variable
ENV SERVER_PORT=${PORT:8080}

# Run the JAR
CMD ["java", "-jar", "app.jar"]
