pipeline {
    agent any

    environment {
        MAVEN_HOME ='opt/homebrew/bin/mvn'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Ashvin1983/OMSsystem.git'  // Replace with your Git repo URL
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "${MAVEN_HOME} clean package"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh "${MAVEN_HOME} test"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn deploy"
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
