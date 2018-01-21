FROM java:8u111-jdk-alpine

ADD target/asynch-request-creator-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
