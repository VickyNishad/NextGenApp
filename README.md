# ngr-backend



ssh -i NextGenApp-Key.pem ubuntu@13.127.245.216



move jar file to server from local machine	scp -i NextGenApp-Key.pem "C:\Users\Vicky Nishad\OneDrive\Documents\ngr-backend\ms-nextgen-service\target\ms-nextgen-service.jar" ubuntu@13.201.37.35:/home/ubuntu/build
go to script folder on jump server	cd /opt/script
move jar file to app server from jump server	scp -i NextGenApp-Key.pem /home/ubuntu/build/ms-nextgen-service.jar ubuntu@22.0.4.4:/home/ubuntu/build
move jump server to app server	./Nextgen-DEV-US-AppServer.sh
go to build folder on app server	cd build
copy jar file to desire location	cp ms-nextgen-service.jar /opt/project/business/env/prod/ms-nextgen-service
go to jar file location	cd /opt/project/business/env/prod/ms-nextgen-service
check pid	netstat -tupln
kill pid	kill -9 PID
run script	./script.sh 
