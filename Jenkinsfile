pipeline {
    agent any

    tools {
        jdk 'JDK21'
        gradle 'Gradle'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Abdelhafid-raouf-IN/deploy-back.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Déploiement en cours...'
            }
        }
    }

    post {
        success {
            echo 'Build et tests réussis !'
        }
        failure {
            echo 'Le build ou les tests ont échoué.'
        }
    }
}
