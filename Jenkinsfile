pipeline {
    agent any
    environment {
        NEXUS_URL = 'http://localhost:9091/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
        ACTUATOR_URL = 'http://192.168.10.193:9090/actuator'
    }
    stages {
    stage('Verify') {
                steps {
                    script {
                        sh 'pwd'
                        sh 'ls -l'
                    }
                }
            }
        stage('Build') {
            steps {
                sh 'chmod +x gradlew'
                sh 'gradle build -x test'
                sh 'cd ./build/libs && ls -l'
            }
        }
        stage('Load Test') {
            steps {
                sh 'chmod +x attack.sh'
                sh './attack.sh'
                sh 'ls -l plot.html'
                sh 'cp plot.html /home/jenkins/${BUILD_NUMBER}.html'
                sh 'ls -l /home/jenkins'
                sh 'echo http://localhost:9092/report/${BUILD_NUMBER}.html'
            }
        }

        stage('Copy Report') {
            steps {
                sh 'mkdir -p /var/jenkins_home/workspace/unibank.service.testing/lastSuccessfulBuild/artifact/'
                sh 'cp plot.html /var/jenkins_home/workspace/unibank.service.testing/lastSuccessfulBuild/artifact/plot.html'
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
        stage('Publish') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials-id', usernameVariable: 'ARTIFACTORY_USER', passwordVariable: 'ARTIFACTORY_PASSWORD')]){
                    sh 'gradle publish'
                }
            }
        }
    }
}
