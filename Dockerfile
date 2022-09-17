FROM openjdk:18
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS=""
CMD java $JAVA_OPTS -server -jar app.jar

# docker build -t kotlin-spring-first-sample .
# docker run -d -p 8088:8088 kotlin-spring-first-sample
# docker logs -f 2842c77cd43db2d9b1e59306a9c29c0ff3796bacb72258ba60a573effff9e804
# docker ps
# docker exec -it 2842c77cd43d /bin/bash
# docker stop 2842c77cd43d
