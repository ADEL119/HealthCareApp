From openjdk:17
copy ./target/Patient-0.0.1-SNAPSHOT.jar Patient-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","Patient-0.0.1-SNAPSHOT.jar"]
