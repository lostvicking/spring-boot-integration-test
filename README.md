# spring-boot-integration-test
Integration tests for Spring Boot microservices using Docker

There are 2 microservices, one RabbitMQ Docker image and one MySQL Docker image.

The asynch-request-creator-bdd microservice exposes an endpoint /create-request that accepts
POST requests in JSON format. The request is put on the 'spring-boot' queue.

The asynch-request-reader-bdd registers a RabbitListener which receives the message
from RabbitMQ and persists it to a MySQL database.

The creator service has intergration tests written with Cucumber which is BDD testing framework.

To run the integration tests:
/docker folder cotains the docker-compose that has all the services put together
```
docker-compose up
```

Then from the directory asynch-request-creator-bdd/ run Maven integration tests:
```
mvn verify
```
