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
        bat 'docker run -d --rm -p 8010:8010  vic/asynch-request-creator'
        bat 'docker run -d --rm -p 8020:8020  vic/asynch-request-reader'
        bat 'docker run -d --rm -p 5672:5672 -p 15672:15672 rabbitmq-spring-boot'
        bat 'docker run -d --rm -p 3306:3306  mysql-cucumber'
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
