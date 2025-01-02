# Use a Maven base image to build the project
FROM maven:3.8.7-openjdk-18-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml /app/
COPY src /app/src/

# Build the application (this will create the .jar file in target/)
RUN mvn clean install -DskipTests

# Use a smaller OpenJDK runtime image for the final container
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the compiled .jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (default Spring Boot port is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]