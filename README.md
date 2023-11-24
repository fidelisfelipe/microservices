# Microservice project

[![wakatime](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/7caa551d-73c4-4c0f-83dd-5ae9b30c9e4d.svg)](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/7caa551d-73c4-4c0f-83dd-5ae9b30c9e4d)

# Read Me First

[![wakatime](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/b8a17c62-fcd2-4eb0-b6d1-749af87f69bb.svg)](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/b8a17c62-fcd2-4eb0-b6d1-749af87f69bb)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/)
![GitHub last commit](https://img.shields.io/github/last-commit/fidelisfelipe/microservices)

![Java](https://img.shields.io/badge/-Java-000?&logo=Java&logoColor=007396)
![JavaScript](https://img.shields.io/badge/-JavaScript-000?&logo=JavaScript&logoColor=ddc508)
![TypeScript](https://img.shields.io/badge/-TypeScript-000?&logo=TypeScript&logoColor=007ACC)
![Angular](https://img.shields.io/badge/-Angular-000?&logo=Angular&logoColor=dd0031)
![Node.js](https://img.shields.io/badge/-Node.js-000?&logo=node.js&logoColor=339933)
![NPM](https://img.shields.io/badge/-NPM-000?&logo=npm&logoColor=CB3837)
![Yarn](https://img.shields.io/badge/-Yarn-000?&logo=yarn&logoColor=2C8EBB)
![HTML5](https://img.shields.io/badge/-HTML5-000?&logo=HTML5)
![CSS3](https://img.shields.io/badge/-CSS3-000?&logo=CSS3&logoColor=1572B6)
![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-000?&logo=SpringBoot&logoColor=6DB33F)
![Docker](https://img.shields.io/badge/-Docker-000?&logo=Docker&logoColor=2496ED)
![Docker Compose](https://img.shields.io/badge/-Docker%20Compose-000?&logo=Docker&logoColor=2496ED)
![Kubernetes](https://img.shields.io/badge/-Kubernetes-000?&logo=Kubernetes&logoColor=326CE5)
![GitLab](https://img.shields.io/badge/-GitLab-000?&logo=GitLab&logoColor=FCA121)
![Jenkins](https://img.shields.io/badge/-Jenkins-000?&logo=Jenkins&logoColor=D24939)
![Intellij](https://img.shields.io/badge/-Intelij-000?&logo=IntelliJIDEA&logoColor=000000)
![Eclipse](https://img.shields.io/badge/-Eclipse-000?&logo=EclipseIDE&logoColor=2C2255)
![Maven](https://img.shields.io/badge/-Maven-000?&logo=ApacheMaven&logoColor=C71A36)


The following was discovered as part of building this project:

* The original package name 'com.example.microservices.currency-conversion-service' is invalid and this project uses 'com.example.microservices.currencyconversionservice' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.7/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#web)
* [Config Server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_server)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#spring-cloud-eureka-server)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)
* [Gateway](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#actuator)
* [Spring Boot Docker](https://docs.spring.io/spring-boot/docs/2.7.7/reference/htmlsingle/#build-image)


### Guides
The following guides illustrate how to use some features concretely:

* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)
* [Using Spring Cloud Sleuth for tracing request](https://spring.io/projects/spring-cloud-sleuth)

### Ports used
    * Limite Service                    8080, 8081, ...        
        - http://localhost:8080/limits
    * Currency Exchange Service         8000, 8001, 8002, ...   
        - http://localhost:8000/currency-exchange/from/EUR/to/INR
        - http://localhost:8000/h2-console
    * Spring Cloud Config Service       8888
    * Currency Conversion Service       8100, 8101, 8102, ...
        - http://localhost:8100/currency-conversion-feign/from/USA/to/INR/quantity/200
    * Statemachine Service              8200, 8201, 8202, ...
        - http://localhost:8200/create
        - http://localhost:8200/state/last/1
    * Netflix Eureka Naming Server      8761                    
        - http://localhost:8761/
    * Netflix Zuul API Gateway Server   8765                    
        - http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USA/to/INR/quantity/200
    * Zipkin Distributed Tracing Server 9411                    
        - http://localhost:9411/zipkin

### dev
  * zipkin with rabbitmq
  ```
    set RABBIT_URI=amqp://localhost 
    java -jar zipkin-server-2.24.0-exec.jar
  ```
  * refresh config
    - POST - http://localhost:8080/actuator/refresh - refresh one instance
    - POST - http://localhost:8080/actuator/busrefresh - refresh all instances
	
  * docker
    - [Open Zipkin] (https://hub.docker.com/r/openzipkin/zipkin/)
	```
       docker run -d -p 9411:9411 openzipkin/zipkin
	```
 
   * spring boot docker
     ```
        mvn spring-boot:build-image -DskipTests
     ```
     