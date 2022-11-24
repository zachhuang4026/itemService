# Item Service: framework with Maven, Quarkus
## Author: Zach Huang (zachhuang4026)

## How to run
1. Start Docker container served with MongoDB
1-1. Container name: itemDB

1-2. Execute into the MongoDB container
```shell script
docker exec -it itemDB /bin/bash
```

1-3. Get the IP that MongoDB container is using
```shell script
ifconfig
```


2. Start Docker container served as the Item Server (I published the Docker container on Docker Hub)
2-1. Log in into Docker Hub
```shell script
docker login
```
Then fill in your Docker Hub username and password.

2-2. Run Docker container with specific IP/Port  
```shell script
docker run -it --rm -p 127.0.0.1:8080:8080 --name itemServer zachhuang4026/itemserver:latest
```




## Build/
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
