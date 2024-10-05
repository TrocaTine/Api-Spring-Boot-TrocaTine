FROM openjdk:17-jdk-slim
COPY --from=build /target/Api-Spring-Boot-TocaTine-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
