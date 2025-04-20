FROM openjdk:17-jdk
# Set the working directory in the container to /app
WORKDIR /app

# Copy the JAR file from the target directory to the /app directory in the container
COPY target/OMSsystem-1.0.0.jar.original /app/OMSsystem-1.0.0.jar

# Set the entry point to run the JAR file using Java
ENTRYPOINT ["java", "-jar", "/app/OMSsystem-1.0.0.jar"]

