FROM openjdk:17-oracle
ARG JAR_FILE=build/libs/accompany-0.0.1.jar
ENV TZ=Asia/Seoul
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
