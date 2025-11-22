# Stage 1: Build the App
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY src ./src
COPY build.gradle build.gradle
COPY gradle/ ./gradle
COPY gradlew gradlew
RUN ./gradlew bootJar


# Stage 2: Run the app
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar covenant-classroom.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "covenant-classroom.jar"]
