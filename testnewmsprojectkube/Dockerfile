FROM fabric8/java-jboss-openjdk8-jdk:1.2.3

ENV JAVA_APP_JAR testnewmsprojectkube-1.0-SNAPSHOT.jar
ENV JAVA_OPTIONS -Xmx256m 

EXPOSE 8080

RUN chmod -R 777 /deployments/
ADD target/testnewmsprojectkube-1.0-SNAPSHOT.jar /deployments/ 

