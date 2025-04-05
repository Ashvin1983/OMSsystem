pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
    }

    stages {
        stage('Checkout') {
            steps {
                echo '🔁 Checking out code...'
                git 'https://github.com/Ashvin1983/OMSsystem.git'
            }
        }

        stage('Build') {
            steps {
                script {
                 echo '🧪 Running Build...'
                    sh "${MAVEN_HOME}/bin/mvn clean package"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                     echo '🧪 Running tests...'
                    sh "${MAVEN_HOME}/bin/mvn test"
                }
            }
        }

        stage('Install Locally') {
            steps {
                script {
                echo '🧪 Running Install ...'
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
