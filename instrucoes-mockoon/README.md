# MOCKOON #

- A ferramenta ajuda a construir e executar APIs simuladas localmente.

- O objetivo da utilização dessa ferramenta foi simplificar a implementação de uma API e focar nos conceitos e implementação do Camel.

  - [Link da Ferramenta](https://mockoon.com/)

## Configuração do Mockoon ## 

![](https://github.com/marcelpinotti/Apache_Camel_Rest/blob/main/instrucoes-mockoon/img/img5.png)
- Botão New Environment: Cria um novo projeto do Mockoon;
- Botão Play: Inicializa a API;
- Botão Mais: Adiciona um novo endpoint;
- Menu de Opções: Permite a seleção do método do protocolo HTTP desejado;
- Caixa de Texto: Permite inserir o path do endpoint;

![](https://github.com/marcelpinotti/Apache_Camel_Rest/blob/main/instrucoes-mockoon/img/img3.png)
- Menu Settings: Permite configurar porta, path inicial dos endpoints, etc;
- Caixa de Texto 1: Permite inserir o none da API mockada;
- Caixa de Texto 2: Permite inserir a porta em que a aplicação será exposta;
- Caixa de Texto 3: Permite inserir o path padrão de todos os endpoints;

![](https://github.com/marcelpinotti/Apache_Camel_Rest/blob/main/instrucoes-mockoon/img/img4.png)
- Menu Logs: Permite vizualizar todas as chamadas realizadas para a API;

## Scripts do Mockoon ## 

  - Lista de Users: 
    - Endpoint : users
  ![](https://github.com/marcelpinotti/Apache_Camel_Rest/blob/main/instrucoes-mockoon/img/img1.png)

- Inserir o Script no Body:
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
  - User por Id: 
    - Endpoint: user/:id
    - Para identificar que existe um PathParam é necessário inserir os dois pontos(:) antes do parâmetro a ser passado na URL.
    ![](https://github.com/marcelpinotti/Apache_Camel_Rest/blob/main/instrucoes-mockoon/img/img2.png)

- Inserir o Script no Body:
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
