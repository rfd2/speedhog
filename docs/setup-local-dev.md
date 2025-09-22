## Required software:
- Java Development Kit (JDK) version 21 or higher
- Quarkus CLI version 3.x or higher
- Docker
- Taskfile

## Steps Taken to Create Initial Project:
quarkus create app io-speedhog:speedhog \
    --extensions=rest,rest-jackson \
    --gradle

## Running in dev mode 
* cd to the speedhog directory
* run:  *quarkus dev*
