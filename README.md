# Getting Started

## Start eureka server

```
cd src/test/env
docker-compose up -d
```

## Start hello-world 

```
gradle clean bootRun -PjvmArgs="-Dspring.profiles.active=development"
```