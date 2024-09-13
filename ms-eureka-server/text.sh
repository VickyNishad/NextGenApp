#!/bin/bash

# Define service name, directory, and port
SERVICE_NAME="ms-eureka-server"
BASE_DIR="D:/spring-boot-microservicess/NextGenApp/ms-eureka-server/$SERVICE_NAME"
LOG_FILE="D:/spring-boot-microservicess/NextGenApp/ms-eureka-server/deploy-$SERVICE_NAME.log"
PORT="8761"

# Function to deploy the service
deploy_service() {
    echo "=======================================" >> $LOG_FILE
    echo "Deploying $SERVICE_NAME on port $PORT" >> $LOG_FILE
    echo "=======================================" >> $LOG_FILE

    # Navigate to the service directory
    cd $BASE_DIR

    # Pull the latest code from GitHub
    echo "Pulling latest code for $SERVICE_NAME..." >> $LOG_FILE
    git pull origin main >> $LOG_FILE 2>&1

    # Clean and package the Maven project
    echo "Building $SERVICE_NAME..." >> $LOG_FILE
    mvn clean package >> $LOG_FILE 2>&1

    # Kill the existing service if running
    echo "Stopping any existing instance of $SERVICE_NAME running on port $PORT..." >> $LOG_FILE
    PID=$(lsof -t -i:$PORT)
    if [ ! -z "$PID" ]; then
        kill -9 $PID
        echo "$SERVICE_NAME stopped (PID $PID)." >> $LOG_FILE
    else
        echo "No running instance of $SERVICE_NAME found on port $PORT." >> $LOG_FILE
    fi

    # Deploy the new version
    echo "Starting $SERVICE_NAME..." >> $LOG_FILE
    nohup java -jar target/*.jar --server.port=$PORT > $BASE_DIR/$SERVICE_NAME.log 2>&1 &
    echo "$SERVICE_NAME deployed successfully!" >> $LOG_FILE
}

# Call the deployment function
deploy_service

echo "Deployment completed at $(date)." >> $LOG_FILE
