FROM maven:3.5.4-jdk-8-alpine
WORKDIR /app
COPY /Address-Book /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=0 /app/target/Address-Book*.jar /app
EXPOSE 8080
CMD [ "/bin/sh", "-c", "java -jar /app/Address-Book*.jar"]