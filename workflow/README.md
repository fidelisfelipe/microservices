# Workflow

Implementations of the workflow for the project.

## Model:

* Fluxo: id, state, date
* History: id, fluxo_id, state, date

## How to use:

* create
GET http://localhost:8200/api/v1/flow/create

* flow by id
GET http://localhost:8200/api/v1/flow/600b3c9e-0178-462c-b515-20f3e92d4a2c

* history by flow id
GET http://localhost:8200/api/v1/flow/history/600b3c9e-0178-462c-b515-20f3e92d4a2c

* history list by flow id
GET http://localhost:8200/api/v1/flow/history/600b3c9e-0178-462c-b515-20f3e92d4a2c/list

* change - iniciar
PUT http://localhost:8200/api/v1/flow/INICIAR/600b3c9e-0178-462c-b515-20f3e92d4a2c

* change - finalizar
PUT http://localhost:8200/api/v1/flow/FINALIZAR/c29118a2-e77a-432d-8bb9-4f1b389c5ef4

* change - cancelar
PUT http://localhost:8200/api/v1/flow/CANCELAR/600b3c9e-0178-462c-b515-20f3e92d4a2c

* Use [workflow-request.http](workflow-request.http) for easy test the endpoints in IntelliJ 



