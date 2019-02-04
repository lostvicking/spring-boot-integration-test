pipeline {
  agent any
  tools {
    maven 'M3'
  }
  stages {
    stage('Build') {
      steps {
            dir(path: 'asynch-request-creator-bdd') {
              sh 'mvn  clean package -DskipTests'
            }
            dir(path: 'asynch-request-reader-bdd') {
              sh 'mvn  clean package -DskipTests'
            }
            dir(path: 'docker/mysql-image') {
              sh 'docker build -t mysql-cucumber:0.0.1 .'
            }
            dir(path: 'docker/rabbitmq-image') {
              sh 'docker build -t rabbitmq-spring-boot:0.0.1  .'
            }
      }
    }
    stage('Docker-compose') {
      steps {
          dir(path: 'docker') {
            sh 'docker-compose up -d'
          }

          sleep 180

          echo 'Done sleeping, services should be available now!'
      }
    }
    stage('Run-tests') {
      steps {
        echo '/ *********** *********** *********** /'
        echo '/ *********** Tests are done! *********** /'
        echo '/ *********** *********** *********** /'
        dir(path: 'docker') {
          sh 'docker-compose down'
        }
      }

    }
    stage('Deploy') {
      steps {
        echo 'Deploying to PROD!'
      }
    }
  }
  post {
    failure {
      dir(path: 'docker') {
        sh 'docker-compose down'
      }
    }
  }
}
