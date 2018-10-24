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
              bat 'docker build -t mysql-cucumber:0.0.1 .'
            }
            dir(path: 'docker/rabbitmq-image') {
              bat 'docker build -t rabbitmq-spring-boot:0.0.1  .'
            }
      }
    }
    stage('Docker-compose') {
      steps {
          dir(path: 'docker') {
            bat 'docker-compose up -d'
          }

          sleep 120

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
        //sleep 30
        echo 'Deploying to PROD!'
        //bat 'docker run -d --rm -h asynch-request-creator --network=bridge -p 8010:8010  vic/asynch-request-creator:0.0.1'
        //bat 'docker run -d --rm -h asynch-request-reader --network=bridge -p 8020:8020  vic/asynch-request-reader:0.0.1'
        //bat 'docker run -d --rm -h rabbitmq-spring-boot --network=bridge -p 5672:5672 -p 15672:15672 rabbitmq-spring-boot:0.0.1'
        //bat 'docker run -d --rm -h  mysql-server -e MYSQL_ROOT_PASSWORD=devpassword  --network=bridge -p 3306:3306  mysql-cucumber:0.0.1'
      }
    }
  }
  post {
    always {
		dir(path: 'asynch-request-creator-bdd') {
          junit 'target/surefire-reports/*.xml'
        }
 	  
    }
    failure {
      dir(path: 'docker') {
        bat 'docker-compose down'
      }
    }
  }
}
