FROM openjdk:20-jdk-slim


WORKDIR /app


COPY K8C2-0.0.1-SNAPSHOT .


EXPOSE 8080


CMD ["java", "-jar", "K8C2-0.0.1-SNAPSHOT", "--server.port=8080"]