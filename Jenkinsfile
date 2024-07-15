pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
        ACTUATOR_URL = 'http://192.168.10.165:9090/actuator'
    }
    stages {
        stage('Check Vegeta') {
            steps {
                sh 'vegeta -version'
            }
        }
        stage('Build') {
            steps {
                sh 'gradle build -x test'
                sh 'cd ./build/libs && ls -l'
            }
        }
        stage('Load Test') {
            steps {
                sh 'chmod +x attack.sh'
                sh './attack.sh'
                sh 'ls -l plot.html'  // Vérifie que plot.html est bien présent
                sh 'ls -l plot.png'   // Vérifie que plot.png est bien présent
            }
        }
        stage('Health Check') {
            steps {
                script {
                    def healthResponse = sh(script: "curl -s ${ACTUATOR_URL}/health", returnStdout: true).trim()
                    echo "Health Check Response:"
                    echo "${healthResponse}"
                }
            }
        }
        stage('Metrics') {
            steps {
                script {
                    def metricsResponse = sh(script: "curl -s ${ACTUATOR_URL}/metrics", returnStdout: true).trim()
                    echo "Metrics Response:"
                    echo "${metricsResponse}"
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
    post {
        always {
            archiveArtifacts artifacts: 'results.json, plot.html, plot.png', allowEmptyArchive: true
            publishHTML (target: [
                reportName : 'Vegeta Load Test Report',
                reportDir  : '.',
                reportFiles: 'plot.html',
                keepAll    : true,
                alwaysLinkToLastBuild: true
            ])
            publishHTML (target: [
                reportName : 'Vegeta Load Test Graph',
                reportDir  : '.',
                reportFiles: 'plot.png',
                keepAll    : true,
                alwaysLinkToLastBuild: true
            ])
        }
    }
}
