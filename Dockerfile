FROM openjdk:8-jdk-alpine
COPY apache-maven-3.8.4-bin.tar.gz /tmp/

# Install dependencies
RUN apk update && apk add --no-cache bash

# Extract Maven binary distribution and create symbolic links
RUN tar xf /tmp/apache-maven-3.8.4-bin.tar.gz -C /opt \
    && ln -s /opt/apache-maven-3.8.4 /opt/maven \
    && ln -s /opt/maven/bin/mvn /usr/local/bin \
    && rm -f /tmp/apache-maven-3.8.4-bin.tar.gz

# Set Maven home environment variable
ENV MAVEN_HOME /opt/maven

VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
WORKDIR /app
COPY mysql-connector-j-8.3.0.jar .
COPY . .
RUN mvn clean install
EXPOSE 3000
CMD ["java", "-jar", "chatbotapp.jar"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar chatbotusingjava.jar
