# Build project sources stage
FROM maven:3.6.3-jdk-11-openj9 as BUILD
COPY ./pom.xml /app/source/pom.xml
WORKDIR /app/source
RUN mvn dependency:go-offline
COPY . /app/source/
RUN mvn clean install -DskipTests

# Prepare runnable project stage
FROM openjdk:11.0.6-jre

# build image with:
#   docker build --build-arg TARGET_SYSTEM=k8s . -t newsapp:0.0.1-SNAPSHOT

# run with:
#   docker network create newsAppNetwork
#   docker run --name=newsAppPostgres --network=newsAppNetwork --env="POSTGRES_PASSWORD=mysecretpassword" --env="POSTGRES_DB=news" -p 5432:5432  postgres:12
#   docker run --name newsweb --network=newsAppNetwork -p 8881:8080 newsapp:0.0.1-SNAPSHOT

# push to docker registry with :
#   docker login
#   docker tag newsapp:0.0.1-SNAPSHOT vnhub/newsapp:0.0.1-SNAPSHOT
#   docker push vnhub/newsapp:0.0.1-SNAPSHOT

LABEL Author="VN" Description="Rest app with postgress integration" Build="docker build . -t newsapp:0.0.1-SNAPSHOT" Run="docker run --name=newsAppPostgres --network=newsAppNetwork --env="POSTGRES_PASSWORD=mysecretpassword" --env="POSTGRES_DB=news" -p 5432:5432  postgres:12"
#COPY target/gke-rest-db-0.0.1-SNAPSHOT.jar /app/app.jar
COPY --from=BUILD /app/source/target/gke-rest-db-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
# TARGET_SYSTEM might be docker or k8s
ARG TARGET_SYSTEM
ENV SPRING_PROFILES_ACTIVE=${TARGET_SYSTEM}
CMD ["java", "-jar", "app.jar"]