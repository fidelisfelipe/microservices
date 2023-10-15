# Workflow

[![wakatime](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/018b0f97-9f75-47f1-bc97-841f837b61ae.svg)](https://wakatime.com/badge/user/f64b4287-ccd2-422f-a4b2-01e67f19827b/project/018b0f97-9f75-47f1-bc97-841f837b61ae)

Implementations of the workflow for the project.

## Model:

* Flow: id, state, date
* History: id, flow_id, state, date

## Swagger

* [Swagger ui](http://localhost:8200/swagger-ui/index.html)
* [Swagger json](http://localhost:8200/workflow-api-docs)
* [Swagger yaml](http://localhost:8200/api-docs.yaml)

## Development

- Build the project:

```bash
    .\mvnw clean install
```

- Build Image:

```bash
    .\mvnw spring-boot:build-image
```

- Docker run image:

```bash
    docker run -p 8200:8200 -t workflow:0.0.1-SNAPSHOT
```

- Run the project:

```bash
    .\mvnw spring-boot:run
```

- Docker compose:

```bash
    docker-compose up -d
```

- Docker compose down:

```bash
    docker-compose down
```

- Docker compose down and remove volumes:

```bash
    docker-compose down -v
```

