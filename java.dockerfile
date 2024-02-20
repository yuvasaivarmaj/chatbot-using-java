FROM openjdk:11


COPY YourProject.jar /app/YourProject.jar

COPY mysql-connector-j-8.3.0.jar /app/mysql-connector-j-8.3.0.jar

WORKDIR /app

ENV MYSQL_DATABASE=chat_database
ENV MYSQL_USER=chat_user
ENV MYSQL_PASSWORD=chat_password

EXPOSE 80

CMD ["java", "-cp", "YourProject.jar:mysql-connector-java-8.3.0.jar", "chatbot"]