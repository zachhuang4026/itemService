# Quarkus-MongoDB
## Author: Zach Huang (zachhuang4026)

## Introduction
1. The container has been deployed on AWS lightsail with this endpoint url: https://container-service-1.njnffghb7db4a.us-west-2.cs.amazonlightsail.com/people

2. Postman test cases 'People.postman_collection.json' is under the current directory.

## Build/Deploy
### Local
1. Open this project with Intellij, simply click on 'Run' botton. The endpoint is http://localhost:8080/people 

### On AWS
1. Run mvn packages
```shell script
mvn -N io.takari:maven:wrapper
```
```shell script
./mvnw package
```

2. Build container (my docker id: zachhuang4026, and I am using MacOS M1 chip)
$ docker build -f src/main/docker/Dockerfile.jvm -t zachhuang4026/quarkusrest-2-jvm --platform=linux/amd64 .

3. Push to Docker Hub 
$ docker push zachhuang4026/quarkusrest-2-jvm

4. Build mongoDB
$ docker run -ti --rm -p 27017:27017 --network="host" mongo:4.0

5. Run the container with compiled quarkus
$ docker run -it --rm -p 8080:8080 --network="host" zachhuang4026/quarkusrest-2-jvm

6. In Docker hub will see two conatiners running, one is for mongoDB with port 27017; the other one is for quarkus with port 8080


#EOF
