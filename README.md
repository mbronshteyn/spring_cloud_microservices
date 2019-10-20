# Microservices with Spring Cloud

**Sample repository with Config server, Eureka server and Eureka clients**

Services communicate with each other using Feign Client with Ribbon LoadBalancer

This repository is my Microservices Sandbox for the project I am doing at work. 

Application notes:

Before:

    - Start Kafka
        -   cd into ./kafka-docker directory
        -   add env variable: export DOCKER_HOST_IP=127.0.0.1
        -   start docker compose: docker-compose up -d
        
    - Start Config Server.  Config server relies on connectin to Kafka to Spring Cloud Bus.
    - Start Eureka

Spring Cloud Gateway with Load Balancer:

    - Start Gateway
    - Start all three Sentence Applications
    - Run following request with Postman:
        curl -X GET \
          http://localhost:8080/sentence-client/port \
          -H 'Content-Type: application/json' \
          -H 'Host: localhost:8080'
    - Start and stop Sentence instances and verify correct load balancing

Feign Client

    - Along with previous step start all Noun, Adjective, Verb and Subject apps.
    
    
        @FeignClient( "VERB" )
        public interface VerbRepo {
          @RequestMapping( method = RequestMethod.GET, value = "/")
          String getItem();
        }
   
    -  Run curl:
        curl -X GET \
          http://localhost:8080/sentence-client/sentence \
          -H 'Accept: */*' \
          -H 'Host: localhost:8080'

Exception Handler:

    - Show class mbronshteyn.lab4sentence.AppExceptionHandler
    - Execute curl calls:
    
        curl -X GET \
          http://127.0.0.1:45000/exception \
          -H 'Host: 127.0.0.1:45000'

        curl -X GET \
          http://127.0.0.1:45000/custom-exception \
          -H 'Host: 127.0.0.1:45000'





