# Init Tailwind config
FROM node:18-alpine AS tailwind-builder

WORKDIR /app

COPY . .

RUN npm i -g npm@10.9.2

RUN npm i
RUN npm run build

FROM openjdk:21-jdk-slim AS builder

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]