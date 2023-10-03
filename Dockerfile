FROM eclipse-temurin:17
COPY target/challenge-0.0.1-SNAPSHOT.jar java-app.jar
ENTRYPOINT ["java", "-jar", "java-app.jar"]
