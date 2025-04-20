pipeline {
    agent any

    environment {
        IMAGE_NAME = "kreeyaj"
        IMAGE_TAG = "1.0"
        OPENSHIFT_REGISTRY = "image-registry.openshift-image-registry.svc:5000"
        NAMESPACE = "ashvinbharda-dev"
        DEPLOYMENT_NAME = "omssystem"
    }

    stages {
        stage('Docker Login to OpenShift Internal Registry') {
            steps {
                sh '''
                    oc whoami -t | docker login -u $(oc whoami) --password-stdin ${OPENSHIFT_REGISTRY}
                '''
            }
        }

        stage('Build & Push Image to OpenShift Registry') {
            steps {
                sh '''
                    docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                    docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${OPENSHIFT_REGISTRY}/${NAMESPACE}/${IMAGE_NAME}:${IMAGE_TAG}
                    docker push ${OPENSHIFT_REGISTRY}/${NAMESPACE}/${IMAGE_NAME}:${IMAGE_TAG}
                '''
            }
        }

        stage('Update Deployment Image') {
            steps {
                sh '''
                    oc set image deployment/${DEPLOYMENT_NAME} ${DEPLOYMENT_NAME}=${OPENSHIFT_REGISTRY}/${NAMESPACE}/${IMAGE_NAME}:${IMAGE_TAG} -n ${NAMESPACE}
                '''
            }
        }

        stage('Trigger Rollout') {
            steps {
                echo '🔄 Triggering OpenShift rollout...'
                sh '''
                    oc rollout restart deployment/${DEPLOYMENT_NAME} -n ${NAMESPACE}
                '''
            }
        }
    }
}
