# MOCKOON #

- A ferramenta ajuda a construir e executar APIs simuladas localmente.

- O objetivo da utilização dessa ferramenta foi simplificar a implementação de uma API e focar nos conceitos e implementação do Camel.

  Link da Ferramenta: https://mockoon.com/

## Configuração do Mockoon ## 





## Scripts do Mockoon ## 


- Inserir os Scripts no Body:

  - Lista de Users: Endpoint : users

```
[
  {
    "id":1,
    "name":"Marcel",
    "lastname":"Pinotti",
    "email":"marcel@xpto.com",
    "password":"Marcel@123",
    "cep":"01001-000"
  },
  {
    "id": 2,
    "name":"João",
    "lastname":"Roberto",
    "email":"joao@xpto.com",
    "password":"Joao@123",
    "cep": "04140-110"
  },
  {
    "id": 3,
    "name":"Paulo",
    "lastname":"Costa",
    "email":"paulo@xpto.com",
    "password":"Paulo@123",
    "cep": "04503-000"
  }
]
```

- Scripts do Mockoon (Inserir no Body):
  - User por Id: user/:id

```
{{#switch (urlParam 'id')}}
  {{#case '1'}}
    {
      "id": 1,
      "name":"Marcel",
      "lastname":"Pinotti",
      "email":"marcel@xpto.com",
      "password":"Marcel@123",
      "cep": "01001-000"
    }
  {{/ case }}
  {{#case '2'}}
    {
      "id": 2,
      "name":"João",
      "lastname":"Roberto",
      "email":"joao@xpto.com",
      "password":"Joao@123",
      "cep": "04140-110"
    }
  {{/case}}
  {{#case '3'}}
    {
      "id": 3,
      "name":"Paulo",
      "lastname":"Costa",
      "email":"paulo@xpto.com",
      "password":"Paulo@123",
      "cep": "04503-000"
    }
  {{/case}}
  {{#default}}
    {
      "id": "null",
      "name":"null",
      "lastname":"null",
      "email":"null",
      "password":"null",
      "cep": "null"
    }
  {{/default}}
{{/switch}}
```