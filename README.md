# Building Microservice Based Application

Aim of this project is to provide a basic working example of a microservice application from scratch (Not entirely from scratch. We will be linking small subset of existing projects that I have built overtime) with Spring Boot with a set project starters, Netflix OSS(Zuul,Eureka, and Feign), Hibernate, and JJWT


The Project will divided into 4 sub-modules:

 * friflow-admin 
 * friflow-api 
 * friflow-service-registry
 * friflow-api-gateway



### Application Architecture in a nutshell
 
![alt text](https://cdn-images-1.medium.com/max/800/1*QnEUoCAR7T-7VFaguGFj-w.png)


### Functional Services

Here's our functional services.

### Friflow-admin

The friflow-admin will act as our Identity management service that handles token issuance, persisting user information such as roles,username,password, and etc. We can roll up our own or use an existing identity management API like Auth0 


Database Config. (Change them accordingly) 
```javascript
spring.datasource.url=jdbc:postgresql://localhost:5432/friflow-admin
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
```

The database config for friflow-admin can be found under 
```javascript
friflow-admin\src\main\resources
```


running friflow admin - under friflow-admin execute the mvn spring-boot:run command

```javascript
mvn spring-boot:run
```




### Friflow-api

The Friflow-api is an existing system we will be plugging in. It contains the some of the core business logic of our application. The Friflow-api is a ticketing service/a small workflow management system that issues various types tickets such as quotation tickets which contains product inquiries,pricing,materials used.


### Friflow-api ERD

![alt text](https://cdn-images-1.medium.com/max/800/1*jQS-oewiCjxbPgwADatmAw.png)


Database Config. (Change them accordingly) 
```javascript
spring.datasource.url=jdbc:postgresql://localhost:5432/friflow
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
```

The database config for friflow-admin can be found under 
```javascript
friflow-api\src\main\resources
```

running friflow api - under friflow-api execute the mvn spring-boot:run command

```javascript
mvn spring-boot:run
```

### Infrastructure Services

There are several patterns in distributed systems that can aid us in making our Functional/Core services work together -- and they are called as Infrastructure Services. Spring cloud provides those tools to implement some of those Patterns and these are the ff:


### Friflow-api-gateway 

The Friflow-api-gateway is our Edge service. It would act as main entry point of clients. It’s primary purpose to aggregate results from different service if needed, and act as router/or proxy for our backend service. This is implemented using Zuul, And Feign, and Eureka. . More information about The Api Gateway Pattern  can be [here](http://microservices.io/patterns/apigateway.html)


### Friflow-service-registy

The friflow-service-registry is the Service registry of our applications. Services self-registered. More information about service registry can be [here](http://microservices.io/patterns/service-registry.html) Our Service Registry is implemented using Eureka
Look, a list!


### Materials and Inspiration used: 

 * [microservices.io](http://microservices.io/)
 * [O'Reilly Software Architecture NY 2016](https://player.oreilly.com/videos/9781491944615) 
 * [Microservices vs SOA](http://www.oreilly.com/programming/free/microservices-vs-service-oriented-architecture.csp)
 * [Building Micorservices](http://shop.oreilly.com/product/0636920033158.do) 
