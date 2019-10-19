# Microservices with Spring Cloud

**Sample repository with Config server, Eureka server and Eureka clients**

Services communicate with each other using Feign Client with Ribbon LoadBalancer

This repository is my Microservices Sandbox for the project I am doing at work. 

Labs for Udemy class [ <b>Microservices with Spring Cloud</b> ]( https://www.udemy.com/microservices-with-spring-cloud/ )

Application Demo notes:

Before:

    - Start Kafka
        -   cd into ./kafka-docker directory
        -   add env variable: export DOCKER_HOST_IP=127.0.0.1
        -   start docker compose: docker-compose up -d
        
    - Start Config Server
    - Start Eureka

Demo Spring Cloud Gateway with Load Balancer:

    - Start Gateway
    - Start all three Sentence Applications
    - Run following request with Postman:
        curl -X GET \
          http://localhost:8080/sentence-client/port \
          -H 'Content-Type: application/json' \
          -H 'Host: localhost:8080'
    - Start and stop Sentence instances and verify correct load balancing

Demo Feign Client

    - Along with previous step start all Noun, Adjective, Verb and Subject apps.
    ```bash
        @FeignClient( "VERB" )
        public interface VerbRepo {
          @RequestMapping( method = RequestMethod.GET, value = "/")
          String getItem();
        }
    ```
    -  Run curl:
        curl -X GET \
          http://localhost:8080/sentence-client/sentence \
          -H 'Accept: */*' \
          -H 'Host: localhost:8080'

Demo Spring Cloud Bus:
    
    coming

Demo Exception Handler:

    - Show class mbronshteyn.lab4sentence.AppExceptionHandler
    - Execute curl calls:
    
        curl -X GET \
          http://127.0.0.1:45000/exception \
          -H 'Host: 127.0.0.1:45000'

        curl -X GET \
          http://127.0.0.1:45000/custom-exception \
          -H 'Host: 127.0.0.1:45000'

Demo Spring Security:

    coming




