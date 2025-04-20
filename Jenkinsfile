pipeline {
    agent any

    environment {
        MAVEN_HOME = '/opt/homebrew/Cellar/maven/3.9.9/libexec'
        OPENSHIFT_SERVER = 'https://api.rm3.7wse.p1.openshiftapps.com:6443'
        OPENSHIFT_TOKEN = credentials('openshift-token') // Jenkins Secret Text
        NAMESPACE = "omssystem"
        DOCKER_USER = 'ashvinbharda+kreeyaj'
        DOCKER_PASSWORD = credentials('quay-creds') // Quay robot token
        IMAGE_NAME = 'kreeyaj'
        IMAGE_TAG = '1.0'
        REGISTRY = 'quay.io/ashvinbharda'
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
                withEnv(["OPENSHIFT_TOKEN=${OPENSHIFT_TOKEN}"]) {
                    sh '''
                        echo "🔐 Logging into OpenShift..."
                        oc login ${OPENSHIFT_SERVER} --token=$OPENSHIFT_TOKEN --insecure-skip-tls-verify=true
                    '''
                }
            }
        }
       stage('Docker Login') {
             steps {
                 sh '''
                     echo "DOCKER_PASSWORD" | docker login -u="DOCKER_USER" --password-stdin quay.io
                 '''
             }
         }
         stage('Build Docker Image') {
            steps {
                sh '''
                    docker build -t $IMAGE_NAME:$IMAGE_TAG .
                '''
            }
        }
         stage('Tag Docker Image') {
            steps {
                sh '''
                    docker tag $IMAGE_NAME:$IMAGE_TAG $REGISTRY/$IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }
         stage('Push to Quay.io') {
            steps {
                sh '''
                    docker push $REGISTRY/$IMAGE_NAME:$IMAGE_TAG
                '''
            }
        }
         stage('Trigger Rollout') {
            steps {
                echo '🔄 Triggering OpenShift rollout...'
                sh "oc rollout restart deployment/${IMAGE_NAME} -n ${NAMESPACE}"
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
