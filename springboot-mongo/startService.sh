#!/bin/sh
touch /deployments/app.jar

java -jar /deployments/app.jar > /opt/app/log/output.log
