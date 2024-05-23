# Use an official Maven image to build the app
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the app
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/thea-security-oauth.jar app.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

#Running this container with docker run
#docker run -p 8080:8080 \
#  -e DB_HOST=mysql \
#  -e DB_PORT=3306 \
#  -e DB_NAME=oauthdb \
#  -e DB_USERNAME=your_username \
#  -e DB_PASSWORD=your_password \
#  -e GOOGLE_CLIENT_ID=your-google-client-id \
#  -e GOOGLE_CLIENT_SECRET=your-google-client-secret \
#  your-application-name
