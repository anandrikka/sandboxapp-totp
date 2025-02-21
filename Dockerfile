# Build frontend
FROM node:20.16.0-slim AS fe_build
WORKDIR /app
COPY ui/package*.json .
RUN npm install
COPY ui .
RUN npm run build

# Build java image
FROM maven:3.9.9-eclipse-temurin-21-alpine AS be_build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN rm -rf src/main/resources/static
COPY --from=fe_build /app/dist/ui/browser src/main/resources/static
RUN mvn clean package -DskipTests

# Final State
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=be_build /app/target/totp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]