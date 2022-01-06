#FROM openjdk:17
#
#WORKDIR /app
#
#COPY target/demo-0.0.1-SNAPSHOT.jar /app
#
#EXPOSE 8007
#
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "demo-0.0.1-SNAPSHOT.jar"]
#

FROM maven as builder

#ARG EXTRA_MVN_FLAGS
#ENV ENV_EXTRA_MVN_FLAGS=$EXTRA_MVN_FLAGS

COPY . /project
WORKDIR /project
RUN mvn --settings /usr/share/maven/ref/settings-docker.xml clean install
RUN mkdir -p /project/target/docker_lab
#RUN mkdir -p /project/target/failsafe-reports

#final stage - copy artifacts and tests
FROM openjdk:11 as runtime

WORKDIR /tmp/
RUN mkdir docker_lab
COPY --from=builder /project/target/docker_lab docker_lab

WORKDIR /app
COPY --from=builder /project/target/demo-0.0.1-SNAPSHOT.jar .

EXPOSE 8007

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "demo-0.0.1-SNAPSHOT.jar"]
