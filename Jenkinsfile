pipeline {
    agent any

    stages {
        stage('Checkout Backend') {
            steps {
                git 'https://github.com/Abdelhafid-raouf-IN/deploy-back.git'
            }
        }
        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh './gradlew clean build'
                }
            }
        }
        stage('Archive Backend Artifacts') {
            steps {
                archiveArtifacts artifacts: 'deploy-back/build/libs/*.jar', allowEmptyArchive: true
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
