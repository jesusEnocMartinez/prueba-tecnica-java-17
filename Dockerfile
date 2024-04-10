# Stage 1: Define the Maven build environment
FROM maven:3.8.4-openjdk-17-slim AS maven
# Set the working directory for the Maven build environment
WORKDIR /maven
# Copy the project's POM file to the working directory
COPY pom.xml .
# Download project dependencies to enable offline builds
RUN mvn dependency:go-offline
# Stage 2: Build the application
FROM maven AS build
# Set the working directory for building the application
WORKDIR /src
# Copy the entire project directory to the working directory
COPY . .
# Clean and package the application, skipping tests
RUN mvn clean package -DskipTests
# Stage 3: Create the final runtime image
FROM openjdk:17-jdk-alpine AS final
# Set the working directory for the final image
WORKDIR /app
# Copy the compiled JAR file from the build stage to the final image
COPY --from=build /src/target/*.jar app.jar
# Create a group and user with appropriate permissions for running the application
RUN addgroup -g 2000 webadmin
RUN adduser -D -u 2000 -G webadmin webadmin
RUN chown -R 2000:2000 /app/app.jar
# Set the user context to the newly created user
USER webadmin
# Expose port 8090 for the application
EXPOSE 8090
# Set the entry point to run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
