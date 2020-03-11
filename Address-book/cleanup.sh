docker-compose down
docker rmi -f $(docker images -f "dangling=true" -q)
docker rmi redis-db
docker rmi spring-apache
docker rmi maven:3.5.4-jdk-8-alpine
docker rmi openjdk:8-jre-alpine
docker rmi redis:6.0-rc1-alpine