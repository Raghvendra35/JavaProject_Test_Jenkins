pipeline {
    agent any

    environment {
        APP_NAME   = "springboot-app"
        APP_PORT   = "8000"
        IMAGE_NAME = "springboot-app:latest"
        DEPLOY_DIR = "/opt/Jan_Deploy"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📦 Checking out code..."
                git branch: 'main', url: 'https://github.com/Raghvendra35/JavaProject_Test_Jenkins.git'
            }
        }

        stage('Build Jar') {
            steps {
                echo "🏗️ Building Maven project..."
                sh '''
                    if [ -f mvnw ]; then
                        chmod +x mvnw
                        ./mvnw clean package -DskipTests
                    else
                        mvn clean package -DskipTests
                    fi
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "🐳 Building Docker image..."
                sh '''
                    docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Deploy Docker Container') {
            steps {
                echo "🚀 Deploying with Docker..."
                sh '''
                    # Stop old container if running
                    if [ "$(docker ps -q -f name=$APP_NAME)" ]; then
                        echo "🛑 Stopping old container..."
                        docker stop $APP_NAME || true
                        docker rm $APP_NAME || true
                    fi

                    # Run new container
                    echo "🚀 Starting new container on port $APP_PORT..."
                    docker run -d --name $APP_NAME -p $APP_PORT:8000 $IMAGE_NAME
                '''
            }
        }
    }

    post {
        success {
            echo "🎉 Deployment successful — Docker container running on port ${APP_PORT}"
        }
        failure {
            echo "❌ Pipeline failed — check build logs."
        }
    }
}
