pipeline {
    agent any

    environment {
        DEPLOY_DIR = "/opt/Jan_Deploy"
        APP_PORT   = "8000"
        REPO_URL   = "https://github.com/Raghvendra35/JavaProject_Test_Jenkins.git"
        BRANCH     = "main"
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo "📦 Checking out latest code..."
                script {
                    if (fileExists('.git')) {
                        sh "git fetch origin ${BRANCH}"
                        sh "git reset --hard origin/${BRANCH}"
                    } else {
                        git branch: "${BRANCH}", url: "${REPO_URL}"
                    }
                }
                sh 'git log -1 --oneline'
            }
        }

        stage('Build') {
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

       stage('Deploy') {
    steps {
        script {
            echo "🚀 Deploying new build..."

            sh '''
            set -e
            DEPLOY_DIR="/opt/Jan_Deploy"
            APP_PORT=8000
            JAR_FILE=$(ls target/*.jar | head -n 1)
            JAR_NAME=$(basename $JAR_FILE)

            echo "📦 Copying $JAR_NAME to $DEPLOY_DIR..."
            mkdir -p $DEPLOY_DIR
            cp $JAR_FILE $DEPLOY_DIR/

            cd $DEPLOY_DIR

            # Stop old process if running
            if [ -f app.pid ]; then
                PID=$(cat app.pid)
                if ps -p $PID > /dev/null 2>&1; then
                    echo "🛑 Stopping old app (PID: $PID)..."
                    kill -9 $PID || true
                fi
                rm -f app.pid
            fi

            echo "🚀 Starting new app on port $APP_PORT..."
            setsid java -Dserver.port=$APP_PORT -jar $JAR_NAME > app.log 2>&1 &

            sleep 2m
            pgrep -f "$JAR_NAME" > app.pid

            if [ -s app.pid ]; then
                echo "✅ App started successfully (PID: $(cat app.pid))"
            else
                echo "❌ App failed to start!"
                exit 1
            fi
            '''
        }
    }
}

    }

    post {
        success {
            echo "🎉 Pipeline completed successfully — code built and deployed!"
        }
        failure {
            echo "❌ Pipeline failed — check logs for details."
        }
    }
}
