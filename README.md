<div id="top"></div>

<!-- ABOUT THE PROJECT -->
## About The Project
This is a spring boot application that fetches stock data from stocks API from RapidApi site. CRUD functionalities
are implemented.

### Built With
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Mongodb](https://www.mongodb.com/)
* [Redis](https://redis.io/)
* [Stocks API](https://rapidapi.com/suneetk92/api/latest-stock-price/)
* [Swagger API](https://swagger.io)

## Getting Started

The following are needed to run the project
* Java - atleast java 11. Project built with java 17
* latest-stock API Key

### Installation

To run the application:
1. Clone the repo
   ```sh
   git clone https://github.com/samwanjo41/stocks-api-springBoot.git
   ```
2. Make sure that Mongodb, Java and Redis are in your local machine

3. Run the application using your favorite IDE

## Usage

1. To get all stocks:
``` sh
    GET : http://localhost:8080/api/v1/stocks
```
2. To create a stock :
``` sh
    POST : http://localhost:8080/api/v1/stocks
```
3. To update stock details:
``` sh
    PUT : http://localhost:8080/api/v1/stocks/{symbol}
```
4. To get stock by symbol:
``` sh
    GET : http://localhost:8080/api/v1/stocks/{symbol}
```
5. To delete a stock:
``` sh
    DELETE : http://localhost:8080/api/v1/stocks/{symbol}
```
<p align="right">(<a href="#top">back to top</a>)</p>