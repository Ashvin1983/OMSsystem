FROM FROM arm64v8/openjdk:11-jre-slim

WORKDIR /app

COPY target/OMSsystem-1.0.0.jar /app/

ENTRYPOINT ["java", "-cp", "OMSsystem-1.0.0.jar", "OMSsystem.OMSsystemApplication"]