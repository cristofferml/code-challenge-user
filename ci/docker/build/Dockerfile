FROM adoptopenjdk:11-jre-openj9
RUN mkdir -p /home/root/code-challenge/jar
COPY ./jar/*-SNAPSHOT.jar /home/root/code-challenge/jar/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/home/root/code-challenge/jar/app.jar"]