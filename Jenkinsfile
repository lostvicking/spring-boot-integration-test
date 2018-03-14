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
            }
        }
    }
}
