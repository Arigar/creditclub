FROM java:8-jre
MAINTAINER test

ADD ./target/zuul-server-1.0.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/zuul-server-1.0.0.jar"]

EXPOSE 8080