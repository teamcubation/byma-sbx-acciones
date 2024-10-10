FROM openjdk:21-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY target/AccionService-0.0.1-SNAPSHOT.jar /app/AccionService.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/AccionService.jar"]


