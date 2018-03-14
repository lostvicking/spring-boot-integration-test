pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'cd asynch-request-creator-bdd'
                sh 'mvn  clean package'
                sh 'cd ..'
                sh 'cd asynch-request-reader-bdd'
                sh 'mvn  clean package'
            }
        }
    }
}
