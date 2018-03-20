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
        dir(path: 'docker') {
          bat 'docker-compose down'
        }
      }

    }
    stage('Deploy') {
      steps {
        sleep 30
        echo 'Deploying to PROD!'
        bat 'docker run -d --rm -p 8010:8010  vic/asynch-request-creator:1.0-SNAPSHOT'
        bat 'docker run -d --rm -p 8020:8020  vic/asynch-request-reader:1.0-SNAPSHOT'
        bat 'docker run -d --rm -p 5672:5672 -p 15672:15672 rabbitmq-spring-boot:latest'
        bat 'docker run -d --rm -p 3306:3306  mysql-cucumber:latest'
      }
    }
  }
  //post {
  //     always {
  //       dir(path: 'docker') {
  //         bat 'docker-compose down'
  //       }
  //     }
  // }
}
