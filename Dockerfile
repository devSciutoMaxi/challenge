FROM eclipse-temurin:17
COPY src/main/docker/challenge-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]
