FROM tomcat:9-jdk17

COPY . /usr/local/tomcat/webapps/ROOT

EXPOSE 8080
