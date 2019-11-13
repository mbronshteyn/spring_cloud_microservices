# Microservices with Spring Cloud

**Sample repository with Config server, Eureka server and Eureka clients**

Services communicate with each other using Feign Client with Ribbon LoadBalancer

This repository is my Microservices Sandbox for the project I am currently working at my client. 

Application notes:

Before:

    - Start Kafka
        -   cd into ./kafka-docker directory
        -   add env variable: export DOCKER_HOST_IP=127.0.0.1
        -   start docker compose: docker-compose up -d
        
    - Start Config Server.  Config server relies on connection to Kafka for Spring Cloud Bus.
    - Start Eureka

Spring Cloud Gateway with Load Balancer:

    - Start Gateway
    - Start all three Sentence Applications. 
        - If using IntelliJ configure the app to run in parallel and run on separate server.port.
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

Spring Cloud Bus:
    
    Check Config Server. Config server pom.xml has to have dependency on actuator and kafka:
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
                <version>2.1.7.RELEASE</version>
            </dependency>
            
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bus-kafka</artifactId>
                <version>2.1.2.RELEASE</version>
                <scope>runtime</scope>
            </dependency>  
    
    application.yml has to have config which allows bus-refresh and bus-env:
    management:
      endpoints:
        web:
          exposure:
            include: bus-refresh, bus-env
            
    Run the following curl command and verify the response is 204:
    curl -X POST \
      http://10.0.0.11:8001/actuator/bus-refresh 
      
    




