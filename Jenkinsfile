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
          sh 'docker-compose up'
        }
        
      }
    }
  }
}