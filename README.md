# Distributed application built with Spring Cloud 

This repository was initially created to demonstrate how to split sample Spring application into microservices.
To achieve that goal we use some technologies 
from the [Spring Cloud Netflix](https://github.com/spring-cloud/spring-cloud-netflix) technology stack. For detail, take a look at [microservices tutorial](https://www.youtube.com/watch?v=y8IQb4ofjDo&list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas) of Java Brains.

## Understanding Movie Catalog application

<img width="1042" height="550" alt="movie-ss" src="https://user-images.githubusercontent.com/53643180/77846676-5e6bbd00-71c0-11ea-8b21-03578a3b4379.png">

## Starting services locally

Every microservice is a Spring Boot application and can be started locally using IDE or `../mvnw spring-boot:run` command. Please note that supporting services (Discovery Server) must be started before any other application (Movie Catalog, Movie Info, Ratings Data).
If everything goes well, you can access the following services at given location:
* Discovery Server - http://localhost:8761
* Movie Catalog - http://localhost:8081/catalog/{userId}
* Movie Info - http://localhost:8082/movies/{movieId}
* Ratings Data - http://localhost:8083/ratings/user/{userId}
* Hystrix Dashboard - Go to http://localhost:8081/hystrix. Then put 'https://localhost:8081/actuator/hystrix.stream' to the textbox.

## In case you find a bug/suggested improvement for Movie Catalog
The [issue tracker](https://github.com/enesoral/Movie-Catalog/issues) is the preferred channel for bug reports, features requests and submitting pull requests.


