FROM openjdk:17
COPY . /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "src/main/java/edu/uchicago/zachhuang4026/quarkus/Resources/ObjectResources"]