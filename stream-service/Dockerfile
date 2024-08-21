# Dockerfile
FROM openjdk:17-jdk-alpine

# Argument to copy the jar file
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Set the profile to docker by default
ENV SPRING_PROFILES_ACTIVE=docker

# Start the application with the docker profile
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expose port
EXPOSE 9007