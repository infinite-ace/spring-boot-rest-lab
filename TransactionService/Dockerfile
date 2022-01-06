FROM maven as builder

COPY . /project
WORKDIR /project
RUN mvn --settings /usr/share/maven/ref/settings-docker.xml clean install
RUN mkdir -p /project/target/docker_lab

FROM openjdk:11 as runtime

WORKDIR /tmp/
RUN mkdir docker_lab
COPY --from=builder /project/target/docker_lab docker_lab

WORKDIR /app
COPY --from=builder /project/target/demo-0.0.1-SNAPSHOT.jar .

EXPOSE 8024

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "demo-0.0.1-SNAPSHOT.jar"]
