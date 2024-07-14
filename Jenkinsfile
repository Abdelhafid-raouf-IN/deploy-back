pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
    }
    stages {
        stage('Build') {
            steps {
                bat 'gradle build -x test'
                bat 'cd ./build/libs && dir'
            }
        }
        stage('Load Test') {
            steps {
                bat 'call attack.bat'
            }
        }
        /*stage('Publish') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials-id', usernameVariable: 'ARTIFACTORY_USER', passwordVariable: 'ARTIFACTORY_PASSWORD')]){
                    sh """
                        gradle publish
                    """
                }
            }
        }*/
    }
    post {
        always {
            // Archive the test results
            archiveArtifacts artifacts: 'results.json, plot.html', allowEmptyArchive: true
            publishHTML (target: [
                reportName : 'Vegeta Load Test Report',
                reportDir  : '.',
                reportFiles: 'plot.html'
            ])
        }
    }
}
