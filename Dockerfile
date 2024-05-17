FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
ARG JAR_FILE=build/libs/storm_score_new-1.0-SNAPSHOT.war
COPY ${JAR_FILE} storm_score_new.war

ENTRYPOINT [ "java", "-jar", "/storm_score_new.war", ">", "application.log", "2>&1"]