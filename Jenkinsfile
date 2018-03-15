pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
          dir(path: 'asynch-request-creator-bdd') {
            bat 'mvn  clean package'
          }
          dir(path: 'asynch-request-reader-bdd') {
            bat 'mvn  clean package'
          }
          dir(path: 'docker/mysql-image') {
            bat 'docker build -t mysql-cucumber .'
          }
          dir(path: 'docker/rabbitmq-image') {
            bat 'docker build -t rabbitmq-spring-boot .'
          }
      }
    }
    stage('Docker-compose') {
      steps {
        dir(path: 'docker') {
          bat 'docker-compose up'
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
