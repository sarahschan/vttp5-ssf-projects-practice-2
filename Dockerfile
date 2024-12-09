FROM openjdk:23-jdk AS builder


ARG APP_DIR=/app
WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src
COPY events.json .
# Need to copy events.json to the workdir


RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true


## Stage 2 ##
FROM openjdk:23-jdk

ARG DEPLOY_DIR=/app
WORKDIR ${DEPLOY_DIR}

COPY --from=builder /app/target/eventmanagement-0.0.1-SNAPSHOT.jar ssf-practice-assessment-2.jar
COPY --from=builder /app/events.json .
# Need to copy events.json to the final image

ENV SERVER_PORT=8888
EXPOSE ${SERVER_PORT}

ENTRYPOINT java -jar ssf-practice-assessment-2.jar




