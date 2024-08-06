FROM eclipse-temurin:17-jre-focal
ENV TZ=Asia/Seoul

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]