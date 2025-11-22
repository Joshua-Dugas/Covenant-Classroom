FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY src ./src
COPY build.gradle build.gradle
COPY gradle/ ./gradle 
COPY gradlew gradlew
RUN ./gradlew bootjar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
