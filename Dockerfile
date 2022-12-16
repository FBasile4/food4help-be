FROM openjdk:19-alpine
RUN addgroup -S spring && adduser -S spring -G spring
VOLUME /tmp
EXPOSE 8080
ARG DEPENDENCY=target
ADD ${DEPENDENCY}/*.jar appuser.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/appuser.jar"]
