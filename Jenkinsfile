pipeline {
  // run on any agent
  agent any
  // use tools configured in Jenkins
  tools {
    maven 'M3'
  }
  stages {
    // Build stage declaration
    stage('Build') {
      steps {
            // go to dir, execute maven build
            dir(path: 'asynch-request-creator-bdd') {
              sh 'mvn  clean package -DskipTests'
            }
            // go to dir, execute maven build
            dir(path: 'asynch-request-reader-bdd') {
              sh 'mvn  clean package -DskipTests'
            }
            // go to dir, build and tag docker image
            dir(path: 'docker/mysql-image') {
              sh 'docker build -t mysql-cucumber:0.0.1 .'
            }
            // go to dir, build and tag docker image
            dir(path: 'docker/rabbitmq-image') {
              sh 'docker build -t rabbitmq-spring-boot:0.0.1  .'
            }
      }
    }
    // Bring up docker-compose
    stage('Docker-compose') {
      steps {
          // go to dir, run docker-compose up command
          dir(path: 'docker') {
            sh 'docker-compose up -d'
          }

          // sleep to wait for all services to come up
          sleep 120
          // print message to screen
          echo 'Done sleeping, services should be available now!'
      }
    }
    // Run tests stage, testing would normally happen here
    stage('Run-tests') {
      steps {
        echo '/ *********** *********** *********** /'
        echo '/ *********** Tests are done! *********** /'
        echo '/ *********** *********** *********** /'
        
        // bring down docker-compose
        dir(path: 'docker') {
          sh 'docker-compose down'
        }
      }

    }
    // Deploy stage, deploying out to PROD/STAGE/DEV environment 
    stage('Deploy') {
      steps {
        echo 'Deploying to PROD!'
      }
    }
  }
  // after everything has executed, if there is a failure bring down docker-compose 
  post {
    failure {
      dir(path: 'docker') {
        sh 'docker-compose down'
      }
    }
  }
}
