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
  
This project was tested on ``Google Chrome`` and ``Safari``
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
    
  - To check the containers running, use ``docker ps`` , then you will get something such as
    Then to check the log of the system you need to run ``docker logs -f <CONTAINER_ID>``, in this case ``docker logs -f ``

## API Endpoints

### Create a url

To create a URL shortener, you need to send a ``POST`` request to http://yourserverurl:8080/ (http://localhost:8080/)

```json
{
  "url" : "https://www.google.com"
}
```

The response will be something such as 
```json
{
    "id": "750f83372b846d48b7cfdddcc734fb3c",
    "url": "http://www.google.com",
}
```

### Get the URL you created

To go to the shortened URL, you must go to http://yourserverurl:8080/<ID> (http://localhost:8080/750f83372b846d48b7cfdddcc734fb3c)


### Get all clicks for a URL

To get all the clicks (HITS) you need to send a ``GET`` request to http://yourserverurl:8080/statistics/all/<ID_OF_YOUR_URL> (http://localhost:8080/)

The response will be something such as 

```json
[
    {
        "date": "2019-01-19T22:12:20.743",
        "ip": "0:0:0:0:0:0:0:1"
    },
    {
        "date": "2019-01-19T22:10:12.616",
        "ip": "0:0:0:0:0:0:0:1"
    }
]
```

### Get number of clicks for a URL

To get a count of all the clicks you need to send a ``GET`` request to http://yourserverurl:8080/statistics/<ID_OF_YOUR_URL> (http://localhost:8080/)

```json
[
    {
        "date": "2019-01-19T22:12:20.743",
        "ip": "0:0:0:0:0:0:0:1"
    },
    {
        "date": "2019-01-19T22:10:12.616",
        "ip": "0:0:0:0:0:0:0:1"
    }
]
```

## Postman

I created a collection to test the API using Postman, the collection is named as ``Collection.postman_collection.json`` in the root of this project, you need to import it on Postman to test.

## API Documentation

There's a documentation using Postman on this [link](https://documenter.getpostman.com/view/2904938/RzteSCZJ) , you can use as a reference.


## Premises / Improvements

For the statistics, i've faced some issues with the web browsers, related below:

### Google Chrome

- Google chrome by default send a GET request when you type in the address, and caches that request, i tried to manage using Cache Control.

### Safari

- It's the same as google, but sends 2 requests, 1 when you type the address, and one when you hit enter, i'm saving those hits twice.
- 
## LICENSE

This project is licensed under the ``Apache License 2.0``, check the LICENSE file for questions or doubts.