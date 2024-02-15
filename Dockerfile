# Use a Java 21 base image
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/item-importer-service.jar app.jar

# Copy the trustStore.jks file to the classpath
COPY trustStore.jks trustStore.jks
#COPY files/technical_challenge_data.csv technical_challenge_data.csv

# Expose the port your application is running on (if applicable)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]