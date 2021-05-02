# Producer Service
 Producer service responsible for triggering events

## Components
* Spring Boot 2.5.0-RC1
* Spring Cloud Stream
* Spring Basic Security
* RabbitMQ message broker
* Swagger Doc
* Gradle

## General Design
* Event driven service send events to RabbitMQ
* Scheduled events will be triggered for every 2 minutes
* Random city will be chosen for every scheduled event 
* On-Demand instant event can be triggered through an exposed API
* API’s is securely accessible through Basic Authentication 
* API documentation acccessible via [SwaggerUI](http://localhost:8080/swagger-ui.html) 

# Build
```gradle clean build```

# Running Locally

gradle :bootRun

#TODO
* JUNIT Test cases