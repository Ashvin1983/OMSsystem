pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        OPENSHIFT_SERVER = 'https://your-openshift-api-url:6443'
        OPENSHIFT_TOKEN = credentials('sha256~NxVrFSKZpvX-9OSuo1hF_goB8BfPQTmihZoJfDXnGZo')
        NAMESPACE=omssystem
        IMAGE_NAME = 'kreeyaj'
        IMAGE_TAG = '1.0' // You can replace with BUILD_NUMBER for dynamic
        QUAY_REPO = "quay.io/ashvinbharda"
        DOCKER_IMAGE = "${QUAY_REPO}/${IMAGE_NAME}:${IMAGE_TAG}"
    }
    stages {

         stage('Checkout Code') {
                   steps {
                   cho '🧪 checkout code...'
                        git branch: 'dev-branch', url: 'https://github.com/Ashvin1983/OMSsystem.git'
                   }
               }
        stage('Build JAR with Maven') {
            steps {
                echo '🧪 Building JAR file...'
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
        }
       stage('Login to OpenShift') {
                   steps {
                       sh "oc login ${OPENSHIFT_SERVER} --token=${OPENSHIFT_TOKEN} --insecure-skip-tls-verify=true"
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
         stage('Tag Image') {
                    steps {
                        // This assumes the image is already built and pushed to OpenShift image stream
                        sh "oc tag ${NAMESPACE}/${IMAGE_TAG} ${NAMESPACE}/${DEPLOYMENT_NAME}:latest"
                    }
                }
                stage('Trigger Deployment') {
                    steps {
                        sh "oc rollout restart deployment/${DEPLOYMENT_NAME} -n ${NAMESPACE}"
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
