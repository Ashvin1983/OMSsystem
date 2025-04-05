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

        /*stage('Install Locally') {
            steps {
                script {
                echo '🧪 Running Install ...'
                    sh "${MAVEN_HOME}/bin/mvn clean install"
                }
            }
        }*/
        stage('Deploy') {
                  steps {
                      withMaven(maven: 'Maven3') {
                          script {
                              def repoId = "internal.repo"
                              def repoUrl = "https://console.redhat.com/quay/repository/ashvinbharda/kreeyaj"
                              def repoLayout = "default"
                              sh "mvn clean deploy -DaltDeploymentRepository=${repoId}::${repoLayout}::${repoUrl}"
                          }
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
