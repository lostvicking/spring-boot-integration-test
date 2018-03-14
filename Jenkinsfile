pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir ('cd asynch-request-creator-bdd') {
                  bat 'mvn  clean package'
                }
                dir ('cd asynch-request-reader-bdd'){
                  bat 'mvn  clean package'
                }
            }
        }
    }
}
