FROM openjdk:11
ADD target/admin-service.jar admin-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "admin-service.jar"]