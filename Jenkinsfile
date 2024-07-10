pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:9091/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'chmod +x gradlew'

            }
        }
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Publish') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${NEXUS_CREDENTIALS_ID}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh './gradlew publish -PnexusUsername=$NEXUS_USER -PnexusPassword=$NEXUS_PASSWORD'
                }
            }
        }
        stage('Push to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${NEXUS_CREDENTIALS_ID}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]) {
                    sh 'curl -u $NEXUS_USER:$NEXUS_PASSWORD --upload-file build/libs/pilot-0.0.1-SNAPSHOT.jar $NEXUS_URL/your-artifact.jar'
                }
            }
        }
    }

    post {
        success {
            echo 'Backend Build Success!'
        }
        failure {
            echo 'Backend Build Failed!'
        }
    }
}
