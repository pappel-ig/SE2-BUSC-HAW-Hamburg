# SE2 Projekt "Mensa"
## Rollen
- *Product Ownerin*: Elizabeth
- *Scrum Master*: Jonas
- *Developer*: Sam
- *Developer*: Jonas
- *Developer*: Rey
- *Developer*: Larissa

## Requirements:
- Docker (mit Docker-compose)

## Starten der Anwendung (Lokal)
Startet die Datenbank, sowie ein Service um die Datenbank bequem "anzugucken".
```bash
docker-compose -f composeLocally.yml up
```
Starte die Anwendung (entweder über IntelliJ) oder mittels gradle:
```bash
./gradlew bootRun
```
Die Anwendung sollte nun unter `http://localhost:8080` verfügbar sein.
Das DB Management Programm ist unter `http://localhost:8081` erreichbar.

### Anlegen eines Users
```bash
POST /user/register?username=jonas&password=12345678
```
### Login eines Nutzers
```bash
POST /user/login
Body:
{
  "username": "jonas",
  "password": "12345678"
}
```
### Mensa Meal abrufen:
```bash
GET /mensa-meal
```

## Removen der Anwendung
```bash
docker-compose -f composeLocally.yml down
```