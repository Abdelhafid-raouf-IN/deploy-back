pipeline {
    agent any

    environment {
        // Définir les variables d'environnement pour Nexus
        NEXUS_URL = 'http://localhost:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-credentials-id'
        VEGETA_VERSION = '12.0.1' // Spécifiez la version de Vegeta que vous souhaitez utiliser
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt contenant le code source
                git 'https://votre-depot-git.com/votre-repository.git'
            }
        }

        stage('Build') {
            steps {
                // Construire le projet avec Gradle, en excluant les tests
                sh 'gradle build -x test'
                sh 'cd ./build/libs && ls -l'
            }
        }

        stage('Publish') {
            steps {
                // Publier les artefacts construits dans Nexus
                withCredentials([usernamePassword(credentialsId: "${NEXUS_CREDENTIALS_ID}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD')]){
                    sh """
                        gradle publish -PnexusUrl=${NEXUS_URL} -PnexusUsername=${NEXUS_USER} -PnexusPassword=${NEXUS_PASSWORD}
                    """
                }
            }
        }

        stage('Install Vegeta') {
            steps {
                // Télécharger et installer Vegeta
                sh """
                wget https://github.com/vegeta/vegeta/releases/download/v${VEGETA_VERSION}/vegeta-linux-amd64-${VEGETA_VERSION}.tar.gz
                tar xvf vegeta-linux-amd64-${VEGETA_VERSION}.tar.gz
                mv vegeta /usr/local/bin/
                """
            }
        }

        stage('Run Load Test') {
            steps {
                // Exécuter les tests de charge avec Vegeta
                sh """
                vegeta attack -duration=30s -rate=10 -targets=src/main/java/unibank/service/pilot/vegeta/requests.txt | vegeta report
                """
            }
        }

        stage('Publish Test Results') {
            steps {
                // Publier les résultats du test
                sh """
                vegeta report -type=text > results.txt
                cat results.txt
                """
                archiveArtifacts artifacts: 'results.txt', allowEmptyArchive: true
            }
        }

        stage('Notify Results') {
            steps {
                // Envoyer des notifications par email en cas de succès ou d'échec des tests
                script {
                    def emailSubject = currentBuild.result == 'SUCCESS' ? "Tests de charge réussis" : "Échec des tests de charge"
                    def emailBody = currentBuild.result == 'SUCCESS' ?
                        "Les tests de charge avec Vegeta ont réussi. Consultez les résultats dans Jenkins." :
                        "Les tests de charge avec Vegeta ont échoué. Consultez les résultats dans Jenkins."

                    emailext (
                        to: 'abdelhafidpfe@gmail.com',
                        subject: emailSubject,
                        body: emailBody,
                        attachLog: true,
                        compressLog: true
                    )
                }
            }
        }
    }

    post {
        always {
            // Actions à effectuer à la fin du pipeline, qu'il réussisse ou échoue
            cleanWs()  // Nettoyer l'espace de travail après l'exécution du pipeline
        }
    }
}
