FROM openjdk:19-jdk

WORKDIR /app

COPY target/queueOverFlow-0.0.1-SNAPSHOT.jar /app/queueOverFlow.jar

EXPOSE 8080

CMD ["java", "-jar", "queueOverFlow.jar"]