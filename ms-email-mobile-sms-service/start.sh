#!/bin/bash

# Define service name, directory, and port
SERVICE_NAME="ms-email-mobile-sms-service"
BASE_DIR="D:/spring-boot-microservicess/NextGenApp/$SERVICE_NAME"
LOG_FILE="D:/spring-boot-microservicess/NextGenApp/deploy-$SERVICE_NAME.log"
PORT="8082"

# Function to deploy the service
deploy_service() {
    echo "=======================================" >> "$LOG_FILE"
    echo "Deploying $SERVICE_NAME on port $PORT" >> "$LOG_FILE"
    echo "=======================================" >> "$LOG_FILE"

    # Navigate to the service directory
    cd "$BASE_DIR" || exit

    # Pull the latest code from GitHub
    echo "Pulling latest code for $SERVICE_NAME..." >> "$LOG_FILE"
    git pull origin main >> "$LOG_FILE" 2>&1

    # Clean and package the Maven project
    echo "Building $SERVICE_NAME..." >> "$LOG_FILE"
    mvn clean package -DskipTests -T 4 >> "$LOG_FILE" 2>&1

    # Kill the existing service if running
    echo "Stopping any existing instance of $SERVICE_NAME running on port $PORT..." >> "$LOG_FILE"

    # Find the PID of the process using the specified port (using netstat as lsof is not available on Windows)
#    PID=$(netstat -ano | findstr :$PORT | awk '{print $5}')
     PID=$(netstat -ano | findstr :$PORT | powershell -Command "$input | ForEach-Object { $_.Trim() -split '\s+' | Select-Object -Last 1 }")


    if [ ! -z "$PID" ]; then
        echo "Stopping process with PID $PID..." >> "$LOG_FILE"
        taskkill //PID $PID //F >> "$LOG_FILE" 2>&1
        echo "$SERVICE_NAME stopped (PID $PID)." >> "$LOG_FILE"
    else
        echo "No running instance of $SERVICE_NAME found on port $PORT." >> "$LOG_FILE"
    fi

    # Deploy the new version
    echo "Starting $SERVICE_NAME..." >> "$LOG_FILE"
    nohup java -jar target/*.jar --server.port=$PORT > "$BASE_DIR/$SERVICE_NAME.log" 2>&1 &
    echo "$SERVICE_NAME deployed successfully!" >> "$LOG_FILE"
}

# Call the deployment function
deploy_service

echo "Deployment completed at $(date)." >> "$LOG_FILE"
