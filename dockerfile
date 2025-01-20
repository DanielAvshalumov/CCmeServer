FROM gradle:8.10.2-jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle /app/

COPY src /app/src/

RUN gradle clean build -x test

FROM openjdk:21-jdk

ENV AWS_REGION=us-east-2
ENV AWS_ACCESS_KEY=AKIATFYQCLRLOU3OLJ7Q
ENV AWS_SECRET_KEY=5Sk/jdtQAd9A01iIe/NY3jjHiqrLVjT2vMvquSoz
ENV GOOGLE_PUBLIC_KEY=AIzaSyCEwrq-mfDzjMutnfI2uZFSY7DsUZdLlNo

WORKDIR /app

COPY --from=builder /app/build/libs/your-spring-boot-app.jar /app/your-spring-boot-app.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/your-spring-boot-app.jar"]