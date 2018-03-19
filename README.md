# spring-boot-integration-test
Integration tests for Spring Boot microservices using Docker

There are 2 microservices, one RabbitMQ Docker image and one MySQL Docker image.

The asynch-request-creator-bdd microservice exposes an endpoint /create-request that accepts
POST requests in JSON format. The request is put on the 'spring-boot' queue.

The asynch-request-reader-bdd registers a RabbitListener which receives the message
from RabbitMQ and persists it to a MySQL database.

The creator service has intergration tests written with Cucumber which is BDD testing framework.

To run the integration tests:
Compile both microservices
```
mvn clean install -DskipTests
```


/docker folder cotains the docker-compose that has all the services put together
in /docker/mysql-image create Docker image for MySQL DB, call it mysql-cucumber
```
docker build -t mysql-cucumber .
```

in /docker/rabbitmq-image create Docker image for RabbitMQ, call it rabbitmq-spring-boot
```
docker build -t rabbitmq-spring-boot .
```

In /docker run docke-compose to bring up the microservice, MySQL and RabbitMQ together
```
docker-compose up
```

Then from the directory asynch-request-creator-bdd/ run Maven integration tests:
```
mvn verify
```

Decision to go with Jenkins inside a Docker container motivated by the availability  
of tools like wget and curl in Linux which are useful when doing development.

Start Jenkins Blue Ocean is Docker using:  
´´´
docker run ^
  --rm ^
  -u root ^
  -p 8080:8080 ^
  -v jenkins-data:/var/jenkins_home ^
  -v /var/run/docker.sock:/var/run/docker.sock ^
  -v "%HOMEPATH%":/home ^
  jenkinsci/blueocean
  ´´´
