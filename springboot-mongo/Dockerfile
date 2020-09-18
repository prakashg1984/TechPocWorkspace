FROM fabric8/java-jboss-openjdk8-jdk:1.2.3

ENV JAVA_APP_JAR app.jar
ENV AB_ENABLED off
ENV AB_JOLOKIA_AUTH_OPENSHIFT true
ENV JAVA_OPTIONS -Xmx256m

EXPOSE 8080

USER root
VOLUME /opt/app/log
RUN chmod -R 777 /deployments/
RUN mkdir -p /opt/app/log/
COPY startService.sh /deployments/startService.sh
RUN chmod 777 /deployments/startService.sh

ADD target/springboot-mongo-1.0-SNAPSHOT.jar /deployments/app.jar
ENTRYPOINT ["/bin/bash", "/deployments/startService.sh"]
#CMD sh ./deployments/startService.sh

