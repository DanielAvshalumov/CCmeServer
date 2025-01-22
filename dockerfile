FROM gradle:8.10.2-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle /app/

COPY src /app/src/

RUN gradle clean build -x test

FROM openjdk:21-jdk


WORKDIR /app

COPY --from=builder /app/build/libs/your-spring-boot-app.jar /app/your-spring-boot-app.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/your-spring-boot-app.jar"]