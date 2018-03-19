pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
          dir(path: 'asynch-request-creator-bdd') {
            sh 'mvn  clean package'
          }
          dir(path: 'asynch-request-reader-bdd') {
            sh 'mvn  clean package'
          }
          dir(path: 'docker/mysql-image') {
            sh 'docker build -t mysql-cucumber .'
          }
          dir(path: 'docker/rabbitmq-image') {
            sh 'docker build -t rabbitmq-spring-boot .'
          }
      }
    }
    stage('Docker-compose') {
      steps {
        dir(path: 'docker') {
          bat 'docker-compose up -d'
        }
        waitUntil {
          script {
           sh script: 'curl http://asynch-request-creator:8010/actuator/health | grep UP -c > status'
           def r = readFile('status').trim()
           echo 'curl and grep output is ' + r
           return (r == 1);
         }
        }
        sleep 90
        echo 'Done sleeping!'
      }
    }
    stage('Run-tests') {
      steps {
        echo 'RUN TESTS HERE!'
      }

    }
  }
}
