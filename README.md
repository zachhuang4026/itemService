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
$ ./mvnw package

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
##########################################################



This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkusrest-2-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes
  with Swagger UI
- Amazon
  DynamoDB ([guide](https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-dynamodb.html)):
  Connect to Amazon DynamoDB datastore

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
