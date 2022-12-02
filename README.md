# Item Service: framework with Maven, Quarkus
## Author: Zach Huang (zachhuang4026)

## How to run
1. Open a new terminal, login Docker Hub
```shell script
docker login
```
Then type the Docker Hub account name and password.

2. Pull docker images from Docker Hub, this will serve as the `server` for Item Service
```shell script
docker pull zachhuang4026/itemserver:latest
```

3. Run the docker container with static ip under subnet `ebay` 
```shell script
docker run -it -p 8080:8080 --net ebay --ip 172.20.0.5 --name itemServer zachhuang4026/itemserver:latest
```
(No need to execute into this container, just run this command and keep it running)

4. Open another new terminal, pull the docker container served as the `datebase` for Item Service
```shell script
docker pull zachhuang4026/itemdb:latest
```

5. Run the docker container 
```shell script
docker run -p 27017:27017 -v MongoDockerData:/data/db --net ebay --ip 172.20.0.6 --name itemDB zachhuang4026/itemdb:latest
```
(Please wait until seeing a word `QUARKUS`, I used Quarkus to auto fire up all the java code, so no need to execute into the container manually)   
 
## Please ignore the following, it's my personal note to build docker container
## Build
1. Run mvn packages 
```shell script
mvn -N io.takari:maven:wrapper
```
```shell script
./mvnw package
```

2. Build container (my docker id: zachhuang4026, and I am using MacOS M1 chip)
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t zachhuang4026/itermserver --platform=linux/amd64 .
```

3. Push to Docker Hub 
```shell script
$ docker push zachhuang4026/itemserver
```

4. Build mongoDB
$ docker run -ti --rm -p 27017:27017 --network="host" mongo:4.0

5. Run the container with compiled quarkus
$ docker run -it --rm -p 8080:8080 --network="host" zachhuang4026/quarkusrest-2-jvm

6. In Docker hub will see two conatiners running, one is for mongoDB with port 27017; the other one is for quarkus with port 8080


#EOF
