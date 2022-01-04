# Write docker files for all your applications
# ● Build them using multi-staged builds
# ● Run all of your services in with a docker compose file including all
# dependencies and appropriate routing between them
#
#
#
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
#RUN mkdir failsafe-reports
COPY --from=builder /project/target/docker_lab docker_lab
#COPY --from=builder /project/target/failsafe-reports failsafe-reports

WORKDIR /app
COPY --from=builder /project/target/demo-0.0.1-SNAPSHOT.jar .

EXPOSE 80
ENTRYPOINT ["java", "-jar", "/demo-0.0.1-SNAPSHOT.jar"]
#"Dspring.profiles.active=${ENVIRONMENT:dev}",



#FROM openjdk:11
#
#WORKDIR /app
#
#EXPOSE 8081
#
#CMD []






