pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:9091/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build -x test'
                sh 'cd ./build/libs && ls -l'
            }
        }
    }


}
