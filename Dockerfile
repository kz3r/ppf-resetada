FROM adoptopenjdk/openjdk11:alpine
ARG JAR_PATH=build/libs/ppf-resetada-0.1.1-SNAPSHOT.jar
EXPOSE 8080:8080
RUN mkdir /app
COPY ${JAR_PATH} /app/ppf-resetada.jar
ENTRYPOINT ["java", "-jar", "/app/ppf-resetada.jar"]