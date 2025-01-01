
FROM gradle:7.6-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle gradle/
RUN gradle build --no-daemon
COPY src ./src
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/your-app.jar /app/your-app.jar

ENTRYPOINT ["java", "-jar", "/app/your-app.jar"]

EXPOSE 8080