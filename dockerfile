FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-jdk-slim
COPY --from=build /target/ApiTrocaTineSql-0.0.1-SNAPSHOT.jar ApiTrocaTineSql.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","ApiTrocaTineSql.jar"]
