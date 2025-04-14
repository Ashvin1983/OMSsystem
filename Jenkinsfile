pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        OPENSHIFT_SERVER = 'https://api.crc.testing:6443'
        OPENSHIFT_TOKEN = credentials('openshift-token') // Jenkins Secret Text
        NAMESPACE = "omssystem"
        IMAGE_NAME = 'kreeyaj'
        IMAGE_TAG = '1.0' // Replace with "${BUILD_NUMBER}" if you want unique tags
        QUAY_REPO = "quay.io/ashvinbharda"
        DOCKER_IMAGE = "${QUAY_REPO}/${IMAGE_NAME}:${IMAGE_TAG}"
        DEPLOYMENT_NAME = "kreeyaj" // 🛠️ Add this to avoid undefined variable error
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo '🧪 Checking out code...'
                git branch: 'dev-branch', url: 'https://github.com/Ashvin1983/OMSsystem.git'
            }
        }

        stage('Build JAR with Maven') {
            steps {
                echo '📦 Building JAR file...'
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
        }

        stage('Login to OpenShift') {
            steps {
                echo '🔐 Logging into OpenShift...'
                sh 'oc version'
                sh "oc login ${OPENSHIFT_SERVER} --token=${OPENSHIFT_TOKEN} --insecure-skip-tls-verify=true"
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "🐳 Building Docker image from docker/Dockerfile..."
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

        stage('Update Image in Deployment') {
            steps {
                echo '🛠️ Updating OpenShift deployment image...'
                sh "oc set image deployment/${DEPLOYMENT_NAME} ${DEPLOYMENT_NAME}=${DOCKER_IMAGE} -n ${NAMESPACE}"
            }
        }

        stage('Trigger Rollout') {
            steps {
                echo '🔄 Triggering OpenShift rollout...'
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
