pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        IMAGE_NAME = 'kreeyaj'
        IMAGE_TAG = '1.0' // You can replace with BUILD_NUMBER for dynamic
        QUAY_REPO = "quay.io/ashvinbharda"
        DOCKER_IMAGE = "${QUAY_REPO}/${IMAGE_NAME}:${IMAGE_TAG}"
    }
    stages {

        stage('Checkout Code') {
            steps {
                echo '🔁 Checking out code from GitHub...'
                git 'https://github.com/Ashvin1983/OMSsystem.git'
            }
        }
        stage('Build JAR with Maven') {
            steps {
                echo '🧪 Building JAR file...'
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
        }
         stage('Checkout') {
             steps {
                 checkout scm
             }
         }
       stage('Build Docker Image') {
           steps {
                echo "🐳 Building Docker image from docker/Dockerfile"
                sh "docker build -t ${DOCKER_IMAGE} -f docker/Dockerfile ."
            }
       }
        stage('Push to Quay.io') {
            steps {
                echo "🚀 Pushing Docker image to Quay: ${DOCKER_IMAGE}"
                withCredentials([usernamePassword(credentialsId: 'quay-creds', usernameVariable: 'QUAY_USER', passwordVariable: 'QUAY_PASS')]) {
                    sh '''
                      echo "$QUAY_PASS" | docker login -u "$QUAY_USER" --password-stdin quay.io
                      docker push ${DOCKER_IMAGE}
                     '''
                }
            }
        }
        stage('Deploy to OpenShift') {
            steps {
                echo '🌐 Deploying to OpenShift...'
                sh """
                    oc delete pod omssystem || true
                    oc delete service omssystem || true
                    oc run omssystem --image=${DOCKER_IMAGE} --port=8080
                    oc expose pod omssystem --port=8080
                """
            }
        }
    }

    post {
        success {
            echo '✅ CI/CD pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Please check logs above.'
        }
    }
}
