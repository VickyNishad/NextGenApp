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



# .pem



-----BEGIN RSA PRIVATE KEY-----
MIIEpQIBAAKCAQEAv79yTUWcH3xOo4i/LZmE573p+wXDK3Rokj7kpjBwzF0F7Obl
D3yJQeSDQVUJw0Ygvq4vwW9zpIqykR1lpR66r1U10zm10JNN7ISPRjNm9Mey0Bc7
BiTGcCnMZnXZTO9d+4sj4WIGn1KF8dd3E+9bNyUGtamcNBjvEcDTrca9s+G4WHig
zLEkzub1eGUUbxz/bqHcshhzc6kwX6g4VXDw0kHnlex4QT8q/mNlxDeIfbp3o9B2
7g1hK2NfKAj9qpP84/RzDMFYI3xU0RzL9qyqsr40VRRpK+UZ0BSYeGG6E6w3D6Pv
5l2lt9l2y8dZBsMKgOYIj1HAKfVBUL/bYn43qQIDAQABAoIBAFV4pB23orESOweA
ZB+SaADY0dx4JBhRqm9OI6PEwfMGoCaKgZ5Wl+RMVQmhO1nU/7GfeWATKww0WwZ+
gRmKShLvu69s8ROJJdoA+NCFByajgZxqToAdkmra5ubhLzyxEkaXLiZACM2p/lTg
A49aSWGaWpRNh3hdiOtz9adniJYOhzT70fntm8lHKY5K2Ot0YCsQdeuQrVDrioik
Qazv/29G8ACtRTkasIBfM4r2hfeFqPhhFU17bcS/9WUcch+Y7hMgTMf/Mza9rNF0
i4w0xuD1tb/K85zfF8ZLaC2wy6jU4zi54h7yRbzd66oUMdOvob0mlbLr8qaZw8b8
LEhUIgUCgYEA/kg/uzYlmWt9LltPoTLQAYT/G/L4fo417Rxff07idkJTHhe0qNOI
3sAOIdnLdBeQIb+ZmEuivMmfCxYKI3eymBnVi9FnmpHIA83A22LFalHlOlVV4ulr
sxWEWFlloFICCbZnvqSVtWAg2NdkR13bNjpmnhQiqZkCYpa2V4jWF8cCgYEAwQsN
PSKGD6uPriAsdeNeddMgVHxKkH/k/yLM9zvo8DBFKejNfKpYeTUAuiyIBrXlfMGJ
rRdLRIB+P2Wxxxpyt5NAOwgsak9wFwIaUhsXOHnnvIMHV+Ciq58vglXIC0PxWgAl
tRMoP9mjkCcOotvQ3adoSEhcP6/sTZVqhYUElQ8CgYEA9uoFKK79d6qOlbMI8ci7
fp48clFOxuUkrCe++4oIETl6KGBwhvdRkqr/jhNEomg3Bu5cISxgy90kWMl/8szK
WTPMzsX6SneAt2DWArI4bHM1mZ0/luV+NRmRt5CnRDQd0nyS5OZAkSzU+Hq+3yXe
NRwkhR+cQP9Y947NZNZ0vgUCgYEAnjiDsk7gmGliSzJ9nmYsHuBbiEOTDdp1g5Qv
cJg+9NZrac0w1vP7reR+YxlqqC1gGZ+7Sw5jl5LFZwrf6jPIQKRFKd0qIjxXv9Pu
ZMtlfdmU0VKHq0QnsAi59NaALpOSdtTWExLPk327bMRGX79Q8stqUoxUZ7K2OGS1
uRfsSIsCgYEApQw2V64Jq+AuSfeiFZmU8glWTZDG34Du7X4NdeTeyghMn2UL5Feu
fUZgOCY4A9DfAOw87ng3724f2RI09QW/yazmTyX6CEXVCmyKJITRBKdrej6kgAds
tWJsOEdKlqRfTCyQvJei2QTssFjH/xCktjCAmSz1OtqSzikm5BrwK7c=
-----END RSA PRIVATE KEY-----
