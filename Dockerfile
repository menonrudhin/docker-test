FROM scratch
FROM openjdk:17-oracle
ARG JAR_FILE=target/kafkaclient-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} kafkaclient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kafkaclient-0.0.1-SNAPSHOT.jar"]
