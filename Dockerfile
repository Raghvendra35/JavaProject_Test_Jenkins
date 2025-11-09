# Use official Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in container
WORKDIR /app

# Copy jar from target directory
COPY target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8000

# Run the jar
ENTRYPOINT ["java", "-Dserver.port=8000", "-jar", "app.jar"]
