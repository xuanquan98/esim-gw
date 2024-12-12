# Use an official OpenJDK image as a base
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/myapp.jar /app/myapp.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]
