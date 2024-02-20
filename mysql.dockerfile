# Use an official MySQL image as a parent image
FROM mysql:latest

# Environment variables
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=chat_database
ENV MYSQL_USER=chat_user
ENV MYSQL_PASSWORD=chat_password

# Expose MySQL port
EXPOSE 3306
