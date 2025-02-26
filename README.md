# Gamestore - REST API for managing collections of games.

## Tooling:
### Framework: Java Spring Boot
### Build tools: Maven, Docker

## Live deployed (with CI/CD):
[delicious-hanni-danilobml-c0cd7d80.koyeb.app](delicious-hanni-danilobml-c0cd7d80.koyeb.app/swagger-ui/index.html)

## Swagger-UI OpenApi docs:
[delicious-hanni-danilobml-c0cd7d80.koyeb.app/swagger](delicious-hanni-danilobml-c0cd7d80.koyeb.app/swagger-ui/index.html)

## To Test:
### Login:
-Do a POST request to [delicious-hanni-danilobml-c0cd7d80.koyeb.app/users/login](delicious-hanni-danilobml-c0cd7d80.koyeb.app/users/login), with a request body in JSON format consisting in: 

{
    "username": "test-user",
    "password": "test-pass"
}

-From the Response, copy the "Authorization" header value ("Bearer ...");
-On each request to the API, add a header "Authorization" and paste it as value.

