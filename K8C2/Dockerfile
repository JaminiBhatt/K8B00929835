FROM openjdk:20-jdk-slim


WORKDIR /app


COPY target/DockerContainer2-0.0.1-SNAPSHOT.jar .


EXPOSE 8080


CMD ["java", "-jar", "DockerContainer2-0.0.1-SNAPSHOT.jar", "--server.port=8080"]