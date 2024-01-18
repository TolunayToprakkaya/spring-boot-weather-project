# Spring Boot Weather Project

Spring Boot Web application to provide REST API in JSON. This project shows live weather information to the user by city using the Openweathermap public API.

<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/TolunayToprakkaya/spring-boot-weather-project.git
   ```
2. Install NPM packages
   ```sh
   npm install
   ```
3. Build application
   ```sh
   java -jar .\target\WeatherApi-0.0.1-SNAPSHOT.jar
   ```
4. Run the test suite
   ```sh
   mvn clean test
   ```   
   
## Tech Stacks

This project template uses:

* Java 21
* Spring Boot
* [Openweathermap](https://openweathermap.org/) to get real time weather data
* [Spring Web](https://spring.io/guides/gs/serving-web-content/) to serve HTTP requests
* [Swagger](https://swagger.io/) for api documentation
* [Maven](https://maven.apache.org/) to build and, in dev-mode, run the application with hot reload
* [Junit](https://junit.org/junit5/) for testing

<!-- API DESCRIPTIONS -->
## API Descriptions

### Fetch weather data with post request
```
POST /api/weather/temperature
Accept: application/json
Content-Type: application/json
{
    "cityName": "istanbul",
    "unit": "metric"
}

Response: HTTP 200
{
    "status": "SUCCESS",
    "response": {
        "name": "istanbul",
        "temperature": "14",
        "unit": "Celsius"
    }
}
```

### Fetch weather data with get request
```
Get /api/weather/temperature?cityName=kuala lumpur&unit=metric
Accept: application/json
Content-Type: application/json

Response: HTTP 200
{
    "status": "SUCCESS",
    "response": {
        "name": "kuala lumpur",
        "temperature": "24",
        "unit": "Celsius"
    }
}
```

### Fail response (City not found or Openweathermap API not response)
```
POST /api/user/{userId}
Accept: application/json
Content-Type: application/json
{
    "cityName": "istanbullll",
    "unit": "metric"
}

Response: HTTP 200
{
    "status": "FAILURE",
    "response": null
}
```

### To view Swagger API docs

Run the server and browse to localhost:8000/swagger-ui/index.html/
