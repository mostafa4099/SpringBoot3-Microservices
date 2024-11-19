# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app
# Add the JAR file for the service
ARG JAR_FILE=target/notification-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} notification-service.jar

# Expose the port the service will run on
EXPOSE 8086

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "notification-service.jar"]