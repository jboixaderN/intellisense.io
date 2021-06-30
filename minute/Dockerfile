FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/minute-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} minute.jar
ENTRYPOINT ["java","-jar","minute.jar"]