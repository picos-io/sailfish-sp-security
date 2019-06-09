#!/bin/bash -e
docker build --no-cache -f Dockerfile -t clouthinkin/helloworld:eureka --rm=true ../..
docker push clouthinkin/helloworld:eureka