pipeline {
    agent any

    environment {
        MAVEN_HOME ='/opt/homebrew/Cellar/maven/3.9.9/libexec'
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

       stage('Deploy') {
           steps {
               script {
                   def repoId = "internal.repo"
                   def repoUrl = "http://localhost:8081/repository/maven-releases"
                   def repoLayout = "default"

                   sh "mvn clean deploy -DaltDeploymentRepository=${repoId}::${repoLayout}::${repoUrl}"
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
