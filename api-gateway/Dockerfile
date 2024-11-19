# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app
# Add the JAR file for the service
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api-gateway.jar

# Expose the port the service will run on
EXPOSE 9000

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]