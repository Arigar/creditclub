FROM java:8-jre
MAINTAINER test

ADD ./target/auth-server-1.0.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/auth-server-1.0.0.jar"]

EXPOSE 8082