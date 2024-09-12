
# Use an official Java runtime as the base image
FROM openjdk:17-jdk-alpine

# Create a volume directory (optional)
VOLUME /tmp

# Copy the application JAR into the Docker image
COPY target/ms-eureka-server.jar ms-eureka-server.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","ms-eureka-server.jar"]
