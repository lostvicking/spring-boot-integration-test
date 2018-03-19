pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
            dir(path: 'asynch-request-creator-bdd') {
              bat 'mvn  clean package -DskipTests'
            }
            dir(path: 'asynch-request-reader-bdd') {
              bat 'mvn  clean package -DskipTests'
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
            bat 'docker-compose up -d'
          }

          sleep 90

          echo 'Done sleeping, services should be available now!'
      }
    }
    stage('Run-tests') {
      steps {
        dir(path: 'asynch-request-creator-bdd') {
          bat 'mvn  verify'
        }
        echo 'Tests are done!'
      }

    }
    stage('Deploy') {
      steps {
        echo 'Deploying to PROD!'
        bat 'docker -d -p 8010:8010 run vic/asynch-request-creator:latest'
        bat 'docker -d -p 8020:8020 run vic/asynch-request-reader:latest'
        bat 'docker -d -p 5672:5672 -p 15672:15672 run rabbitmq-spring-boot:latest'
        bat 'docker -d -p 3306:3306 run mysql-cucumber:latest'
      }
    }
  }
  post {
       always {
         dir(path: 'docker') {
           bat 'docker-compose down'
         }
       }
   }
}
