# build from Dockerfile:
# docker build -t mysql-cucumber
# docker run -p 3306:3306 -d --name mysql-container  -e MYSQL_ROOT_PASSWORD=devpassword mysql-cucumber
# docker exec -it mysql-container /bin/bash
# mysql -u springuser -pThePassword cucumber
