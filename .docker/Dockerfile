FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/accompany-0.0.1.jar
ENV TZ=Asia/Seoul \
ACTIVE_PROFILE=${ACTIVE_PROFILE}
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-jar", "/app.jar"]
