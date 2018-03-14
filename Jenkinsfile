pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir ('asynch-request-creator-bdd') {
                  bat 'mvn  clean package'
                }
                dir ('asynch-request-reader-bdd'){
                  bat 'mvn  clean package'
                }
                dir ('docker/mysql-image'){
                  bat 'docker build -t mysql-cucumber .'
                }
                dir ('docker/rabbitmq-image'){
                  bat 'docker build -t rabbitmq-spring-boot .'
                }
            }
        }
    }
}
