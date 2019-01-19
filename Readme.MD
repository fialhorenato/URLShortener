# URL Shortener Project for Neueda

This project is based on the requisites listed on this [Gist](https://gist.github.com/FylmTM/e3c4e5f337a176e94d6dd51703068925) by [@FylmTM](https://github.com/FylmTM).

## Introduction

This project is developed using Java 8, Spring Boot, Redis, Maven, Docker and Docker-Compose.

The project is using the default ``8080`` port on Spring.

It comes with a embbed Maven that can be used by typing ``./mvnw``

I created a docker-compose for developers to spin-up some dependencies, such as ``redis``, and to run it, you need to run ``docker-compose -f docker-compose-dev.yml up -d`` and you need the port ``6379`` open in your machine.

I created 2 different Maven and Spring Profiles : ``prod`` and ``dev``:

- ``dev`` profile connects to a redis instance running on localhost, since is a in-memory database and doesn't need anything installed with.
- ``prod`` profile connects to the postgres that i started in docker. I'm only forwarding the port 8080, so if you want to change the mapping, edit the ``docker-compose.yml`` file , attribute ``<PORT_OF_YOUR_CHOICE>:8080`` on ports.
  

## IDE's

This project was tested on Eclipse 12-2018 and on IntelliJ IDEA Community Edition.

## Useful commands

- ``docker-compose -f docker-compose-dev.yml up -d && ./mvnw spring-boot:run -Pdev``
  - Spin up the project using the redis server.

- ``./mvnw clean package docker:build``
  - This build cleans the target folder, re-creates it and build the docker image using the ``Dockerfile`` under ``src/main/Docker`` , for that, you need to have ``Docker`` installed and running in your machine.

- ``docker-compose up -d``
  - There's a ``docker-compose.yml`` file on project's root with all the configuration to spin up the project using the PostgreSQL database and everything.
  - To check the containers running, use ``docker ps`` , then you will get something such as
  
    | CONTAINER_ID 	| IMAGE                                	| COMMAND                	| CREATED        	| STATUS        	| PORTS                  	| NAMES                                   	|
    |--------------	|--------------------------------------	|------------------------	|----------------	|---------------	|------------------------	|-----------------------------------------	|
    | acc11e7869aa 	| renatofialho/testurlshortener:latest 	| "java -Dspring.profi…" 	| 18 minutes ago 	| Up 18 minutes 	| 0.0.0.0:8080->8080/tcp 	| urlshortener_url-shortener_1            	|
    | b613880607c3 	| postgres:latest                      	| "docker-entrypoint.s…" 	| 24 minutes ago 	| Up 24 minutes 	| 0.0.0.0:5432->5432/tcp 	| urlshortener_url-shortener-postgresql_1 	|
    
  - To check the containers running, use ``docker ps`` , then you will get something such as
    Then to check the log of the system you need to run ``docker logs -f <CONTAINER_ID>``, in this case ``docker logs -f ``


## API Documentation

There's a documentation using Postman on this [link](https://documenter.getpostman.com/view/2904938/RzteSCZJ) , you can use as a reference.

## LICENSE

This project is licensed under the ``Apache License 2.0``, check the LICENSE file for questions or doubts.