# Apache Camel - Rest API #

### A aplicação utiliza a arquitetura Rest, e simula a integração entre duas APIs. A primeira é uma API de cadastro de pessoas mockada com o Mockoon, e a segunda é uma API de cadastro endereços do WebService ViaCep. ###

#### ✒️ Os objetivos da aplicação foram, iniciar os estudos de implementação do Framework Apache Camel e integração entre APIs utilizando o framework. #####

- O Apache Camel é um framework que permite a integração entre sistemas, consumindo ou produzindo dados.
- Os componentes do Apache Camel facilitam as integrações:
  - por seguirem os EIPs (Enterprise Integration Patterns);
  - por se conectam a uma grande variedade de transportes e APIs;
  - por usar Linguagens específicas de domínio (DSLs) que conectam EIPs e os transportes.

#### 📋 A Aplicação: ####

- Como os estudos são iniciais, a aplicação expõe endpoints utilizando o Método GET do protocolo HTTP.

✒️ Consome dados da API Mockada no Mockoon:

``` 
Endpoint do Mockoon (GET): http://localhost:3000/api/users
Endpoint do Camel (GET): http://localhost:8081/api/camel-users
Estrutura de dados:
[
	{
  		"id": "number",
  		"name": "string",
  		"lastname": "string",
  		"email": "string",
  		"password":"string",
  		"cep":"string"
	}
]
```
``` 
Endpoint(GET): http://localhost:3000/api/user/{id}
Endpoint do Camel (GET): http://localhost:8081/api/camel-user/{id}
Estrutura de dados:
{
  	"id": "number",
  	"name": "string",
  	"lastname": "string",
  	"email": "string",
  	"password":"string",
  	"cep":"string"
}
```

✒️ Consome dados do WebService ViaCep :

``` 
Endpoint do ViaCep (GET): http://viacep.com.br/ws/{cep}/json/
Estrutura de dados:
{
	"cep": "string",
    "logradouro": "string",
    "complemento": "string",
    "bairro": "string",
    "localidade": "string",
    "uf": "string",
    "ibge": "string",
    "gia": "string",
    "ddd": "string",
    "siafi": "string"
}

Endpoint do Camel (GET): http://localhost:8081/api/camel-cep/{cep}
Estrutura de dados:
{
  	"cep": "string",
    "logradouro": "string",
    "complemento": "string",
    "bairro": "string",
    "localidade": "string",
    "uf": "string"
}
```

✒️ Consome os dados da API do Mockoon e do WebService ViaCep e produz uma estrutura de dados com dados das duas Apis (Enriquecimento de dados).

``` 
Endpoint do Camel (GET): http://localhost:8081/api/camel-userViaCep/{id_do_user}
Estrutura de dados:
{
    "id": "number",
  	"name": "string",
  	"lastname": "string",
  	"email": "string",
  	"password":"string",
    "address": {
        "cep": "string",
    	"logradouro": "string",
    	"complemento": "string",
    	"bairro": "string",
    	"localidade": "string",
    	"uf": "string"
    }
}
```

## Tecnologias ## 

- IntelliJ IDEA Community Edition;
- Java v.17;
- Spring Boot v.2.7.4;
- Apache Camel  e Componentes v.3.18.x;
- Mockoon;
- Postman;

### 📋 Principais Desafios ###

Os desafios foram:
  -  Descobrir quais dependências eram necessárias para rodar e deixar a aplicação em standalone.
  -  Entender como configurar a aplicação para o consumir dados de diferentes APIs.
  -  Entender o fluxo de troca de mensagens e construção das estruturas de dados através do Apache Camel.
  -  Entender como criar o processo de enriquecimento e produção de uma nova estrutura de dados com dados de duas APIs que não possuiam comunicação.
  -  Construir o Controller para expor os endpoints da aplicação. 

### 📋 Principais Referências ###

1. Documentação: https://camel.apache.org/

2. Conceito: https://www.cybermedian.com/pt/enterprise-integration-patterns-eip-tutorial/

3. Conceito: https://dzone.com/articles/open-source-integration-apache

4. Exemplo: https://dev.to/thegusmao/utilizando-apachel-camel-para-agregar-endpoints-de-rest-apis-4pan#:~:text=Nossa%20aplica%C3%A7%C3%A3o%20Camel%20vai%20disponibilizar%20dois%20endpoints%3A%201,os%20livros%20do%20autor%20na%20API%20de%20livros.

5. Youtube: https://www.youtube.com/watch?v=X_351K0WS4g&t=1264s

