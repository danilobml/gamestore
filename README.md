# Gamestore - REST API for managing collections of games.

## Tooling:
### Framework: Java Spring Boot
### Build tools: Maven, Docker

## Live deployed (with CI/CD):
https://www.delicious-hanni-danilobml-c0cd7d80.koyeb.app

## Endpoints:
- GET: /games -> list of al games (minimal info)
- GET: /games/{gameId} -> gets one game (full info)
- GET: /lists -> different predefined game lists
- GET: /lists/{listId}/games -> games that belong to that particular list (minimal info)
