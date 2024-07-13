pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build -x test'
                sh 'cd ./build/libs && ls -l'
            }
        }
        stage('Verify Vegeta Installation') {
            steps {
                sh 'vegeta --version'
            }
        }
        stage('Prepare Targets') {
            steps {
                writeFile file: 'target.txt', text: '''GET http://127.0.0.1:9090/api/tests/results
GET http://127.0.0.1:9090/api/branches
GET http://127.0.0.1:9090/api/endpoints/1
GET http://127.0.0.1:9090/api/endpoints
GET http://127.0.0.1:9090/api/status
GET http://127.0.0.1:9090/api/auth/users/1
GET http://127.0.0.1:9090/api/auth/users
GET http://127.0.0.1:9090/api/docs/1
GET http://127.0.0.1:9090/api/docs'''
            }
        }
        stage('Load Testing') {
            steps {
                script {
                    sh 'vegeta attack -targets=target.txt -rate=10/s -duration=30s | tee results.bin | vegeta report'
                }
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
}
