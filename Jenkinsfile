pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'cd asynch-request-creator-bdd'
                bat 'mvn  clean package'
                bat 'cd ..'
                bat 'cd asynch-request-reader-bdd'
                bat 'mvn  clean package'
            }
        }
    }
}
