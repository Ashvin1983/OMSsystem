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
                echo '⚙️ Running Build...'
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Running tests...'
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Deploy to Quay') {
            steps {
                script {
                    echo '🚀 Deploying to Quay...'

                    // Use withMaven only if plugin is installed; otherwise use shell directly
                    // Uncomment this if withMaven is working:
                    // withMaven(maven: 'Maven 3.9.9') {
                    def repoId = "internal.repo"
                    def repoUrl = "https://console.redhat.com/quay/repository/ashvinbharda/kreeyaj"
                    def repoLayout = "default"
                    sh "${MAVEN_HOME}/bin/mvn deploy -DaltDeploymentRepository=${repoId}::${repoLayout}::${repoUrl}"
                    // }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build and deployment successful!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
