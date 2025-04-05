pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Ashvin1983/OMSsystem.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn clean package"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn test"
                }
            }
        }

        stage('Install Locally') {
            steps {
                script {
                    sh "${MAVEN_HOME}/bin/mvn clean install"
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
