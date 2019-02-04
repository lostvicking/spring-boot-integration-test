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

# Jenkins:
build java app: https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/

run Jenkins docker:
```docker run \
  --rm \
  -u root \
  -p 8080:8080 \
  -p 8010:8010 \
  -v jenkins-data:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v "$HOME":/home \
  jenkinsci/blueocean
```

## install docker compose in jenkins image
Attached to the image an run the following:
```
export GLIBC="2.28-r0"

apk update && apk add --no-cache openssl ca-certificates curl libgcc && \
    curl -fsSL -o /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub && \
    curl -fsSL -o glibc-$GLIBC.apk https://github.com/sgerrand/alpine-pkg-glibc/releases/download/$GLIBC/glibc-$GLIBC.apk && \
    apk add --no-cache glibc-$GLIBC.apk && \
    ln -s /lib/libz.so.1 /usr/glibc-compat/lib/ && \
    ln -s /lib/libc.musl-x86_64.so.1 /usr/glibc-compat/lib && \
    ln -s /usr/lib/libgcc_s.so.1 /usr/glibc-compat/lib && \
    rm /etc/apk/keys/sgerrand.rsa.pub glibc-$GLIBC.apk
curl -L "https://github.com/docker/compose/releases/download/1.23.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

# CURL commands to test asynch-request-creator-bdd:
To install cURL
```
apk update
apk add curl
```

cURL command for correct rtequest:
```
curl --header "Content-Type: application/json"   --request POST   --data '{"content":"My JSON request"}'   http://asynch-request-creator:8010/create-request
```

cURL command for malformed request:
```
curl --header "Content-Type: application/json"   --request POST   --data 'Malformed request'   http://asynch-request-creator:8010/create-request
```


# Running Docker container with Java8, Maven and Git:
```
docker run -i -t --name docker-java8-maven --rm jamesdbloom/docker-java8-maven
```
Optionally, install nano for editing:
```
apt-get install nano
```

Configure Git user:
```
git config --global user.email "you@example.com"
git config --global user.name "Your Name"
```