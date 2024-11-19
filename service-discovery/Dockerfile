# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app
# Add the JAR file for the service
ARG JAR_FILE=target/service-discovery-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} service-discovery.jar

# Expose the port the service will run on
EXPOSE 8761

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "service-discovery.jar"]