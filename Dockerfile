FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/AccionService.jar /app/AccionService.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/AccionService.jar"]


