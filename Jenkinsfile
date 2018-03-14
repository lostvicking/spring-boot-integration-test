pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir ('asynch-request-creator-bdd') {
                  sh 'mvn  clean package'
                }
                dir ('asynch-request-reader-bdd'){
                  sh 'mvn  clean package'
                }
                dir ('docker/mysql-image'){
                  sh 'docker build -t mysql-cucumber .'
                }
                dir ('docker/rabbitmq-image'){
                  sh 'docker build -t rabbitmq-spring-boot .'
                }
            }
        }
    }
}
